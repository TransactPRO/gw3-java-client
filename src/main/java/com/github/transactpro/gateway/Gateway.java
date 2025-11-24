package com.github.transactpro.gateway;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.digest.ResponseDigest;
import com.github.transactpro.gateway.model.request.Authorization;
import com.github.transactpro.gateway.operation.Operation;
import jakarta.validation.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provide ability to make requests to Transact Pro Gateway API v3.
 */
public class Gateway {

    @Getter
    @Setter
    private String url;
    @Getter
    private final Authorization authorization;
    @Setter
    private CloseableHttpClient httpClient;
    private Validator validator;

    /**
     * Main gateway constructor. Is used always. Builds required libraries for gateway work.
     *
     * @param authorization gateway authorization
     */
    public Gateway(Authorization authorization) {
        this.authorization = authorization;
        prepare();
    }

    /**
     * Constructor for account and secret key authorization
     *
     * @param objectGuid gateway authorization GUID
     * @param secretKey gateway authorization secret key
     * @param url gateway base url
     */
    public Gateway(String objectGuid, String secretKey, String url) {
        this(new Authorization(objectGuid, secretKey));
        this.url = url;
    }

    /**
     * Constructor for session id authorization.
     *
     * @param objectGuid gateway authorization GUID
     * @param secretKey gateway authorization secret key
     * @param sessionId gateway authorization session id
     * @param url gateway base url
     */
    public Gateway(String objectGuid, String secretKey, String sessionId, String url) {
        this(new Authorization(objectGuid, secretKey, sessionId));
        this.url = url;
    }

    /**
     * Building all libraries
     */
    private void prepare() {
        buildHttpClient();
        buildValidator();
    }

    /**
     * Create default http client
     */
    private void buildHttpClient() {
        httpClient = HttpClients.createDefault();
    }

    /**
     * Create default validator
     */
    private void buildValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    /**
     * Validate operation with group, that provided in operation
     *
     * @param operation The operation validate to.
     * @return set of validation errors
     */
    private Set<ConstraintViolation<Request>> validate(Operation<?> operation) {
        return validator.validate(operation.getRequest(), operation.getValidationGroups());
    }

    /**
     * Provide main gateway functionality. Validate operation and send json of model to operation endpoint.
     * On success set parsed response body to operation response.
     *
     * @param operation to process.
     * @throws ValidationException      when operation can't pass validation
     * @throws IOException              when http request is faulty
     * @throws InvalidKeyException      when request digest creation error
     * @throws NoSuchAlgorithmException when request digest creation error
     * @throws ParseException           when response parsing is unsuccessful
     */
    public void process(Operation<?> operation) throws ValidationException, IOException, InvalidKeyException, NoSuchAlgorithmException, ParseException {
        Set<ConstraintViolation<Request>> constraintViolations = validate(operation);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Request> cv = constraintViolations.iterator().next();
            throw new ValidationException(
                    String.format("Validation error! property: [%s], value: [%s], message: [%s]",
                            cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }

        ClassicHttpResponse httpResponse = httpClient.execute(buildRequest(operation));

        final HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        EntityUtils.consume(entity);

        Map<String, String> headers = new HashMap<>();
        for (final Header header : httpResponse.getHeaders()) {
            headers.put(header.getName().toLowerCase(), header.getValue());
        }

        int statusCode = httpResponse.getCode();
        Response<?> response = operation.createResponse(statusCode, responseBody, headers);

        if (response.isSuccessful()) {
            ResponseDigest responseDigest = new ResponseDigest(httpResponse.getFirstHeader("Authorization"));
            response.setDigest(responseDigest);

            responseDigest.setOriginalUri(operation.getRequest().getDigest().getUri());
            responseDigest.setOriginalCnonce(operation.getRequest().getDigest().getCnonce());
            responseDigest.setBody(responseBody);
            responseDigest.verify(authorization.getObjectGuid(), authorization.getSecretKey());
        }
    }

    /**
     * Build GET or POST request from operation data for HttpClient
     *
     * @param operation transaction or another operation
     * @return request for http client
     */
    private ClassicHttpRequest buildRequest(Operation<?> operation) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        /* Setting session ID for request if exists */
        if (authorization.getSessionId() != null) {
            operation.getRequest().getAuthorization().setSessionId(authorization.getSessionId());
        }

        String payload = operation.getRequestPayload();
        StringEntity requestBody = new StringEntity(payload, StandardCharsets.UTF_8);

        String uri = operation.getRequest().getUri(this.url, operation.getRequestUri());
        String sign = operation.getRequest().getSign(authorization.getObjectGuid(), authorization.getSecretKey(), uri, payload);

        String method = operation.getMethod();
        ClassicRequestBuilder requestBuilder = ClassicRequestBuilder
                .create(method)
                .setHeader("Authorization", sign)
                .setUri(uri)
                .setEntity(requestBody);

        if (!method.equals("GET")) {
            requestBuilder.setHeader("Content-Type", "application/json");
        }

        return requestBuilder.build();
    }
}

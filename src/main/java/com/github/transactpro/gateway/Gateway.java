package com.github.transactpro.gateway;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.digest.ResponseDigest;
import com.github.transactpro.gateway.model.request.Authorization;
import com.github.transactpro.gateway.operation.Operation;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.validation.*;
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
    private HttpClient httpClient;
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
        httpClient = HttpClientBuilder
                .create()
                .build();
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
     */
    public void process(Operation<?> operation) throws ValidationException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        Set<ConstraintViolation<Request>> constraintViolations = validate(operation);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Request> cv = constraintViolations.iterator().next();
            throw new ValidationException(
                    String.format("Validation error! property: [%s], value: [%s], message: [%s]",
                            cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }

        HttpResponse httpResponse = httpClient.execute(buildRequest(operation));
        String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);

        Map<String, String> headers = new HashMap<>();
        for (final Header header : httpResponse.getAllHeaders()) {
            headers.put(header.getName().toLowerCase(), header.getValue());
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
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
    private HttpUriRequest buildRequest(Operation<?> operation) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        /* Setting session ID for request if exists */
        if (authorization.getSessionId() != null) {
            operation.getRequest().getAuthorization().setSessionId(authorization.getSessionId());
        }

        String payload = operation.getRequestPayload();
        StringEntity requestBody = new StringEntity(payload, "UTF-8");

        String uri = operation.getRequest().getUri(this.url, operation.getRequestUri());
        String sign = operation.getRequest().getSign(authorization.getObjectGuid(), authorization.getSecretKey(), uri, payload);

        String method = operation.getMethod();
        RequestBuilder requestBuilder = RequestBuilder
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

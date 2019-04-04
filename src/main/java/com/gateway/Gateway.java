package com.gateway;

import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.request.Authorization;
import com.gateway.operation.Operation;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.validation.*;
import java.io.IOException;
import java.util.Set;

/**
 * Provide ability to make requests to Transact Pro Gateway API v3.
 */
public class Gateway {

    private String url;
    @Getter
    private Authorization authorization;
    private HttpClient httpClient;
    private Gson jsonParser;
    private Validator validator;

    public Gateway(String url) {
        this.url = url;
        this.authorization = new Authorization();
        buildHttpClient();
        buildJsonParser();
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
     * Create default JSON builder
     */
    private void buildJsonParser() {
        jsonParser = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();
    }

    /**
     * Validate operation with group, that provided in operation
     *
     * @param operation
     * @return
     */
    private Set<ConstraintViolation<Request>> validate(Operation operation) {
        return validator.validate(operation.getRequest(), operation.getValidationGroups());
    }

    /**
     * Provide main gateway functionality. Validate operation and send json of model to operation endpoint.
     * On success set parsed response body to operation response.
     *
     * @param operation
     * @throws ValidationException
     * @throws IOException
     */
    public void process(Operation operation) throws ValidationException, IOException {
        Set<ConstraintViolation<Request>> constraintViolations = validate(operation);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Request> cv = constraintViolations.iterator().next();
            throw new ValidationException(
                    String.format("Validation error! property: [%s], value: [%s], message: [%s]",
                            cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }

        operation.getRequest().setAuthorization(authorization);

        HttpResponse httpResponse = httpClient.execute(buildRequest(operation));
        System.out.println(httpResponse.getEntity());
        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        operation.setResponse(jsonParser.fromJson(responseBody, Response.class));
    }


    /**
     * Build GET or POST request from operation data for HttpClient
     *
     * @param operation
     * @return
     */
    private HttpUriRequest buildRequest(Operation operation) {
        String json = jsonParser.toJson(operation.getRequest());

        StringEntity requestBody = new StringEntity(json, "UTF-8");

        return RequestBuilder
                .create(operation.getMethod())
                .setUri(url + operation.getRequestUri())
                .setEntity(requestBody)
                .build();
    }
}

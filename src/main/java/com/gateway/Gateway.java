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
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.validation.*;
import java.io.IOException;
import java.util.Set;


public class Gateway {

    private String url;
    @Getter
    private Authorization authorization;
    private HttpClient httpClient;
    private Gson gson;
    private Validator validator;

    public Gateway(String url) {
        this.url = url;
        this.authorization = new Authorization();
        buildHttpClient();
        buildJsonParser();
        buildValidator();
    }

    private void buildHttpClient() {
        httpClient = HttpClientBuilder
                .create()
                .build();
    }

    private void buildValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    private void buildJsonParser() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();
    }

    private Set<ConstraintViolation<Request>> validate(Operation operation) {
        return validator.validate(operation.getRequest(), operation.getValidationGroups());
    }

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
        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        System.out.println(responseBody);

        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            operation.setResponse(gson.fromJson(responseBody, Response.class));
        }
        return;
    }

    private HttpUriRequest buildRequest(Operation operation) {
        String json = gson.toJson(operation.getRequest());
        //DEBUG
        System.out.println(json);
        System.out.println();

        StringEntity requestBody = new StringEntity(json, "UTF-8");

        return RequestBuilder
                .create(operation.getMethod())
                .setUri(url + operation.getRequestUri())
                .setEntity(requestBody)
                .build();
    }
}

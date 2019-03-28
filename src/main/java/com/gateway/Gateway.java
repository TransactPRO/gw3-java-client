package com.gateway;

import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.request.Authorization;
import com.gateway.operation.Operation;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;


public class Gateway {

    private String url;
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

    public void process(Operation operation) throws IOException {
        Set<ConstraintViolation<Request>> constraintViolations = validate(operation);

        if (constraintViolations.size() > 0) {
            //got errors
            //TODO think what to respond
            System.out.println(String.format("Кол-во ошибок: %d", constraintViolations.size()));
            for (ConstraintViolation<Request> cv : constraintViolations)
                System.out.println(String.format(
                        "Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
            return;
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
        StringEntity requestBody = new StringEntity(gson.toJson(operation.getRequest()), "UTF-8");

        return RequestBuilder
                .create(operation.getRequestMethod())
                .setUri(url + operation.getRequestUri())
                .setEntity(requestBody)
                .build();
    }

    public Authorization getAuthorization() {
        return authorization;
    }
}

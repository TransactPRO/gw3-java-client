package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;

public abstract class Operation implements Operable {

    protected Request request;
    protected Response response = null;

    public Operation() {
        request = new Request();
    }

    public Request getRequest() {
        return request;
    }

    public Operation setRequest(Request request) {
        this.request = request;
        return this;
    }

    public abstract String getRequestUri();

    public abstract String getRequestMethod();

    public abstract Class<?> getValidationGroups();

    public Response getResponse() {
        return response;
    }

    public Operation setResponse(Response response) {
        this.response = response;
        return this;
    }
}

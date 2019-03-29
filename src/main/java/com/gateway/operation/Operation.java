package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;

import javax.validation.groups.Default;

public abstract class Operation implements Operable {

    protected Request request;
    protected Response response = null;
    protected String method = "POST";

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

    public Response getResponse() {
        return response;
    }

    public Operation setResponse(Response response) {
        this.response = response;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public boolean isSuccessful() {
        return this.response.getError() == null;
    }

    @Override
    public Class<?> getValidationGroups() {
        return Default.class;
    }
}

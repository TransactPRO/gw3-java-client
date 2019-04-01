package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.response.Error;

import javax.validation.groups.Default;

public abstract class Operation<T> implements Operable {

    protected Request request;
    protected Response response = null;
    protected String method = "POST";

    public Operation() {
        request = new Request();
    }

    public Request getRequest() {
        return request;
    }

    public void setResponse(Response response) {
        this.response = response;
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

    public Error getError() {
        return response.getError();
    }
}

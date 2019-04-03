package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.response.Error;
import lombok.Getter;

import javax.validation.groups.Default;

public abstract class Operation implements Operable {

    @Getter
    protected Request request;
    @Getter
    protected Response response;
    protected String method = "POST";

    public Operation() {
        request = new Request();
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getMethod() {
        return method;
    }

    public boolean isSuccessful() {
        return this.response.getError().getMessage() == null;
    }

    @Override
    public Class getValidationGroups() {
        return Default.class;
    }

    public Error getError() {
        return response.getError();
    }
}

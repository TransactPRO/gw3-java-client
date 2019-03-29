package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;

public abstract class Operation implements Operable {

    protected Request request;
    protected Response response = null;
    protected String method = "POST";

    public Operation() {
        request = new Request();
    }

    public String getMethod() {
        return method;
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

    public boolean isSuccessful() {
        return this.response.getError() == null;
    }

    public Operation setResponse(Response response) {
        this.response = response;
        return this;
    }
}

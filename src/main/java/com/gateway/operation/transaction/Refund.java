package com.gateway.operation.transaction;

public class Refund {

    public final String uri = "/refund";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
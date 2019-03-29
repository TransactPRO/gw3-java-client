package com.gateway.operation.transaction;

public class Cancel {

    public final String uri = "/cancel";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
package com.gateway.operation.transaction;

public class MotoDms {

    public final String uri = "/moto/dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
package com.gateway.operation.transaction;

public class DmsHold {

    public final String uri = "/hold-dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
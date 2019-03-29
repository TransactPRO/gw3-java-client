package com.gateway.operation.transaction;

public class DmsCharge {

    public final String uri = "/charge-dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
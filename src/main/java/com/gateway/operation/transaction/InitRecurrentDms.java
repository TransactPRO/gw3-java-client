package com.gateway.operation.transaction;

public class InitRecurrentDms {

    public final String uri = "/recurrent/dms/init";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
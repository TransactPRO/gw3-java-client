package com.gateway.operation.transaction;

public class RecurrentDms {

    public final String uri = "/recurrent/dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
package com.gateway.operation.transaction;

public class Credit {

    public final String uri = "/credit";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
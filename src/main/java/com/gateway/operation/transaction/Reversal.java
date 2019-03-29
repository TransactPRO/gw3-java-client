package com.gateway.operation.transaction;

public class Reversal {

    public final String uri = "/reversal";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
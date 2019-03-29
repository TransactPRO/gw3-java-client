package com.gateway.operation.transaction;

public class B2P {

    public final String uri = "/b2p";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
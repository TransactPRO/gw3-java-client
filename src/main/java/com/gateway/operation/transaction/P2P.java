package com.gateway.operation.transaction;

public class P2P {

    public final String uri = "/p2p";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
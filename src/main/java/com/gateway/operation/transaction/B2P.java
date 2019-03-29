package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class B2P extends Operation {

    public final String uri = "/b2p";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
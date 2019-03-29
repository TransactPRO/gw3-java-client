package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class P2P  extends Operation {

    public final String uri = "/p2p";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
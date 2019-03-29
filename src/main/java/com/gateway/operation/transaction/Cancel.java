package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class Cancel extends Operation {

    public final String uri = "/cancel";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
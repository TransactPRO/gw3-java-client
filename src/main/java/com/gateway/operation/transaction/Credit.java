package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class Credit extends Operation {

    public final String uri = "/credit";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
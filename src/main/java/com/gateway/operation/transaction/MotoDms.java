package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class MotoDms extends Operation {

    public final String uri = "/moto/dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
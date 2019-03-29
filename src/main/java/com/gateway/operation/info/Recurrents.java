package com.gateway.operation.info;

import com.gateway.operation.Operation;

public class Recurrents extends Operation {

    public final String uri = "/recurrents";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
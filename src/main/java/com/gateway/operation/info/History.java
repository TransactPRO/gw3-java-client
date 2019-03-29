package com.gateway.operation.info;

import com.gateway.operation.Operation;

public class History extends Operation {

    public final String uri = "/history";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}

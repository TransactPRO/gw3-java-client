package com.gateway.operation.info;

import com.gateway.operation.Operation;

public class Result extends Operation {

    public final String uri = "/result";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}

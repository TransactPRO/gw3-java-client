package com.gateway.operation.info;

import com.gateway.operation.Operation;

public class Refunds extends Operation {

    public final String uri = "/refunds";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}

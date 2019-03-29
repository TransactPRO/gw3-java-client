package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class Refund extends Operation {

    public final String uri = "/refund";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
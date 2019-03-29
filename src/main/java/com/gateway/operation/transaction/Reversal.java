package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class Reversal extends Operation {

    public final String uri = "/reversal";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
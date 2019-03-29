package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class RecurrentDms extends Operation {

    public final String uri = "/recurrent/dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
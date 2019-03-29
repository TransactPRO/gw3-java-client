package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class InitRecurrentDms extends Operation {

    public final String uri = "/recurrent/dms/init";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
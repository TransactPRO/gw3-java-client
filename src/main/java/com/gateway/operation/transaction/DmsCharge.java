package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class DmsCharge extends Operation {

    public final String uri = "/charge-dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
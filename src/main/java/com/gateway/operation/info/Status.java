package com.gateway.operation.info;


import com.gateway.operation.Operation;

public class Status extends Operation {

    public final String uri = "/status";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }


}

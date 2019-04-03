package com.gateway.operation.info;


import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.InfoInterface;

public class Status extends Operation implements InfoInterface<Status> {

    public final String uri = "/status";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return null;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

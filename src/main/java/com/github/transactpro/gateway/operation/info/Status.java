package com.github.transactpro.gateway.operation.info;


import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Status extends Operation implements InfoInterface<Status> {

    private final String uri = "/status";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

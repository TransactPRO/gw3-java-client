package com.transactpro.gateway.operation.info;


import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Status extends Operation implements InfoInterface<Status> {

    public final String uri = "/status";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

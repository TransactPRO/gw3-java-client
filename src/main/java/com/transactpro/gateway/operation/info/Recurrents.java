package com.transactpro.gateway.operation.info;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Recurrents extends Operation implements InfoInterface<Recurrents> {

    private final String uri = "/recurrents";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
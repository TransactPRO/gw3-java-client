package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Refunds extends Operation implements InfoInterface<Refunds> {

    private final String uri = "/refunds";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

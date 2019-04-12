package com.transactpro.gateway.operation.info;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.InfoInterface;

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

package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class History extends Operation implements InfoInterface<History> {

    private final String uri = "/history";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

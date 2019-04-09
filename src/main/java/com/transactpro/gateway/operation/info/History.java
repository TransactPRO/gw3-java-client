package com.transactpro.gateway.operation.info;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class History extends Operation implements InfoInterface<History> {

    public final String uri = "/history";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

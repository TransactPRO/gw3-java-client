package com.transactpro.gateway.operation.info;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Result extends Operation implements InfoInterface<Result> {

    public final String uri = "/result";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

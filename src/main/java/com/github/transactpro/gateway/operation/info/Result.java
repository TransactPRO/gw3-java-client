package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Result extends Operation implements InfoInterface<Result> {

    private final String uri = "/result";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

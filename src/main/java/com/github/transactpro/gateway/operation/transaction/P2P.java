package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.TransactionGroup;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

public class P2P extends Operation implements TransactionInterface<P2P> {

    private final String uri = "/p2p";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return TransactionGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
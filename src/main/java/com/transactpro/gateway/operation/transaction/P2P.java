package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.ToPersonGroup;

public class P2P extends Operation implements TransactionInterface<P2P> {

    public final String uri = "/p2p";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return ToPersonGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
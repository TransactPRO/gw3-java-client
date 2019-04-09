package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.ToPersonGroup;

public class B2P extends Operation implements TransactionInterface<B2P> {

    public final String uri = "/b2p";

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
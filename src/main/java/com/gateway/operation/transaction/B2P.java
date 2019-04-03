package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;

public class B2P extends Operation implements Transaction<B2P> {

    public final String uri = "/b2p";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return null;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
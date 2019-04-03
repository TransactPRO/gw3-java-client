package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;
import com.gateway.validation.ToPersonGroup;

public class P2P extends Operation implements Transaction<P2P> {

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
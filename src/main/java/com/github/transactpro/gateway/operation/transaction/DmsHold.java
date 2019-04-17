package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.TransactionGroup;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

public class DmsHold extends Operation implements TransactionInterface<DmsHold> {

    private final String uri = "/hold-dms";

    @Override
    public Operation getOperation() {
        return this;
    }

    @Override
    public Class getValidationGroups() {
        return TransactionGroup.class;
    }

    public String getRequestUri() {
        return uri;
    }
}
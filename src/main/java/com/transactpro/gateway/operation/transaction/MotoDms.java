package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.TransactionGroup;

public class MotoDms extends Operation implements TransactionInterface<MotoDms> {

    private final String uri = "/moto/dms";

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
package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.TransactionGroup;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

public class InitRecurrentDms extends Operation implements TransactionInterface<InitRecurrentDms> {

    private final String uri = "/recurrent/dms/init";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Class getValidationGroups() {
        return TransactionGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.TransactionGroup;

public class InitRecurrentDms extends Operation implements TransactionInterface<InitRecurrentDms> {

    public final String uri = "/recurrent/dms/init";

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
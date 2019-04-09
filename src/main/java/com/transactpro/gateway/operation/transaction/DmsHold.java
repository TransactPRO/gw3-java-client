package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.TransactionGroup;

public class DmsHold extends Operation implements TransactionInterface<DmsHold> {

    public final String uri = "/hold-dms";

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
package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;
import com.gateway.validation.TransactionGroup;

public class InitRecurrentDms extends Operation implements Transaction<InitRecurrentDms> {

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
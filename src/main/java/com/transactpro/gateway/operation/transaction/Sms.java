package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.validation.TransactionGroup;

public class Sms extends Operation implements TransactionInterface<Sms> {

    public final String uri = "/sms";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }

    @Override
    public Class getValidationGroups() {
        return TransactionGroup.class;
    }
}

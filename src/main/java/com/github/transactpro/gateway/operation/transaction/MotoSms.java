package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.TransactionGroup;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

public class MotoSms  extends Operation implements TransactionInterface<MotoSms> {

    private final String uri = "/moto/sms";

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
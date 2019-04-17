package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.TransactionGroup;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

public class InitRecurrentSms extends Operation implements TransactionInterface<InitRecurrentSms> {

    private final String uri = "/recurrent/sms/init";

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
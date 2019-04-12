package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.transactpro.gateway.validation.TransactionGroup;

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
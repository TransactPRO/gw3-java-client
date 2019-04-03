package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;
import com.gateway.validation.TransactionGroup;

public class InitRecurrentSms extends Operation implements Transaction<InitRecurrentSms> {

    public final String uri = "/recurrent/sms/init";

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
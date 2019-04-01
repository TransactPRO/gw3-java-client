package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;

public class InitRecurrentSms extends Operation implements Transaction<InitRecurrentSms> {

    public final String uri = "/recurrent/sms/init";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
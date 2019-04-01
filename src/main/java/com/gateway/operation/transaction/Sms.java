package com.gateway.operation.transaction;

import com.gateway.operation.interfaces.Transaction;
import com.gateway.operation.Operation;
import com.gateway.validation.SmsValidation;

public class Sms extends Operation implements Transaction<Sms> {

    public final String uri = "/sms";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }

    @Override
    public Class<?> getValidationGroups() {
        return SmsValidation.class;
    }
}

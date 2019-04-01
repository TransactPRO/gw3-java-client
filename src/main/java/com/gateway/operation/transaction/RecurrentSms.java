package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;

public class RecurrentSms extends Operation implements ChargeInterface<RecurrentSms> {

    public final String uri = "/recurrent/sms";

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
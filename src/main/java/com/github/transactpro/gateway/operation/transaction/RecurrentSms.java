package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.CommandAmountGroup;
import com.github.transactpro.gateway.operation.interfaces.request.ChargeInterface;

public class RecurrentSms extends Operation implements ChargeInterface<RecurrentSms> {

    private final String uri = "/recurrent/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return CommandAmountGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
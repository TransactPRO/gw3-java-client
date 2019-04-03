package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;
import com.gateway.validation.CommandAmountGroup;

public class RecurrentSms extends Operation implements ChargeInterface<RecurrentSms> {

    public final String uri = "/recurrent/sms";

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
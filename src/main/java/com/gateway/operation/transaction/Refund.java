package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;
import com.gateway.validation.CommandAmountGroup;

public class Refund extends Operation implements ChargeInterface<Refund> {

    public final String uri = "/refund";

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
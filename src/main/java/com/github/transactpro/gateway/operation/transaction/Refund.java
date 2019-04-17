package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.CommandAmountGroup;
import com.github.transactpro.gateway.operation.interfaces.request.ChargeInterface;

public class Refund extends Operation implements ChargeInterface<Refund> {

    private final String uri = "/refund";

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
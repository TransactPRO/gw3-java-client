package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.CommandAmountGroup;
import com.github.transactpro.gateway.operation.interfaces.request.ChargeInterface;

public class DmsCharge extends Operation implements ChargeInterface<DmsCharge> {

    private final String uri = "/charge-dms";

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
package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.ChargeInterface;
import com.transactpro.gateway.validation.CommandAmountGroup;

public class RecurrentDms extends Operation implements ChargeInterface<RecurrentDms> {

    private final String uri = "/recurrent/dms";

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
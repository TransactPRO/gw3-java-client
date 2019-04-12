package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.base.CommandInterface;
import com.transactpro.gateway.operation.interfaces.request.base.OrderInterface;
import com.transactpro.gateway.validation.base.CommandTransactionIdGroup;

public class Reversal extends Operation implements CommandInterface<Reversal>, OrderInterface<Reversal> {

    private final String uri = "/reversal";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return CommandTransactionIdGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
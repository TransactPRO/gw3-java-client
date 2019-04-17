package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.base.CommandTransactionIdGroup;
import com.github.transactpro.gateway.operation.interfaces.request.base.CommandInterface;
import com.github.transactpro.gateway.operation.interfaces.request.base.OrderInterface;

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
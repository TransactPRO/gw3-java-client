package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.base.CommandInterface;
import com.transactpro.gateway.operation.interfaces.request.base.OrderInterface;
import com.transactpro.gateway.validation.base.CommandTransactionIdGroup;

public class Cancel extends Operation implements CommandInterface<Cancel>, OrderInterface<Cancel> {

    private final String uri = "/cancel";

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
package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.base.CommandInterface;
import com.gateway.operation.interfaces.base.OrderInterface;
import com.gateway.validation.base.CommandTransactionIdGroup;

public class Cancel extends Operation implements CommandInterface<Cancel>, OrderInterface<Cancel> {

    public final String uri = "/cancel";

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
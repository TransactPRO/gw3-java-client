package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.base.CommandInterface;
import com.gateway.operation.interfaces.base.OrderInterface;

public class Reversal extends Operation implements CommandInterface<Reversal>, OrderInterface<Reversal> {

    public final String uri = "/reversal";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return null;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
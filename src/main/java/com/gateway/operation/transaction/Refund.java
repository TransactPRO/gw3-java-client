package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;

public class Refund extends Operation implements ChargeInterface<Refund> {

    public final String uri = "/refund";

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
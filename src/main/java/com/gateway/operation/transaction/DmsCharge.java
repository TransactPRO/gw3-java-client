package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;

public class DmsCharge extends Operation implements ChargeInterface<DmsCharge> {

    public final String uri = "/charge-dms";

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
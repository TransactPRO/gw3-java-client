package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.ChargeInterface;
import com.transactpro.gateway.validation.DmsChargeGroup;

public class DmsCharge extends Operation implements ChargeInterface<DmsCharge> {

    public final String uri = "/charge-dms";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return DmsChargeGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
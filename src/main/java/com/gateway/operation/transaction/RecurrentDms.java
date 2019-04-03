package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.ChargeInterface;

public class RecurrentDms extends Operation implements ChargeInterface<RecurrentDms> {

    public final String uri = "/recurrent/dms";

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
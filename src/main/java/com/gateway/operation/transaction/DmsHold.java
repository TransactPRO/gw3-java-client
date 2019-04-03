package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;

public class DmsHold extends Operation implements Transaction<DmsHold> {

    public final String uri = "/hold-dms";

    @Override
    public Operation getOperation() {
        return this;
    }

    @Override
    public Class getValidationGroups() {
        return super.getValidationGroups();
    }

    public String getRequestUri() {
        return uri;
    }
}
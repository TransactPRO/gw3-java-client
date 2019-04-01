package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;

public class MotoDms extends Operation implements Transaction<MotoDms> {

    public final String uri = "/moto/dms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
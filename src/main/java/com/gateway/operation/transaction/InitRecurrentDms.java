package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;

public class InitRecurrentDms extends Operation implements Transaction<InitRecurrentDms> {

    public final String uri = "/recurrent/dms/init";

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
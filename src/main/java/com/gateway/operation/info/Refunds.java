package com.gateway.operation.info;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.InfoInterface;

public class Refunds extends Operation implements InfoInterface<Refunds> {

    public final String uri = "/refunds";

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

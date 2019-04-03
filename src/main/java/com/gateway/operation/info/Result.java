package com.gateway.operation.info;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.InfoInterface;

public class Result extends Operation implements InfoInterface<Result> {

    public final String uri = "/result";

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

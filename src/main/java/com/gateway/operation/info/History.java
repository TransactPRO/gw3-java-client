package com.gateway.operation.info;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.InfoInterface;

public class History extends Operation implements InfoInterface<History> {

    public final String uri = "/history";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

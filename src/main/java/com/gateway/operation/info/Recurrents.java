package com.gateway.operation.info;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.InfoInterface;

public class Recurrents extends Operation implements InfoInterface<Recurrents> {

    public final String uri = "/recurrents";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
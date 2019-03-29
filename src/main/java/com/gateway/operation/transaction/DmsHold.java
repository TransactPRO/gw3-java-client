package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class DmsHold extends Operation {

    public final String uri = "/hold-dms";

    public String getRequestUri() {
        return uri;
    }
}
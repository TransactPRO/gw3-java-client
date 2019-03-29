package com.gateway.operation.verify;

import com.gateway.operation.Operation;

public class Enrolled3D extends Operation {

    public final String uri = "/verify/3d-enrollment";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}

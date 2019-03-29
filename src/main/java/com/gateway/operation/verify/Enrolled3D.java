package com.gateway.operation.verify;

import com.gateway.operation.Operation;

public class Enrolled3D extends Operation {
    public final String uri = "verify/3d-enrollment";
    public final String method = "POST";

    public String getRequestUri() {
        return uri;
    }

    public String getRequestMethod() {
        return method;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}

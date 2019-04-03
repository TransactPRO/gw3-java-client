package com.gateway.operation.verify;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.base.DataInterface;

public class Enrolled3D extends Operation implements DataInterface<Enrolled3D> {

    public final String uri = "/verify/3d-enrollment";

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

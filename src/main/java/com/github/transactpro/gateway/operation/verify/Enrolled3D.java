package com.github.transactpro.gateway.operation.verify;

import com.github.transactpro.gateway.validation.EnrollGroup;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.base.DataInterface;

public class Enrolled3D extends Operation implements DataInterface<Enrolled3D> {

    private final String uri = "/verify/3d-enrollment";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Class getValidationGroups() {
        return EnrollGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

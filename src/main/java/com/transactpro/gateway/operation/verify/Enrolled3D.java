package com.transactpro.gateway.operation.verify;

import com.transactpro.gateway.operation.Operation;
import com.transactpro.gateway.operation.interfaces.request.base.DataInterface;
import com.transactpro.gateway.validation.EnrollGroup;

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

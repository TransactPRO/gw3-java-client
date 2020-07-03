package com.github.transactpro.gateway.operation.verify;

import com.github.transactpro.gateway.model.response.EnrollmentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.base.DataInterface;
import com.github.transactpro.gateway.validation.EnrollGroup;

public class Enrolled3D extends Operation<EnrollmentResponse> implements DataInterface<Enrolled3D> {
    {
        requestUri = "/verify/3d-enrollment";
        responseType = EnrollmentResponse.class;
    }

    @Override
    public Class<?> getValidationGroups() {
        return EnrollGroup.class;
    }
}

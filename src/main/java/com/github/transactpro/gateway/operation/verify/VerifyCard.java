package com.github.transactpro.gateway.operation.verify;

import com.github.transactpro.gateway.model.response.GenericResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.base.DataInterface;
import com.github.transactpro.gateway.validation.VerifyCardGroup;

public class VerifyCard extends Operation<GenericResponse> implements DataInterface<VerifyCard> {
    {
        requestUri = "/verify/card";
        responseType = GenericResponse.class;
    }

    @Override
    public Class<?> getValidationGroups() {
        return VerifyCardGroup.class;
    }
}

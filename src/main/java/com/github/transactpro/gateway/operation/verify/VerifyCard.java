package com.github.transactpro.gateway.operation.verify;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.base.DataInterface;
import com.github.transactpro.gateway.validation.VerifyCardGroup;

public class VerifyCard extends Operation implements DataInterface<VerifyCard> {

    private final String uri = "/verify/card";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Class getValidationGroups() {
        return VerifyCardGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

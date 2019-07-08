package com.github.transactpro.gateway.operation.token;

import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

import javax.validation.groups.Default;

public class CreateToken extends Operation implements TransactionInterface<CreateToken> {

    private final String uri = "/token/create";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Class getValidationGroups() {
        return Default.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}

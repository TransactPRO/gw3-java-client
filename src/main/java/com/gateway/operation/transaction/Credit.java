package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;
import com.gateway.validation.CreditGroup;

public class Credit extends Operation implements Transaction<Credit> {

    public final String uri = "/credit";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return CreditGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
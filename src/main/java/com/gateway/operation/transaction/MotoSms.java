package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.operation.interfaces.Transaction;
import com.gateway.validation.MotoGroup;

public class MotoSms  extends Operation implements Transaction<MotoDms> {

    public final String uri = "/moto/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class getValidationGroups() {
        return MotoGroup.class;
    }

    @Override
    public Operation getOperation() {
        return this;
    }
}
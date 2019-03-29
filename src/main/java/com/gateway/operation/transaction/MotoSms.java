package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class MotoSms  extends Operation {

    public final String uri = "/moto/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
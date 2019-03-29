package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class RecurrentSms extends Operation {

    public final String uri = "/recurrent/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
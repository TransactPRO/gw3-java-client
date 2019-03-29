package com.gateway.operation.transaction;

import com.gateway.operation.Operation;

public class InitRecurrentSms extends Operation {

    public final String uri = "/recurrent/sms/init";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
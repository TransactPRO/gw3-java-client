package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.validation.SmsValidation;

public class Sms extends Operation {

    public final String uri = "/sms";

    public String getRequestUri() {
        return uri;
    }

    @Override
    public Class<?> getValidationGroups() {
        return SmsValidation.class;
    }
}

package com.gateway.operation.transaction;

import com.gateway.operation.Operation;
import com.gateway.validation.SmsValidation;

public class Sms extends Operation {

    public String getRequestUri() {
        return "/sms";
    }

    public String getRequestMethod() {
        return "POST";
    }

    public Class<?> getValidationGroups() {
        return SmsValidation.class;
    }
}

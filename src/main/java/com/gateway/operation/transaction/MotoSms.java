package com.gateway.operation.transaction;

public class MotoSms {

    public final String uri = "/moto/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
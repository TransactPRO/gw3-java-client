package com.gateway.operation.transaction;

public class RecurrentSms {

    public final String uri = "/recurrent/sms";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
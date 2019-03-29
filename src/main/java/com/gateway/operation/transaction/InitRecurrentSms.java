package com.gateway.operation.transaction;

public class InitRecurrentSms {

    public final String uri = "/recurrent/sms/init";

    public String getRequestUri() {
        return uri;
    }

    public Class<?> getValidationGroups() {
        return null;
    }
}
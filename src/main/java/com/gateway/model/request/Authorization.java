package com.gateway.model.request;

public class Authorization {
    private String accountGuid;
    private String secretKey;
    private String sessionId;

    public String getAccountGuid() {
        return accountGuid;
    }

    public Authorization setAccountGuid(String accountGuid) {
        this.accountGuid = accountGuid;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Authorization setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Authorization setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}

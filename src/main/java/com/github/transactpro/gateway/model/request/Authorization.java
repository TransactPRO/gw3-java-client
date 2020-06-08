package com.github.transactpro.gateway.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Authorization {

    private String objectGuid;
    private String secretKey;
    private String sessionId;

    public Authorization() { }

    public Authorization(String objectGuid, String secretKey) {
        this.objectGuid = objectGuid;
        this.secretKey = secretKey;
    }

    public Authorization(String objectGuid, String secretKey, String sessionId) {
        this.objectGuid = objectGuid;
        this.secretKey = secretKey;
        this.sessionId = sessionId;
    }
}

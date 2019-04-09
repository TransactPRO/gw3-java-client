package com.transactpro.gateway.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter()
@Accessors(chain = true)
public class Authorization {
    private String accountGuid;
    private String secretKey;
    private String sessionId;

    public Authorization(String accountGuid, String secretKey) {
        this.accountGuid = accountGuid;
        this.secretKey = secretKey;
    }
}

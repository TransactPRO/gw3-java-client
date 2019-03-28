package com.gateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authorization {
    private String accountGuid;
    private String secretKey;
    private String sessionId;
}

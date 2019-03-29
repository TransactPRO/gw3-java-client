package com.gateway.model.request;

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
}

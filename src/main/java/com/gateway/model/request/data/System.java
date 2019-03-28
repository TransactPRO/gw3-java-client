package com.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class System {
    private String userIp;
    private String xForwardedFor;
}

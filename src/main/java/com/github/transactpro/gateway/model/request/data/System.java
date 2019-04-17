package com.github.transactpro.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class System {

    private String userIp;
    private String xForwardedFor;
}

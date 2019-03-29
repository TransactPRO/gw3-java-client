package com.gateway.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Error {
    private Integer code;
    private String message;
}

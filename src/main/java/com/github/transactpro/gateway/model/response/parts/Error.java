package com.github.transactpro.gateway.model.response.parts;

import com.github.transactpro.gateway.model.response.constants.ErrorCode;
import lombok.Getter;

@Getter
public class Error {
    private int code;
    private String message;

    public ErrorCode getCodeAsEnum() {
        for (ErrorCode enumCode : ErrorCode.values()) {
            if (enumCode.getValue() == code) {
                return enumCode;
            }
        }

        return null;
    }
}

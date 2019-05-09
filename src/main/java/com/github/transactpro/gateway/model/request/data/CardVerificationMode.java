package com.github.transactpro.gateway.model.request.data;

import lombok.Getter;

public enum CardVerificationMode {
    INIT(1),
    VERIFY(2);

    @Getter
    private int value;

    CardVerificationMode(int value) {
        this.value = value;
    }
}

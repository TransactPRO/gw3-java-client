package com.github.transactpro.gateway.model.request.data.command;

import lombok.Getter;

@Getter
public enum PaymentMethodType {
    PAYMENT_METHOD_TYPE_CARD("cc"),
    PAYMENT_METHOD_TYPE_GOOGLE_PAY("google_pay"),
    PAYMENT_METHOD_TYPE_APPLE_PAY("apple_pay"),
    PAYMENT_METHOD_TYPE_CLICK2PAY("click2pay");

    private final String value;

    PaymentMethodType(String value) {
        this.value = value;
    }
}

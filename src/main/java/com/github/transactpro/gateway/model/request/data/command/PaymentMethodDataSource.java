package com.github.transactpro.gateway.model.request.data.command;

import lombok.Getter;

public enum PaymentMethodDataSource {
    DATA_SOURCE_CARDHOLDER(0),
    DATA_SOURCE_SAVE_TO_GATEWAY(1),
    DATA_SOURCE_USE_GATEWAY_SAVED_CARDHOLDER_INITIATED(2),
    DATA_SOURCE_SAVING_BY_MERCHANT(3),
    DATA_SOURCE_USE_MERCHANT_SAVED_CARDHOLDER_INITIATED(4),
    DATA_SOURCE_USE_GATEWAY_SAVED_MERCHANT_INITIATED(5),
    DATA_SOURCE_USE_MERCHANT_SAVED_MERCHANT_INITIATED(6);

    @Getter
    private int value;

    PaymentMethodDataSource(int value) {
        this.value = value;
    }
}

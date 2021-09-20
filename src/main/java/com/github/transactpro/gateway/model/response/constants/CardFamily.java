package com.github.transactpro.gateway.model.response.constants;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public enum CardFamily {
    @SerializedName("VISA")
    VISA(1),
    @SerializedName("MC")
    MASTER_CARD(2),
    @SerializedName("MA")
    MAESTRO(3),
    @SerializedName("AMEX")
    AMERICAN_EXPRESS(4);

    @Getter
    private final int value;

    CardFamily(int value) {
        this.value = value;
    }
}

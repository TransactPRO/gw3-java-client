package com.github.transactpro.gateway.model.response.parts;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Limit {
    @SerializedName("counter-type")
    private String counterType;
    @SerializedName("currency")
    private String currency;
    @SerializedName("limit")
    private int limit;
    @SerializedName("payment-method-subtype")
    private String paymentMethodSubtype;
    @SerializedName("payment-method-type")
    private String paymentMethodType;
    @SerializedName("value")
    private int value;
}

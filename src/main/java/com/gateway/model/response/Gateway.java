package com.gateway.model.response;

import com.google.gson.annotations.SerializedName;

public class Gateway {
    @SerializedName("gateway-transaction-id")
    private String transactionId;
    private Integer statusCode;
    private String statusText;
}

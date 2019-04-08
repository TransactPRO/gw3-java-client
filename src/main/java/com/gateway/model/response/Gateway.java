package com.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Gateway {

    @SerializedName("gateway-transaction-id")
    private String transactionId;
    private Integer statusCode;
    private String statusText;
    private String redirectUrl;
}

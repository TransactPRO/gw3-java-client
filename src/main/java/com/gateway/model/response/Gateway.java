package com.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Gateway {
    @SerializedName("gateway-transaction-id")
    private String transactionId;
    private Integer statusCode;
    private String statusText;
}

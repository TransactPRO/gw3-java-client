package com.gateway.model.request.data.general;

import com.gateway.model.request.data.general.order.Meta;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String merchantTransactionId;
    @SerializedName("merchant-user-id")
    private String merchantId;
    private String orderId;
    private String orderDescription;
    @SerializedName("merchant-side-url")
    private String merchantUrl;
    private String recipientName;
    private String merchantReferringName;
    private String custom3dReturnUrl;
    private Meta meta;
}

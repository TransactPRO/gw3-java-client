package com.gateway.model.request.data.general;

import com.gateway.model.request.data.general.order.Meta;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Accessors(chain = true)
public class Order {
    private String merchantTransactionId;
    @SerializedName("merchant-user-id")
    private String merchantId;
    private String orderId;
    private String orderDescription;
    @SerializedName("merchant-side-url")
    @URL
    private String merchantUrl;
    private String recipientName;
    private String merchantReferringName;
    private String custom3dReturnUrl;
    private Meta meta;
}

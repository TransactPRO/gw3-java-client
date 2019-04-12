package com.transactpro.gateway.model.request.data.general;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class Order {

    private String merchantTransactionId;
    @SerializedName("merchant-user-id")
    private String merchantId;
    @SerializedName("order-id")
    private String id;
    @SerializedName("order-description")
    private String description;
    @SerializedName("merchant-side-url")
    @URL
    private String merchantUrl;
    private String recipientName;
    private String merchantReferringName;
    private String custom3dReturnUrl;
    private Map<String, String> meta;
}

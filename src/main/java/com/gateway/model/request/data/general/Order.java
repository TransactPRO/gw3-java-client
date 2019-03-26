package com.gateway.model.request.data.general;

import com.gateway.model.request.data.general.order.Meta;
import com.google.gson.annotations.SerializedName;

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

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public Order setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
        return this;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Order setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public Order setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }

    public Order setMerchantUrl(String merchantUrl) {
        this.merchantUrl = merchantUrl;
        return this;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public Order setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        return this;
    }

    public String getMerchantReferringName() {
        return merchantReferringName;
    }

    public Order setMerchantReferringName(String merchantReferringName) {
        this.merchantReferringName = merchantReferringName;
        return this;
    }

    public String getCustom3dReturnUrl() {
        return custom3dReturnUrl;
    }

    public Order setCustom3dReturnUrl(String custom3dReturnUrl) {
        this.custom3dReturnUrl = custom3dReturnUrl;
        return this;
    }
}

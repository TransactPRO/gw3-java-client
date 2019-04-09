package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

import java.util.Map;

public interface OrderInterface<T> extends OperationInterface {

    default T setOrderMerchantTransactionId(String merchantTransactionId) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setMerchantTransactionId(merchantTransactionId);
        return (T) this.getOperation();
    }

    default T setOrderMerchantId(String merchantId) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setMerchantId(merchantId);
        return (T) this.getOperation();
    }

    default T setOrderId(String id) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setId(id);
        return (T) this.getOperation();
    }

    default T setOrderDescription(String description) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setDescription(description);
        return (T) this.getOperation();
    }

    default T setOrderMerchantUrl(String merchantUrl) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setMerchantUrl(merchantUrl);
        return (T) this.getOperation();
    }

    default T setOrderRecipientName(String recipientName) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setRecipientName(recipientName);
        return (T) this.getOperation();
    }

    default T setOrderMerchantReferringName(String merchantReferringName) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setMerchantReferringName(merchantReferringName);
        return (T) this.getOperation();
    }

    default T setOrderCustom3dReturnUrl(String custom3dReturnUrl) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setCustom3dReturnUrl(custom3dReturnUrl);
        return (T) this.getOperation();
    }

    default T setOrderMeta(Map<String, String> meta) {
        this.getOperation().getRequest().getData().getGeneral().getOrder().setMeta(meta);
        return (T) this.getOperation();
    }
}

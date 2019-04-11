package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

import java.util.Map;

public interface OrderInterface<T> extends OperationInterface {

    default T setOrderMerchantTransactionId(String merchantTransactionId) {
        getOperation().getRequest().getData().getGeneral().getOrder().setMerchantTransactionId(merchantTransactionId);
        return (T) getOperation();
    }

    default T setOrderMerchantId(String merchantId) {
        getOperation().getRequest().getData().getGeneral().getOrder().setMerchantId(merchantId);
        return (T) getOperation();
    }

    default T setOrderId(String id) {
        getOperation().getRequest().getData().getGeneral().getOrder().setId(id);
        return (T) getOperation();
    }

    default T setOrderDescription(String description) {
        getOperation().getRequest().getData().getGeneral().getOrder().setDescription(description);
        return (T) getOperation();
    }

    default T setOrderMerchantUrl(String merchantUrl) {
        getOperation().getRequest().getData().getGeneral().getOrder().setMerchantUrl(merchantUrl);
        return (T) getOperation();
    }

    default T setOrderRecipientName(String recipientName) {
        getOperation().getRequest().getData().getGeneral().getOrder().setRecipientName(recipientName);
        return (T) getOperation();
    }

    default T setOrderMerchantReferringName(String merchantReferringName) {
        getOperation().getRequest().getData().getGeneral().getOrder().setMerchantReferringName(merchantReferringName);
        return (T) getOperation();
    }

    default T setOrderCustom3dReturnUrl(String custom3dReturnUrl) {
        getOperation().getRequest().getData().getGeneral().getOrder().setCustom3dReturnUrl(custom3dReturnUrl);
        return (T) getOperation();
    }

    default T setOrderMeta(Map<String, String> meta) {
        getOperation().getRequest().getData().getGeneral().getOrder().setMeta(meta);
        return (T) getOperation();
    }
}

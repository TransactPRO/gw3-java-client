package com.gateway.operation.interfaces;

import com.gateway.operation.interfaces.base.RequestInterface;

public interface InfoInterface<T> extends RequestInterface {
    
    default T setCommandGatewayTransactionIds(String[] gatewayTransactionIds) {
        this.getOperation().getRequest().getData().getCommand().setGatewayTransactionIds(gatewayTransactionIds);
        return (T) this.getOperation();
    }

    default T setCommandMerchantTransactionIds(String[] merchantTransactionIds) {
        this.getOperation().getRequest().getData().getCommand().setMerchantTransactionIds(merchantTransactionIds);
        return (T) this.getOperation();
    }
}

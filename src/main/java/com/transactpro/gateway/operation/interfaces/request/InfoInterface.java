package com.transactpro.gateway.operation.interfaces.request;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface InfoInterface<T> extends OperationInterface {
    
    default T setCommandGatewayTransactionIds(String[] gatewayTransactionIds) {
        getOperation().getRequest().getData().getCommand().setGatewayTransactionIds(gatewayTransactionIds);
        return (T) getOperation();
    }

    default T setCommandMerchantTransactionIds(String[] merchantTransactionIds) {
        getOperation().getRequest().getData().getCommand().setMerchantTransactionIds(merchantTransactionIds);
        return (T) getOperation();
    }
}

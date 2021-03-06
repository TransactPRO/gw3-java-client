package com.github.transactpro.gateway.operation.interfaces.request.base;

import com.github.transactpro.gateway.operation.interfaces.OperationInterface;

public interface DataInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setDataPan(String pan) {
        getOperation().getRequest().getData().setPan(pan);
        return (T) getOperation();
    }

    @SuppressWarnings("unchecked")
    default T setDataCurrency(String currency) {
        getOperation().getRequest().getData().setCurrency(currency);
        return (T) getOperation();
    }

    @SuppressWarnings("unchecked")
    default T setDataTerminalMid(String terminalMid) {
        getOperation().getRequest().getData().setTerminalMid(terminalMid);
        return (T) getOperation();
    }

    @SuppressWarnings("unchecked")
    default T setDataGatewayTransactionId(String gatewayTransactionId) {
        getOperation().getRequest().getData().setGatewayTransactionId(gatewayTransactionId);
        return (T) getOperation();
    }
}

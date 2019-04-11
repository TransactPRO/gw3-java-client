package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface DataInterface<T> extends OperationInterface {

    default T setDataPan(String pan) {
        getOperation().getRequest().getData().setPan(pan);
        return (T) getOperation();
    }

    default T setDataCurrency(String currency) {
        getOperation().getRequest().getData().setCurrency(currency);
        return (T) getOperation();
    }

    default T setDataTerminalMid(String terminalMid) {
        getOperation().getRequest().getData().setTerminalMid(terminalMid);
        return (T) getOperation();
    }
}

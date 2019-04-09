package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface DataInterface<T> extends OperationInterface {

    default T setDataPan(String pan) {
        this.getOperation().getRequest().getData().setPan(pan);
        return (T) this.getOperation();
    }

    default T setDataCurrency(String currency) {
        this.getOperation().getRequest().getData().setCurrency(currency);
        return (T) this.getOperation();
    }

    default T setDataTerminalMid(String terminalMid) {
        this.getOperation().getRequest().getData().setTerminalMid(terminalMid);
        return (T) this.getOperation();
    }
}

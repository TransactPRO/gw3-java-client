package com.gateway.operation.interfaces.base;

public interface DataInterface<T> extends RequestInterface {

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

package com.gateway.operation.interfaces.base;

public interface CommandInterface<T> extends RequestInterface {

    default T setCommandGatewayTransactionId(String gatewayTransactionId) {
        this.getOperation().getRequest().getData().getCommand().setGatewayTransactionId(gatewayTransactionId);
        return (T) this.getOperation();
    }

    default T setCommandFormId(String formId) {
        this.getOperation().getRequest().getData().getCommand().setFormId(formId);
        return (T) this.getOperation();
    }

    default T setCommandTerminalMid(String terminalMid) {
        this.getOperation().getRequest().getData().getCommand().setTerminalMid(terminalMid);
        return (T) this.getOperation();
    }
}

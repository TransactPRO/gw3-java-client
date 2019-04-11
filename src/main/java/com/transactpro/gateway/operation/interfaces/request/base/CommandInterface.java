package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface CommandInterface<T> extends OperationInterface {

    default T setCommandGatewayTransactionId(String gatewayTransactionId) {
        getOperation().getRequest().getData().getCommand().setGatewayTransactionId(gatewayTransactionId);
        return (T) getOperation();
    }

    default T setCommandFormId(String formId) {
        getOperation().getRequest().getData().getCommand().setFormId(formId);
        return (T) getOperation();
    }

    default T setCommandTerminalMid(String terminalMid) {
        getOperation().getRequest().getData().getCommand().setTerminalMid(terminalMid);
        return (T) getOperation();
    }
}

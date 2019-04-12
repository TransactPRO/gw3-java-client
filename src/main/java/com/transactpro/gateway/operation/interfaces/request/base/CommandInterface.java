package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.Command;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface CommandInterface<T> extends OperationInterface {

    default T setCommand(Command command) {
        getOperation().getRequest().getData().setCommand(command);
        return (T) getOperation();
    }

    default Command getCommand() {
        return getOperation().getRequest().getData().getCommand();
    }
}

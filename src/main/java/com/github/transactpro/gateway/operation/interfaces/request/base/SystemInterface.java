package com.github.transactpro.gateway.operation.interfaces.request.base;

import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.operation.interfaces.OperationInterface;

public interface SystemInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setSystem(System system) {
        getOperation().getRequest().getData().setSystem(system);
        return (T) getOperation();
    }

    default System getSystem() {
        return getOperation().getRequest().getData().getSystem();
    }
}

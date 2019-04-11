package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface SystemInterface<T> extends OperationInterface {

    default T setSystemUserIp(String ip) {
        getOperation().getRequest().getData().getSystem().setUserIp(ip);
        return (T) getOperation();
    }

    default T setSystemForwardedFor(String xForwardedFor) {
        getOperation().getRequest().getData().getSystem().setXForwardedFor(xForwardedFor);
        return (T) getOperation();
    }
}

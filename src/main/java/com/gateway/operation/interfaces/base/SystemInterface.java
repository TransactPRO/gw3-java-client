package com.gateway.operation.interfaces.base;

public interface SystemInterface<T> extends RequestInterface {

    default T setSystemUserIp(String ip) {
        this.getOperation().getRequest().getData().getSystem().setUserIp(ip);
        return (T) this.getOperation();
    }

    default T setSystemForwardedFor(String xForwardedFor) {
        this.getOperation().getRequest().getData().getSystem().setXForwardedFor(xForwardedFor);
        return (T) this.getOperation();
    }
}

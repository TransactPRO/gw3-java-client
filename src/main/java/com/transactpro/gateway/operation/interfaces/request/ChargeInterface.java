package com.transactpro.gateway.operation.interfaces.request;

import com.transactpro.gateway.operation.interfaces.request.base.CommandInterface;
import com.transactpro.gateway.operation.interfaces.request.base.MoneyInterface;
import com.transactpro.gateway.operation.interfaces.request.base.OrderInterface;

public interface ChargeInterface<T> extends MoneyInterface<T>, CommandInterface<T>, OrderInterface<T> {
}

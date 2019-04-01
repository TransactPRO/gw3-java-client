package com.gateway.operation.interfaces;

import com.gateway.operation.interfaces.base.CommandInterface;
import com.gateway.operation.interfaces.base.MoneyInterface;
import com.gateway.operation.interfaces.base.OrderInterface;

public interface ChargeInterface<T> extends MoneyInterface<T>, CommandInterface<T>, OrderInterface<T> {
}

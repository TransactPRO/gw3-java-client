package com.github.transactpro.gateway.operation.interfaces.request;

import com.github.transactpro.gateway.operation.interfaces.request.base.*;

public interface TransactionInterface<T> extends CommandInterface<T>,
        CustomerInterface<T>,
        OrderInterface<T>,
        PaymentMethodInterface<T>,
        MoneyInterface<T>,
        SystemInterface<T> {
}

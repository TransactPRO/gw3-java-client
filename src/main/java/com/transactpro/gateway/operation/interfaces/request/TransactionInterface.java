package com.transactpro.gateway.operation.interfaces.request;

import com.transactpro.gateway.operation.interfaces.request.base.*;

public interface TransactionInterface<T> extends CustomerInterface<T>,
        OrderInterface<T>,
        PaymentMethodInterface<T>,
        MoneyInterface<T>,
        SystemInterface<T> {
}

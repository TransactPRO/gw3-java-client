package com.gateway.operation.interfaces;

import com.gateway.operation.interfaces.base.*;

public interface Transaction<T> extends CustomerInterface<T>,
        OrderInterface<T>,
        PaymentMethodInterface<T>,
        MoneyInterface<T>,
        SystemInterface<T> {
}

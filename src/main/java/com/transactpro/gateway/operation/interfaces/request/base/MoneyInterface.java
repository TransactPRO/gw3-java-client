package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.Money;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface MoneyInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setMoney(Money money) {
        getOperation().getRequest().getData().setMoney(money);
        return (T) getOperation();
    }

    default Money getMoney() {
        return getOperation().getRequest().getData().getMoney();
    }
}

package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface MoneyInterface<T> extends OperationInterface {

    default T setMoneyAmount(Integer amount) {
        getOperation().getRequest().getData().getMoney().setAmount(amount);
        return (T) getOperation();
    }

    default T setMoneyCurrency(String currency) {
        getOperation().getRequest().getData().getMoney().setCurrency(currency);
        return (T) getOperation();
    }
}

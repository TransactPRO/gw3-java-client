package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface MoneyInterface<T> extends OperationInterface {

    default T setMoneyAmount(Integer amount) {
        this.getOperation().getRequest().getData().getMoney().setAmount(amount);
        return (T) this.getOperation();
    }

    default T setMoneyCurrency(String currency) {
        this.getOperation().getRequest().getData().getMoney().setCurrency(currency);
        return (T) this.getOperation();
    }
}

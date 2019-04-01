package com.gateway.operation.interfaces.base;

public interface MoneyInterface<T> extends RequestInterface {

    default T setMoneyAmount(Integer amount) {
        this.getOperation().getRequest().getData().getMoney().setAmount(amount);
        return (T) this.getOperation();
    }

    default T setMoneyCurrency(String currency) {
        this.getOperation().getRequest().getData().getMoney().setCurrency(currency);
        return (T) this.getOperation();
    }
}

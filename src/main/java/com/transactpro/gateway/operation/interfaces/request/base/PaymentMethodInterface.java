package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface PaymentMethodInterface<T> extends OperationInterface {

    default T setPaymentMethodPan(String pan) {
        this.getOperation().getRequest().getData().getPaymentMethod().setPan(pan);
        return (T) this.getOperation();
    }

    default T setPaymentMethodExpMmYy(String exp) {
        this.getOperation().getRequest().getData().getPaymentMethod().setExpMmYy(exp);
        return (T) this.getOperation();
    }

    default T setPaymentMethodCvv(String cvv) {
        this.getOperation().getRequest().getData().getPaymentMethod().setCvv(cvv);
        return (T) this.getOperation();
    }

    default T setPaymentMethodCardholderName(String cardholderName) {
        this.getOperation().getRequest().getData().getPaymentMethod().setCardholderName(cardholderName);
        return (T) this.getOperation();
    }
}

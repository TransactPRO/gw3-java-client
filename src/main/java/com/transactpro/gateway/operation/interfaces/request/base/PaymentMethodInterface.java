package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface PaymentMethodInterface<T> extends OperationInterface {

    default T setPaymentMethodPan(String pan) {
        getOperation().getRequest().getData().getPaymentMethod().setPan(pan);
        return (T) getOperation();
    }

    default T setPaymentMethodExpMmYy(String exp) {
        getOperation().getRequest().getData().getPaymentMethod().setExpMmYy(exp);
        return (T) getOperation();
    }

    default T setPaymentMethodCvv(String cvv) {
        getOperation().getRequest().getData().getPaymentMethod().setCvv(cvv);
        return (T) getOperation();
    }

    default T setPaymentMethodCardholderName(String cardholderName) {
        getOperation().getRequest().getData().getPaymentMethod().setCardholderName(cardholderName);
        return (T) getOperation();
    }
}

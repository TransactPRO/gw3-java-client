package com.github.transactpro.gateway.operation.interfaces.request.base;

import com.github.transactpro.gateway.model.request.data.PaymentMethod;
import com.github.transactpro.gateway.operation.interfaces.OperationInterface;

public interface PaymentMethodInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setPayment(PaymentMethod paymentMethod) {
        getOperation().getRequest().getData().setPaymentMethod(paymentMethod);
        return (T) getOperation();
    }

    default PaymentMethod getPaymentMethod() {
        return getOperation().getRequest().getData().getPaymentMethod();
    }
}

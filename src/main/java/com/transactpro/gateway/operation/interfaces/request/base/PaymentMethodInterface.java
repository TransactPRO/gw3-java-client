package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.PaymentMethod;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface PaymentMethodInterface<T> extends OperationInterface {

    default T setPayment(PaymentMethod paymentMethod) {
        getOperation().getRequest().getData().setPaymentMethod(paymentMethod);
        return (T) getOperation();
    }

    default PaymentMethod getPaymentMethod() {
        return getOperation().getRequest().getData().getPaymentMethod();
    }
}

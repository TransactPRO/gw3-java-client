package com.github.transactpro.gateway.operation.interfaces.request.base;

import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.operation.interfaces.OperationInterface;

public interface OrderInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setOrder(Order order) {
        getOperation().getRequest().getData().getGeneral().setOrder(order);
        return (T) getOperation();
    }

    default Order getOrder() {
        return getOperation().getRequest().getData().getGeneral().getOrder();
    }
}

package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.general.Order;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface OrderInterface<T> extends OperationInterface {

    default T setOrder(Order order) {
        getOperation().getRequest().getData().getGeneral().setOrder(order);
        return (T) getOperation();
    }

    default Order getOrder() {
        return getOperation().getRequest().getData().getGeneral().getOrder();
    }
}

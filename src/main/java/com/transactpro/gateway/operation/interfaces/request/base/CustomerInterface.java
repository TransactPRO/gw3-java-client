package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.general.Customer;
import com.transactpro.gateway.model.request.data.general.customer.Address;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface CustomerInterface<T> extends OperationInterface {

    @SuppressWarnings("unchecked")
    default T setCustomer(Customer customer) {
        getOperation().getRequest().getData().getGeneral().setCustomer(customer);
        return (T) getOperation();
    }

    default Customer getCustomer() {
        return getOperation().getRequest().getData().getGeneral().getCustomer();
    }

    @SuppressWarnings("unchecked")
    default T setCustomerBillingAddress(Address address) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setBillingAddress(address);
        return (T) getOperation();
    }

    default Address getCustomerBillingAddress() {
        return getOperation().getRequest().getData().getGeneral().getCustomer().getBillingAddress();
    }

    @SuppressWarnings("unchecked")
    default T setCustomerShippingAddress(Address address) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setShippingAddress(address);
        return (T) getOperation();
    }

    default Address getCustomerShippingAddress() {
        return getOperation().getRequest().getData().getGeneral().getCustomer().getShippingAddress();
    }
}

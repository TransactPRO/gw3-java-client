package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.general.customer.Address;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface CustomerInterface<T> extends OperationInterface {

    default T setCustomerEmail(String email) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setEmail(email);
        return (T) getOperation();
    }

    default T setCustomerBirthDate(String birthDate) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setBirthDate(birthDate);
        return (T) getOperation();
    }

    default T setCustomerPhone(String phone) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setPhone(phone);
        return (T) getOperation();
    }

    default T setCustomerBillingAddress(Address address) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setBillingAddress(address);
        return (T) getOperation();
    }

    default T setCustomerShippingAddress(Address address) {
        getOperation().getRequest().getData().getGeneral().getCustomer().setShippingAddress(address);
        return (T) getOperation();
    }
}

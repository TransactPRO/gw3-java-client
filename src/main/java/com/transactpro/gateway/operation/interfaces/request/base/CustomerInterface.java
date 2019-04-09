package com.transactpro.gateway.operation.interfaces.request.base;

import com.transactpro.gateway.model.request.data.general.customer.Address;
import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface CustomerInterface<T> extends OperationInterface {

    default T setCustomerEmail(String email) {
        this.getOperation().getRequest().getData().getGeneral().getCustomer().setEmail(email);
        return (T) this.getOperation();
    }

    default T setCustomerBirthDate(String birthDate) {
        this.getOperation().getRequest().getData().getGeneral().getCustomer().setBirthDate(birthDate);
        return (T) this.getOperation();
    }

    default T setCustomerPhone(String phone) {
        this.getOperation().getRequest().getData().getGeneral().getCustomer().setPhone(phone);
        return (T) this.getOperation();
    }

    default T setCustomerBillingAddress(Address address) {
        this.getOperation().getRequest().getData().getGeneral().getCustomer().setBillingAddress(address);
        return (T) this.getOperation();
    }

    default T setCustomerShippingAddress(Address address) {
        this.getOperation().getRequest().getData().getGeneral().getCustomer().setShippingAddress(address);
        return (T) this.getOperation();
    }
}

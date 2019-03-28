package com.gateway.model.request.data.general;

import com.gateway.model.request.data.general.customer.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String email;
    private String birthDate;
    private String phone;
    private Address billingAddress;
    private Address shippingAddress;
}

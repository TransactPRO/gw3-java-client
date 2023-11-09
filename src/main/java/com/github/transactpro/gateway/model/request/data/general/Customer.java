package com.github.transactpro.gateway.model.request.data.general;

import com.github.transactpro.gateway.model.request.data.general.customer.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Customer {

    @Email(groups = {Default.class})
    private String email;
    private String birthDate;
    private String phone;
    @Valid
    private Address billingAddress;
    @Valid
    private Address shippingAddress;
}

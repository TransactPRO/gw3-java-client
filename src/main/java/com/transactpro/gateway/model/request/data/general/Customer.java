package com.transactpro.gateway.model.request.data.general;

import com.transactpro.gateway.model.request.data.general.customer.Address;
import com.transactpro.gateway.validation.base.CustomerBirthDateGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Getter
@Setter
@Accessors(chain = true)
public class Customer {

    @Email(groups = {Default.class})
    private String email;
    @NotNull(groups = {CustomerBirthDateGroup.class})
    private String birthDate;
    private String phone;
    @Valid
    private Address billingAddress;
    @Valid
    private Address shippingAddress;
}

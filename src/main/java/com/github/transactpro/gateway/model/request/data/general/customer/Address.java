package com.github.transactpro.gateway.model.request.data.general.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Address {

    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String zip;
}

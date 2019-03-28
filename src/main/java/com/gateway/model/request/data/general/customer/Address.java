package com.gateway.model.request.data.general.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String zip;
}

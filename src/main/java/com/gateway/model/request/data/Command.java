package com.gateway.model.request.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Command {

    private String paymentMethodType;
    private String paymentMethodDataSource;
}

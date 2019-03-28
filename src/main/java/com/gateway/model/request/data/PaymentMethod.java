package com.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethod {
    private String pan;
    private String expMmYy;
    private String cvv;
    private String cardholderName;
}

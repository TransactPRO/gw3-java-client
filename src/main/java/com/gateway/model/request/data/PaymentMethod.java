package com.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentMethod {
    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String pan;
    private String expMmYy;
    private String cvv;
    private String cardholderName;
}

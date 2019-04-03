package com.gateway.model.request.data;

import com.gateway.validation.CreditGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentMethod {
    @CreditCardNumber(ignoreNonDigitCharacters = true)
    @NotNull(groups = CreditGroup.class)
    private String pan;
    @NotNull(groups = CreditGroup.class)
    private String expMmYy;
    private String cvv;
    private String cardholderName;
}

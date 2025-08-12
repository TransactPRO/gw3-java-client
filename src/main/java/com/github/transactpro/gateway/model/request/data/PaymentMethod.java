package com.github.transactpro.gateway.model.request.data;

import com.github.transactpro.gateway.model.request.data.payment.ExternalMPIData;
import com.github.transactpro.gateway.model.request.data.payment.ExternalTokenData;
import jakarta.validation.Valid;
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
    private String token;

    @Valid
    private ExternalMPIData externalMpiData;

    @Valid
    private ExternalTokenData externalTokenData;
}

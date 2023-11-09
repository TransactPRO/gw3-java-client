package com.github.transactpro.gateway.model.request.data;

import com.github.transactpro.gateway.model.request.data.payment.ExternalMPIData;
import com.github.transactpro.gateway.validation.base.PaymentMethodPanExpGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentMethod {

    @CreditCardNumber(ignoreNonDigitCharacters = true)
    @NotNull(groups = PaymentMethodPanExpGroup.class)
    private String pan;
    @NotNull(groups = PaymentMethodPanExpGroup.class)
    private String expMmYy;
    private String cvv;
    private String cardholderName;

    @Valid
    private ExternalMPIData externalMpiData;
}

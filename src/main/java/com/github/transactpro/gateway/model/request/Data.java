package com.github.transactpro.gateway.model.request;

import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.*;
import com.github.transactpro.gateway.validation.base.DataGroup;
import com.github.transactpro.gateway.validation.base.GwTransactionIdGroup;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@Accessors(chain = true)
public class Data {

    @Valid
    @SerializedName("command-data")
    private Command command;
    @Valid
    @SerializedName("general-data")
    private General general;
    @Valid
    @SerializedName("payment-method-data")
    private PaymentMethod paymentMethod;
    @Valid
    @SerializedName("money-data")
    private Money money;
    @Valid
    private System system;

    @CreditCardNumber(ignoreNonDigitCharacters = true)
    @NotNull(groups = {DataGroup.class})
    private String pan;
    @NotNull(groups = {DataGroup.class})
    private String currency;
    @NotNull(groups = {DataGroup.class})
    private String terminalMid;

    @NotNull(groups = {GwTransactionIdGroup.class})
    private String gatewayTransactionId;

    public Data() {
        command = new Command();
        general = new General();
        paymentMethod = new PaymentMethod();
        money = new Money();
        system = new System();
    }
}

package com.transactpro.gateway.model.request;

import com.transactpro.gateway.model.request.data.*;
import com.transactpro.gateway.model.request.data.System;
import com.transactpro.gateway.validation.base.DataGroup;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    public Data() {
        command = new Command();
        general = new General();
        paymentMethod = new PaymentMethod();
        money = new Money();
        system = new System();
    }
}

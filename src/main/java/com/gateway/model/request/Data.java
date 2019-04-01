package com.gateway.model.request;

import com.gateway.model.request.data.Command;
import com.gateway.model.request.data.General;
import com.gateway.model.request.data.Money;
import com.gateway.model.request.data.PaymentMethod;
import com.gateway.model.request.data.System;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.Valid;

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
    private String pan;
    private String currency;
    private String terminalMid;

    public Data() {
        command = new Command();
        general = new General();
        paymentMethod = new PaymentMethod();
        money = new Money();
        system = new System();
    }
}

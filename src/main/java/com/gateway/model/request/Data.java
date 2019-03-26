package com.gateway.model.request;

import com.gateway.model.request.data.Command;
import com.gateway.model.request.data.General;
import com.gateway.model.request.data.Money;
import com.gateway.model.request.data.PaymentMethod;
import com.gateway.model.request.data.System;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("command-data")
    private Command command;
    @SerializedName("general-data")
    private General general;
    @SerializedName("payment-method-data")
    private PaymentMethod paymentMethod;
    @SerializedName("money-data")
    private Money money;
    private System system;

    public Command getCommand() {
        return command;
    }

    public Data setCommand(Command command) {
        this.command = command;
        return this;
    }

    public General getGeneral() {
        return general;
    }

    public Data setGeneral(General general) {
        this.general = general;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Data setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Money getMoney() {
        return money;
    }

    public Data setMoney(Money money) {
        this.money = money;
        return this;
    }

    public System getSystem() {
        return system;
    }

    public Data setSystem(System system) {
        this.system = system;
        return this;
    }
}

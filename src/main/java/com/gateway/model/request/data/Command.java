package com.gateway.model.request.data;


public class Command {

    private String paymentMethodType;
    private String paymentMethodDataSource;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public Command setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
        return this;
    }

    public String getPaymentMethodDataSource() {
        return paymentMethodDataSource;
    }

    public Command setPaymentMethodDataSource(String paymentMethodDataSource) {
        this.paymentMethodDataSource = paymentMethodDataSource;
        return this;
    }
}

package com.gateway.model.request.data;

import com.gateway.model.request.data.general.Customer;
import com.gateway.model.request.data.general.Order;
import com.google.gson.annotations.SerializedName;

public class General {
    @SerializedName("customer-data")
    private Customer customer;
    @SerializedName("order-data")
    private Order order;

    public Customer getCustomer() {
        return customer;
    }

    public General setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public General setOrder(Order order) {
        this.order = order;
        return this;
    }
}

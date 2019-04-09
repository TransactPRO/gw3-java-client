package com.transactpro.gateway.model.request.data;

import com.transactpro.gateway.model.request.data.general.Customer;
import com.transactpro.gateway.model.request.data.general.Order;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;

@Getter
@Setter
@Accessors(chain = true)
public class General {
    @SerializedName("customer-data")
    @Valid
    private Customer customer;
    @SerializedName("order-data")
    @Valid
    private Order order;

    public General() {
        this.customer = new Customer();
        this.order = new Order();
    }
}

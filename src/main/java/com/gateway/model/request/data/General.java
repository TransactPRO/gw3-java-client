package com.gateway.model.request.data;

import com.gateway.model.request.data.general.Customer;
import com.gateway.model.request.data.general.Order;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class General {
    @SerializedName("customer-data")
    private Customer customer;
    @SerializedName("order-data")
    private Order order;
}

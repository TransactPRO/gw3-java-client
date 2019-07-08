package com.github.transactpro.gateway.adapters;

import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PaymentMethodDataSourceSerializer implements JsonSerializer<PaymentMethodDataSource> {
    @Override
    public JsonElement serialize(PaymentMethodDataSource paymentMethodDataSource, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(paymentMethodDataSource.getValue());
    }
}

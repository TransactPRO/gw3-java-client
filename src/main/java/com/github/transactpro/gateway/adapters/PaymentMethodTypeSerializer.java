package com.github.transactpro.gateway.adapters;

import com.github.transactpro.gateway.model.request.data.command.PaymentMethodType;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PaymentMethodTypeSerializer implements JsonSerializer<PaymentMethodType> {
    @Override
    public JsonElement serialize(PaymentMethodType paymentMethodType, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(paymentMethodType.getValue());
    }
}

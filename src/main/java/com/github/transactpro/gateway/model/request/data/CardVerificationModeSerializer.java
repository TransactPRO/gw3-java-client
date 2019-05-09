package com.github.transactpro.gateway.model.request.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CardVerificationModeSerializer implements JsonSerializer<CardVerificationMode> {
    @Override
    public JsonElement serialize(CardVerificationMode cardVerificationMode, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(cardVerificationMode.getValue());
    }
}

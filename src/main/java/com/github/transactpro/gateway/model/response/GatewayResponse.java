package com.github.transactpro.gateway.model.response;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GatewayResponse {
    private static class UTCDateAdapter implements JsonDeserializer<Date> {
        private final DateFormat dateFormat;

        public UTCDateAdapter() {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        @Override
        public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
            try {
                return dateFormat.parse(jsonElement.getAsString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new UTCDateAdapter())
                .create();

        return gson.fromJson(json, classOfT);
    }
}

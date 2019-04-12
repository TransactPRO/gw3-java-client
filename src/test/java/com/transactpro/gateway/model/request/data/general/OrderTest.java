package com.transactpro.gateway.model.request.data.general;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    class SomeRandomClass {
        String fffRRR = "hhh-lll";
        int gggAAA = 999954;
        String[] cccSSS = {"kkkLLL", "lllKKK"};
    }

    @Test
    protected void meta() {

        Order order = new Order();
        order.setId("333")
                .setMeta(new SomeRandomClass());

        Gson jsonParser = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();

        String jsonString = jsonParser.toJson(order);
        assertTrue(jsonString.contains("\"fff-r-r-r\":\"hhh-lll\""));
        assertTrue(jsonString.contains("ggg-a-a-a\":999954"));
        assertTrue(jsonString.contains("ccc-s-s-s\":[\"kkkLLL\",\"lllKKK\"]"));
    }

}
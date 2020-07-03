package com.github.transactpro.gateway.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTest {
    @Test
    void getRequestPayload() {
        Operation<?> getOperation = new Operation<Object>() {{ method = "GET"; }};
        assertEquals("", getOperation.getRequestPayload());

        Operation<?> postOperation = new Operation<Object>() {{ method = "POST"; }};
        assertTrue(postOperation.getRequestPayload().contains("data"));
    }
}
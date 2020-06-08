package com.github.transactpro.gateway.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseTest {
    @Test
    void isSuccessful() {
        assertTrue(new Response<>(Object.class, 200, null, null).isSuccessful());
        assertTrue(new Response<>(Object.class, 201, null, null).isSuccessful());
        assertTrue(new Response<>(Object.class, 302, null, null).isSuccessful());
        assertTrue(new Response<>(Object.class, 402, null, null).isSuccessful());

        assertFalse(new Response<>(Object.class, 404, null, null).isSuccessful());
        assertFalse(new Response<>(Object.class, 401, null, null).isSuccessful());
        assertFalse(new Response<>(Object.class, 403, null, null).isSuccessful());
        assertFalse(new Response<>(Object.class, 500, null, null).isSuccessful());
    }
}

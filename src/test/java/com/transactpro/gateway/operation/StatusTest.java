package com.transactpro.gateway.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void getByCode() {
        Status status = Status.getByCode(1);
        assertEquals(Status.INIT, status);
        assertEquals(Status.INIT.getCode(), status.getCode());
        assertEquals(Status.INIT.getMessage(), status.getMessage());

        assertNull(Status.getByCode(999));
    }
}
package com.gateway.operation.transaction;

import com.gateway.model.Request;
import com.gateway.validation.ToPersonGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class B2PTest {

    private B2P operation;

    @BeforeEach
    void setUp() {
        operation = new B2P();
    }

    @AfterEach
    void tearDown() {
        operation = null;
    }

    @Test
    void getRequestUri() {
        assertTrue(operation.getRequestUri().startsWith("/"));
    }

    @Test
    void getValidationGroups() {
        assertEquals(ToPersonGroup.class, operation.getValidationGroups());
    }

    @Test
    void validB2P() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        operation.setCustomerBirthDate("20.20.00")
                .setMoneyAmount(100)
                .setMoneyCurrency("EUR")
                .setOrderRecipientName("John Smith")
                .setPaymentMethodPan("4111-1111-1111-1111")
                .setPaymentMethodExpMmYy("2020");

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void invalidB2P() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertFalse(constraintViolations.isEmpty());
    }
}
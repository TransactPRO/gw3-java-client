package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.model.request.data.Money;
import com.transactpro.gateway.validation.TransactionGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MotoDmsTest {

    private MotoDms operation;

    @BeforeEach
    void setUp() {
        operation = new MotoDms();
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
        assertEquals(TransactionGroup.class, operation.getValidationGroups());
    }

    @Test
    void validOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Money money = new Money()
                .setAmount(100)
                .setCurrency("EUR");

        operation.setMoney(money);

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void invalidOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertFalse(constraintViolations.isEmpty());
    }
}
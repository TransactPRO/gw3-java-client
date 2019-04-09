package com.transactpro.gateway.operation.transaction;

import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.model.request.data.general.customer.Address;
import com.transactpro.gateway.validation.TransactionGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DmsHoldTest {

    private DmsHold operation;

    @BeforeEach
    void setUp() {
        operation = new DmsHold();
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

        Address address = new Address()
                .setCity("Chalon-sur-Saône")
                .setCountry("FR")
                .setFlat("10")
                .setHouse("10")
                .setState("France")
                .setStreet("Rue Garibaldi")
                .setZip("71100");

        Map<String, String> meta = new HashMap<>();
        meta.put("key", "value");

        operation.setOrderMerchantUrl("https://domain.com")
                .setOrderDescription("Cheers")
                .setOrderMerchantTransactionId(UUID.randomUUID().toString())
                .setMoneyAmount(1000)
                .setMoneyCurrency("EUR")
                .setCustomerBillingAddress(address)
                .setCustomerShippingAddress(address)
                .setCustomerPhone("25252525")
                .setCustomerEmail("test@test.domain")
                .setCustomerBirthDate("29/02")
                .setSystemUserIp("127.0.0.1")
                .setSystemForwardedFor("127.0.0.1")
                .setOrderId("OrderId")
                .setOrderMerchantId("test id")
                .setOrderMeta(meta);

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
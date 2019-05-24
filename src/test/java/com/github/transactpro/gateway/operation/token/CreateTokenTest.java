package com.github.transactpro.gateway.operation.token;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.PaymentMethod;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.general.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateTokenTest {

    private CreateToken operation;

    @BeforeEach
    void setUp() {
        operation = new CreateToken();
    }

    @AfterEach
    void tearDown() {
        operation = null;
    }

    @Test
    void getRequestUri() {
        assertTrue(operation.getRequestUri().startsWith("/token/create"));
    }

    @Test
    void getValidationGroups() {
        assertEquals(Default.class, operation.getValidationGroups());
    }

    @Test
    void validOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Money money = new Money()
                .setCurrency("EUR");

        Order order = new Order()
                .setDescription("Payment")
                .setId("Order ID")
                .setMerchantId("customerId")
                .setMerchantUrl("https://domain.com/custom-url/")
                .setMerchantTransactionId("");

        System system = new System()
                .setUserIp("127.0.0.1");

        PaymentMethod paymentMethod = new PaymentMethod()
                .setCardholderName("John Doe")
                .setPan("4111111111111111")
                .setExpMmYy("09/31");

        operation
                .setPayment(paymentMethod)
                .setMoney(money)
                .setOrder(order)
                .setSystem(system);

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }
}
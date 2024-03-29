package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.general.customer.Address;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.model.response.constants.Status;
import com.github.transactpro.gateway.validation.TransactionGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InitRecurrentSmsTest {

    private InitRecurrentSms operation;

    @BeforeEach
    void setUp() {
        operation = new InitRecurrentSms();
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

        Money money = new Money()
                .setAmount(100)
                .setCurrency("EUR");

        operation.setMoney(money)
                .setCustomerBillingAddress(address)
                .setCustomerShippingAddress(address);

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

    @Test
    void parsePaymentResponseSuccessfulRedirect() throws MalformedURLException {
        String body = "{\"acquirer-details\": {},\"error\": {},\"gw\": {\"gateway-transaction-id\": \"965ffd17-1874-48d0-89f3-f2c2f06bf749\"," +
                "\"redirect-url\": \"https://api.url/a4345be5b8a1af9773b8b0642b49ff26\",\"status-code\": 30,\"status-text\": \"INSIDE FORM URL SENT\"}}";

        Response<PaymentResponse> response = operation.createResponse(200, body, null);
        PaymentResponse parsedResponse = response.parse();

        assertNotNull(parsedResponse.getGw());
        assertEquals("965ffd17-1874-48d0-89f3-f2c2f06bf749", parsedResponse.getGw().getGatewayTransactionId());
        assertEquals(new URL("https://api.url/a4345be5b8a1af9773b8b0642b49ff26"), parsedResponse.getGw().getRedirectUrl());
        assertEquals(Status.CARD_FORM_URL_SENT, parsedResponse.getGw().getStatusCode());
        assertEquals("INSIDE FORM URL SENT", parsedResponse.getGw().getStatusText());
    }
}
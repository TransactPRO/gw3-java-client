package com.github.transactpro.gateway.operation.token;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.PaymentMethod;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.model.response.constants.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.net.MalformedURLException;
import java.net.URL;
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
package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.request.data.*;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.command.CardVerificationMode;
import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.github.transactpro.gateway.model.request.data.general.Customer;
import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.model.request.data.general.customer.Address;
import com.github.transactpro.gateway.validation.TransactionGroup;
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

class SmsTest {

    private Sms operation;

    @BeforeEach
    void setUp() {
        operation = new Sms();
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
    void validOperationAllFields() {
        Command command = new Command()
                .setCardVerification(CardVerificationMode.VERIFY)
                .setPaymentMethodDataSource(PaymentMethodDataSource.DATA_SOURCE_USE_MERCHANT_SAVED_CARDHOLDER_INITIATED)
                .setPaymentMethodDataToken("some-test-id");

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

        Map<String,String> meta = new HashMap<>();
        meta.put("test", "rest");

        Order order = new Order()
                .setCustomReturnUrl("https://another-domain.com")
                .setCustom3dReturnUrl("https://domain.com")
                .setDescription("Payment")
                .setId(UUID.randomUUID().toString())
                .setMerchantId(UUID.randomUUID().toString())
                .setMerchantReferringName("Test payment")
                .setMerchantTransactionId(UUID.randomUUID().toString())
                .setMerchantUrl("https://domain.com/custom-url/")
                .setMeta(meta)
                .setRecipientName("John Smith");

        Customer customer = new Customer()
                .setEmail("test@test.domain")
                .setBirthDate("01/00")
                .setPhone("0000000000")
                .setBillingAddress(address)
                .setShippingAddress(address);

        System system = new System()
                .setUserIp("127.0.0.1")
                .setXForwardedFor("127.0.0.1")
                .setBrowserAcceptHeader("application/json, text/javascript, */*; q=0.01")
                .setBrowserJavaEnabled(false)
                .setBrowserJavascriptEnabled(true)
                .setBrowserLanguage("en-US")
                .setBrowserColorDepth("24")
                .setBrowserScreenHeight("1080")
                .setBrowserScreenWidth("1920")
                .setBrowserTz("+300")
                .setBrowserUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");

        PaymentMethod paymentMethod = new PaymentMethod()
                .setCardholderName("John Smith")
                .setCvv("000")
                .setExpMmYy("12/18")
                .setPan("0000000000000000");

        operation.setCommand(command)
                .setCustomer(customer)
                .setMoney(money)
                .setOrder(order)
                .setPayment(paymentMethod)
                .setSystem(system);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

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
package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.request.data.Command;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.PaymentMethod;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.command.CardVerificationMode;
import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.github.transactpro.gateway.model.request.data.general.Customer;
import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.model.request.data.general.customer.Address;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.model.response.constants.ErrorCode;
import com.github.transactpro.gateway.model.response.constants.Status;
import com.github.transactpro.gateway.validation.TransactionGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
                .setRecipientName("John Smith")
                .setRecurringExpiry("20250131")
                .setRecurringFrequency("30");

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

    @Test
    void parsePaymentResponseSuccessfulAPI() {
        String body = "{\"acquirer-details\":{\"dynamic-descriptor\":\"test\",\"eci-sli\":\"648\",\"result-code\":\"000\",\"status-description\":\"Approved\"," +
                "\"status-text\":\"Approved\",\"terminal-mid\":\"5800978\",\"transaction-id\":\"1899493845214315\"},\"error\":{}," +
                "\"gw\":{\"gateway-transaction-id\":\"8a9bed66-8412-494f-9866-2c26b5ceee62\",\"merchant-transaction-id\":\"87d53472ba27fde33ec03e2f5ca6137a\"," +
                "\"status-code\":7,\"status-text\":\"SUCCESS\",\"original-gateway-transaction-id\":\"orig-aaa\",\"parent-gateway-transaction-id\":\"parent-aaa\"}," +
                "\"warnings\":[\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded for the account\"," +
                "\"Soon counters will be exceeded for the terminal group\",\"Soon counters will be exceeded for the terminal\"]}\n";

        Response<PaymentResponse> response = operation.createResponse(200, body, null);
        PaymentResponse parsedResponse = response.parse();

        assertNotNull(parsedResponse.getAcquirerDetails());
        assertEquals("test", parsedResponse.getAcquirerDetails().getDynamicDescriptor());
        assertEquals("648", parsedResponse.getAcquirerDetails().getEciSli());
        assertEquals("000", parsedResponse.getAcquirerDetails().getResultCode());
        assertEquals("Approved", parsedResponse.getAcquirerDetails().getStatusDescription());
        assertEquals("Approved", parsedResponse.getAcquirerDetails().getStatusText());
        assertEquals("5800978", parsedResponse.getAcquirerDetails().getTerminalMid());
        assertEquals("1899493845214315", parsedResponse.getAcquirerDetails().getTransactionId());

        assertNotNull(parsedResponse.getGw());
        assertEquals("8a9bed66-8412-494f-9866-2c26b5ceee62", parsedResponse.getGw().getGatewayTransactionId());
        assertEquals("87d53472ba27fde33ec03e2f5ca6137a", parsedResponse.getGw().getMerchantTransactionId());
        assertEquals("orig-aaa", parsedResponse.getGw().getOriginalGatewayTransactionId());
        assertEquals("parent-aaa", parsedResponse.getGw().getParentGatewayTransactionId());
        assertEquals(Status.SUCCESS, parsedResponse.getGw().getStatusCode());
        assertEquals("SUCCESS", parsedResponse.getGw().getStatusText());

        ArrayList<String> expectedWarnings = new ArrayList<String>() {{
            add("Soon counters will be exceeded for the merchant");
            add("Soon counters will be exceeded for the account");
            add("Soon counters will be exceeded for the terminal group");
            add("Soon counters will be exceeded for the terminal");
        }};
        assertEquals(expectedWarnings, parsedResponse.getWarnings());
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

    @Test
    void parsePaymentResponseError() {
        String body = "{\"acquirer-details\": {},\"error\": {\"code\": 1102,\"message\": \"Invalid pan number. Failed assertion that pan (false) == true\"}," +
                "\"gw\":{\"gateway-transaction-id\": \"33f17d34-3796-45e0-9bba-a771e9d3e504\",\"status-code\": 19,\"status-text\": \"BR VALIDATION FAILED\"}," +
                "\"warnings\": [\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded for the account\"]}";

        Response<PaymentResponse> response = operation.createResponse(200, body, null);
        PaymentResponse parsedResponse = response.parse();

        assertNotNull(parsedResponse.getError());
        assertEquals(ErrorCode.EEC_CC_BAD_NUMBER, parsedResponse.getError().getCodeAsEnum());
        assertEquals("Invalid pan number. Failed assertion that pan (false) == true", parsedResponse.getError().getMessage());

        assertNotNull(parsedResponse.getGw());
        assertEquals("33f17d34-3796-45e0-9bba-a771e9d3e504", parsedResponse.getGw().getGatewayTransactionId());
        assertEquals(Status.BR_VALIDATION_FAILED, parsedResponse.getGw().getStatusCode());
        assertEquals("BR VALIDATION FAILED", parsedResponse.getGw().getStatusText());

        ArrayList<String> expectedWarnings = new ArrayList<String>() {{
            add("Soon counters will be exceeded for the merchant");
            add("Soon counters will be exceeded for the account");
        }};
        assertEquals(expectedWarnings, parsedResponse.getWarnings());
    }
}
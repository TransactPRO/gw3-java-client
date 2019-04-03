package com.gateway.operation.transaction;

import com.gateway.model.request.data.general.customer.Address;
import com.gateway.validation.TransactionGroup;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SmsTest {

    @Test
    void getRequestUri() {
        Sms sms = new Sms();
        assertTrue(sms.getRequestUri().startsWith("/"));
    }

    @Test
    void createSmsTransaction() {
        Sms sms = new Sms();
        Address address = new Address()
                .setCity("Chalon-sur-Saône")
                .setCountry("FR")
                .setFlat("10")
                .setHouse("10")
                .setState("France")
                .setStreet("Rue Garibaldi")
                .setZip("71100");

        sms.setOrderMerchantUrl("https://domain.com")
                .setOrderDescription("Cheers")
                .setOrderMerchantTransactionId(UUID.randomUUID().toString())
                .setMoneyAmount(1000)
                .setMoneyCurrency("EUR")
                .setCustomerBillingAddress(address)
                .setCustomerShippingAddress(address)
                .setCustomerPhone("25252525")
                .setCustomerEmail("test@test.domain")
                .setCustomerBirthDate("29/02")
                .setSystemUserIp("127.0.0.1");
    }

    @Test
    void getValidationGroups() {
        Sms sms = new Sms();
        assertEquals(TransactionGroup.class, sms.getValidationGroups());
    }
}
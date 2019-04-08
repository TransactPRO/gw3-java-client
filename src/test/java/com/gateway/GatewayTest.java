package com.gateway;

import com.gateway.model.Request;
import com.gateway.operation.transaction.Sms;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.util.reflection.FieldSetter;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GatewayTest {

    private Gateway gw;

    @BeforeEach
    void setUp() {
        gw = new Gateway("dsfw3f34fsdf-GUID", "very,very_secret_key", "http://some-fake-url33-should-not-work.com/");
    }

    @AfterEach
    void tearDown() {
        gw = null;
    }

    @Test
    void processErrors() throws NoSuchFieldException {

        Sms sms = new Sms();
        assertThrows(ValidationException.class, () -> gw.process(sms));

        Validator validator = mock(Validator.class);

        HashSet hs = new HashSet<ConstraintViolation<Request>>();
        when(validator.validate(sms, sms.getValidationGroups())).thenReturn(hs);
        FieldSetter.setField(gw, gw.getClass().getDeclaredField("validator"), validator);

        assertThrows(IOException.class, () -> gw.process(sms));
    }

    @ParameterizedTest
    @MethodSource("processParameters")
    void process(String body, boolean success, String transactionId, String redirectUrl, String errorMessage) throws NoSuchFieldException, IOException {

        Sms sms = new Sms();
        Validator validator = mock(Validator.class);

        HashSet hs = new HashSet<ConstraintViolation<Request>>();
        when(validator.validate(sms, sms.getValidationGroups())).thenReturn(hs);

        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = mock(HttpEntity.class);

        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);

        FieldSetter.setField(gw, gw.getClass().getDeclaredField("validator"), validator);
        FieldSetter.setField(gw, gw.getClass().getDeclaredField("httpClient"), httpClient);
        gw.process(sms);

        assertEquals(success, sms.isSuccessful());
        assertEquals(transactionId, sms.getResponse().getGateway().getTransactionId());
        assertEquals(redirectUrl, sms.getResponse().getGateway().getRedirectUrl());
        assertEquals(errorMessage, sms.getError().getMessage());
    }

    private static Stream processParameters() {
        return Stream.of(
                Arguments.of(
                        "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"redirect-url\":\"https://success.url.com/\",\"status-code\":30,\"status-text\":\"INSIDE FORM URL SENT\"},\"warnings\":[]}",
                        true,
                        "51f5171b",
                        "https://success.url.com/",
                        null
                ),
                Arguments.of(
                        "{\"acquirer-details\":{},\"error\":{\"code\":1100,\"message\":\"Invalid input data provided. Failed assertion\"},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"status-code\":19,\"status-text\":\"VALIDATION FAILED\"},\"warnings\":[]}",
                        false,
                        "51f5171b",
                        null,
                        "Invalid input data provided. Failed assertion"
                )
        );
    }
}
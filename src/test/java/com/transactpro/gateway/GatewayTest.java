package com.transactpro.gateway;

import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.operation.Status;
import com.transactpro.gateway.operation.transaction.Sms;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
    void process(
            String body,
            boolean success,
            String transactionId,
            String redirectUrl,
            String errorMessage,
            Status status,
            Integer acquirerDetailsEciSLi,
            String acquirerTerminalId,
            String acquirerTransactionId,
            String acquirerResultCode,
            String acquirerStatusText,
            String acquirerStatusDescription,
            Integer errorCode,
            Integer gatewayStatusCode,
            String gatewayStatusText
    ) throws NoSuchFieldException, IOException {

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

        Assertions.assertEquals(success, sms.isSuccessful());
        Assertions.assertEquals(transactionId, sms.getGatewayTransactionId());
        Assertions.assertEquals(redirectUrl, sms.getGatewayRedirectUrl());
        Assertions.assertEquals(errorMessage, sms.getErrorMessage());
        Assertions.assertEquals(status, sms.status());
        Assertions.assertEquals(acquirerDetailsEciSLi, sms.getAcquirerDetailsEciSLi());
        Assertions.assertEquals(acquirerTerminalId, sms.getAcquirerDetailsTerminalId());
        Assertions.assertEquals(acquirerTransactionId, sms.getAcquirerDetailsTransactionId());
        Assertions.assertEquals(acquirerResultCode, sms.getAcquirerDetailsResultCode());
        Assertions.assertEquals(acquirerStatusText, sms.getAcquirerDetailsStatusText());
        Assertions.assertEquals(acquirerStatusDescription, sms.getAcquirerDetailsStatusDescription());
        Assertions.assertEquals(errorCode, sms.getErrorCode());
        Assertions.assertEquals(gatewayStatusCode, sms.getGatewayStatusCode());
        Assertions.assertEquals(gatewayStatusText, sms.getGatewayStatusText());

//        detailsEciSLi
//        terminalId
//        transactionId
//        resultCode
//        statusText
//        statusDescription
//        errorCode
//        statusCode
//        statusText

    }

    private static Stream processParameters() {
        return Stream.of(
                Arguments.of(
                        /* body */ "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"redirect-url\":\"https://success.url.com/\",\"status-code\":30,\"status-text\":\"INSIDE FORM URL SENT\"},\"warnings\":[]}",
                        /* is successful */ true,
                        /* transactionId */ "51f5171b",
                        /* redirectUrl */ "https://success.url.com/",
                        /* errorMessage */ null,
                        /* status enum */ Status.CARD_FORM_URL_SENT
                        /* acquirerDetailsEciSLi */
                        /* acquirerTerminalId */
                        /* acquirerTransactionId */
                        /* acquirerResultCode */
                        /* acquirerStatusText */
                        /* acquirerStatusDescription */
                        /* errorCode */
                        /* gatewayStatusCode */
                        /* gatewayStatusText */
                ),
                Arguments.of(
                        /* body */ "{\"acquirer-details\":{},\"error\":{\"code\":1100,\"message\":\"Invalid input data provided. Failed assertion\"},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"status-code\":19,\"status-text\":\"VALIDATION FAILED\"},\"warnings\":[]}",
                        /* is successful */ false,
                        /* transactionId */"51f5171b",
                        /* redirectUrl */ null,
                        /* errorMessage */ "Invalid input data provided. Failed assertion",
                        /* status enum */ Status.BR_VALIDATION_FAILED
                        /* acquirerDetailsEciSLi */
                        /* acquirerTerminalId */
                        /* acquirerTransactionId */
                        /* acquirerResultCode */
                        /* acquirerStatusText */
                        /* acquirerStatusDescription */
                        /* errorCode */
                        /* gatewayStatusCode */
                        /* gatewayStatusText */
                ),
                Arguments.of(
                        /* body */ "",
                        /* is successful */ false,
                        /* transactionId */ null,
                        /* redirectUrl */ null,
                        /* errorMessage */ null,
                        /* status enum */ null,
                        /* acquirerDetailsEciSLi */ null,
                        /* acquirerTerminalId */ null,
                        /* acquirerTransactionId */ null,
                        /* acquirerResultCode */ null,
                        /* acquirerStatusText */ null,
                        /* acquirerStatusDescription */ null,
                        /* errorCode */ null,
                        /* gatewayStatusCode */ null,
                        /* gatewayStatusText */ null
                )
        );
    }
}
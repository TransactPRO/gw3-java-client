package com.transactpro.gateway;

import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.operation.transaction.Sms;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GatewayTest {

    @Test
    void processErrors() throws NoSuchFieldException {

        Gateway gw = new Gateway("Adw2DAvvsessionId", "http://some-fake-url33-should-not-work.com/");
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
    void process(String body, Integer statusCode, String headerName, String headerValue) throws NoSuchFieldException, IOException {

        Gateway gw = new Gateway("dsfw3f34fsdf-GUID", "very,very_secret_key", "http://some-fake-url33-should-not-work.com/");
        Sms sms = new Sms();
        Validator validator = mock(Validator.class);

        HashSet hs = new HashSet<ConstraintViolation<Request>>();
        when(validator.validate(sms, sms.getValidationGroups())).thenReturn(hs);


        HttpEntity httpEntity = mock(HttpEntity.class);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        Header header = mock(Header.class);
        when(header.getName()).thenReturn(headerName);
        when(header.getValue()).thenReturn(headerValue);

        HttpResponse httpResponse = mock(HttpResponse.class, Mockito.RETURNS_DEEP_STUBS);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpResponse.getStatusLine().getStatusCode()).thenReturn(statusCode);
        when(httpResponse.getAllHeaders()).thenReturn(new Header[]{header});

        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);

        FieldSetter.setField(gw, gw.getClass().getDeclaredField("validator"), validator);
        FieldSetter.setField(gw, gw.getClass().getDeclaredField("httpClient"), httpClient);
        gw.process(sms);

        Assertions.assertEquals(body, sms.getResponse().getBody());
        Assertions.assertEquals(statusCode, sms.getResponse().getStatusCode());
        Assertions.assertEquals(Map.of(headerName, headerValue), sms.getResponse().getHeaders());
    }

    private static Stream processParameters() {
        return Stream.of(
                Arguments.of(
                        /* body */ "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"redirect-url\":\"https://success.url.com/\",\"status-code\":30,\"status-text\":\"INSIDE FORM URL SENT\"},\"warnings\":[]}",
                        /*status code*/ 200,
                        /*header name*/ "test",
                        /*header value*/ "rest"
                ),
                Arguments.of(
                        /* body */ "{\"acquirer-details\":{},\"error\":{\"code\":1100,\"message\":\"Invalid input data provided. Failed assertion\"},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"status-code\":19,\"status-text\":\"VALIDATION FAILED\"},\"warnings\":[]}",
                        /*status code*/ 400,
                        /*header name*/ "rest",
                        /*header value*/ "test"
                ),
                Arguments.of(
                        /* body */ "",
                        /*status code*/ 0,
                        /*header name*/ "",
                        /*header value*/ ""
                )
        );
    }
}
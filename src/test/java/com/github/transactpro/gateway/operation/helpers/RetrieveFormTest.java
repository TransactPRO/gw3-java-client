package com.github.transactpro.gateway.operation.helpers;

import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.response.GatewayResponse;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RetrieveFormTest {
    @ParameterizedTest
    @MethodSource("wrongResponses")
    void objectConstructorErrors(String rawPaymentResponse) {
        PaymentResponse response = GatewayResponse.fromJson(rawPaymentResponse, PaymentResponse.class);
        Exception exception = assertThrows(ResponseException.class, () -> new RetrieveForm(response));

        String expectedMessage = "Response doesn't contain link to an HTML form";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<Arguments> wrongResponses() {
        return Stream.of(
                Arguments.of("null"),
                Arguments.of("{}"),
                Arguments.of("{\"gw\":{}}")
        );
    }

    @ParameterizedTest
    @MethodSource("wrongUrls")
    void stringConstructorErrors(String url) {
        Exception exception = assertThrows(ResponseException.class, () -> new RetrieveForm(url));

        String expectedMessage = "URL cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<Arguments> wrongUrls() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of("")
        );
    }

    @Test
    void objectConstructorSuccessful() {
        String rawResponse = "{\"gw\":{\"redirect-url\":\"https://api.url/a4345be5b8a1af9773b8b0642b49ff26\"}}";
        PaymentResponse response = GatewayResponse.fromJson(rawResponse, PaymentResponse.class);
        Operation<?> operation = new RetrieveForm(response);

        assertEquals("GET", operation.getMethod());
        assertEquals("", operation.getRequestPayload());
        assertEquals("https://api.url/a4345be5b8a1af9773b8b0642b49ff26", operation.getRequestUri());
    }
}
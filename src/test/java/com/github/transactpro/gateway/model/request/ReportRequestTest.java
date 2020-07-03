package com.github.transactpro.gateway.model.request;

import com.github.transactpro.gateway.model.Request;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportRequestTest {
    @ParameterizedTest
    @MethodSource("uriTestData")
    public void getUri(String baseUrl, String path, String expected) throws MalformedURLException {
        Request request = new ReportRequest();
        assertEquals(expected, request.getUri(baseUrl, path));
    }

    private static Stream<Arguments> uriTestData() {
        return Stream.of(
                Arguments.of("https://aaa.com/v3.0", "/bbb", "https://aaa.com/bbb"),
                Arguments.of("https://aaa.com:3096/v3.0", "/bbb", "https://aaa.com:3096/bbb")
        );
    }
}
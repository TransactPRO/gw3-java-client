package com.github.transactpro.gateway;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.digest.RequestDigest;
import com.github.transactpro.gateway.operation.transaction.Sms;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.utils.Base64;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GatewayTest {

    @Test
    void processErrors() throws NoSuchFieldException {

        Gateway gw = new Gateway("dsfw3f34fsdf-GUID", "very,very_secret_key", "Adw2DAvvsessionId", "http://some-fake-url33-should-not-work.com/");
        Sms sms = new Sms();
        assertThrows(ValidationException.class, () -> gw.process(sms));

        Validator validator = mock(Validator.class);

        //noinspection rawtypes
        HashSet hs = new HashSet<ConstraintViolation<Request>>();
        //noinspection unchecked
        when(validator.validate(sms, sms.getValidationGroups())).thenReturn(hs);
        ReflectionUtils.setField(gw, gw.getClass().getDeclaredField("validator"), validator);

        assertThrows(IOException.class, () -> gw.process(sms));
    }

    @ParameterizedTest
    @MethodSource("processParameters")
    void process(String body, Integer statusCode, String headerName, String headerValue) throws NoSuchFieldException, IOException, NoSuchAlgorithmException, InvalidKeyException, ParseException {

        Gateway gw = new Gateway("dsfw3f34fsdf-GUID", "very,very_secret_key", "http://some-fake-url33-should-not-work.com/v3.0");
        Sms sms = new Sms();
        Validator validator = mock(Validator.class);

        //noinspection rawtypes
        HashSet hs = new HashSet<ConstraintViolation<Request>>();
        //noinspection unchecked
        when(validator.validate(sms, sms.getValidationGroups())).thenReturn(hs);


        HttpEntity httpEntity = mock(HttpEntity.class);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        Header header = new BasicHeader(headerName, headerValue);

        CloseableHttpResponse httpResponse = mock(CloseableHttpResponse.class, Mockito.RETURNS_DEEP_STUBS);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpResponse.getCode()).thenReturn(statusCode);
        when(httpResponse.getHeaders()).thenReturn(new Header[]{header});
        when(httpResponse.getFirstHeader("Authorization")).thenReturn(header);

        CloseableHttpClient httpClient = mock(CloseableHttpClient.class);
        when(httpClient.execute(any(ClassicHttpRequest.class))).thenReturn(httpResponse);

        RequestDigest requestDigest = mock(RequestDigest.class);
        when(requestDigest.getCnonce()).thenReturn(Base64.decodeBase64("MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg=="));
        when(requestDigest.getUri()).thenReturn("/v3.0/sms");
        sms.getRequest().setDigest(requestDigest);

        ReflectionUtils.setField(gw, gw.getClass().getDeclaredField("validator"), validator);
        ReflectionUtils.setField(gw, gw.getClass().getDeclaredField("httpClient"), httpClient);
        gw.process(sms);

        Assertions.assertEquals(body, sms.getResponse().getBody());
        Assertions.assertEquals(statusCode, sms.getResponse().getStatusCode());

        HashMap<String, String> expectedHeaders = new HashMap<String, String>(){{ put(headerName.toLowerCase(), headerValue); }};
        Assertions.assertEquals(expectedHeaders, sms.getResponse().getHeaders());
    }

    private static Stream<Arguments> processParameters() {
        return Stream.of(
                Arguments.of(
                        /* body */ "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"51f5171b\",\"redirect-url\":\"https://success.url.com/\",\"status-code\":30,\"status-text\":\"INSIDE FORM URL SENT\"},\"warnings\":[]}",
                        /*status code*/ 200,
                        /*header name*/ "Authorization",
                        /*header value*/ "Digest username=dsfw3f34fsdf-GUID, uri=\"/v3.0/sms\", algorithm=SHA-256, cnonce=\"MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg==\", snonce=\"MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg==\", qop=auth-int, response=\"c643706e9873ca41596eb66941d652365c19a9f24e78bc872b7f8eaca16ac097\""
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
package com.github.transactpro.gateway.model.digest;

import com.github.transactpro.gateway.model.digest.exception.DigestMismatchException;
import com.github.transactpro.gateway.model.digest.exception.DigestMissingException;
import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.response.CallbackResult;
import com.github.transactpro.gateway.model.response.GatewayResponse;
import org.apache.hc.client5.http.utils.Base64;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDigestTest {
    @ParameterizedTest
    @MethodSource("constructorErrorCases")
    <T extends Throwable> void constructorFailure(String authHeader, Class<T> errorClass, String expectedError) {
        T exception = assertThrows(errorClass, () -> new ResponseDigest(new BasicHeader("Authorization", authHeader)));

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedError), actualMessage + " doesn't contain " + expectedError);
    }

    private static Stream<Arguments> constructorErrorCases() {
        String nonce = Base64.encodeBase64String("1:q".getBytes(StandardCharsets.UTF_8));
        String noTsNonce = Base64.encodeBase64String("qqq".getBytes(StandardCharsets.UTF_8));
        String wrongTsNonce = Base64.encodeBase64String("qqq:www".getBytes(StandardCharsets.UTF_8));

        return Stream.of(
                Arguments.of("", DigestMissingException.class, "Authorization header is missing"),
                Arguments.of(
                        String.format("Digest uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=auth-int, response=e", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for username"
                ),
                Arguments.of(
                        String.format("Digest username=a, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=auth-int, response=e", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for uri"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, cnonce=%s, snonce=%s, qop=auth-int, response=e", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for algorithm"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, snonce=%s, qop=auth-int, response=e", nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for cnonce"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, qop=auth-int, response=e", nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for snonce"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, response=e", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for qop"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=auth", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: empty value for response"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=aaa, response=x", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: format error: Unknown QOP value"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=aaa, cnonce=%s, snonce=%s, qop=auth, response=x", nonce, nonce),
                        DigestMismatchException.class, "Digest mismatch: format error: Unknown algorithm"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=auth, response=x", nonce, noTsNonce),
                        DigestMismatchException.class, "Digest mismatch: corrupted value for snonce (missing timestamp)"
                ),
                Arguments.of(
                        String.format("Digest username=a, uri=b, algorithm=SHA-256, cnonce=%s, snonce=%s, qop=auth, response=x", nonce, wrongTsNonce),
                        DigestMismatchException.class, "Digest mismatch: corrupted value for snonce (unexpected timestamp value)"
                )
        );
    }

    @Test
    void constructorSuccessful() {
        String headerValue = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, " +
                "cnonce=\"MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg==\", " +
                "snonce=\"MTU5MTYyNDgwNzoUte6YsXIJmUo1EsA4yrYDCVbPrvCrEtqGq6CHTMhImg==\", qop=auth-int, " +
                "response=\"a21df219fd9bb2efb71554eb9ebb47f6a7a61769a289f9ab4fcbe41d7544e28d\"";
        Header header = new BasicHeader("Authorization", headerValue);
        ResponseDigest digest = new ResponseDigest(header);

        assertEquals("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", digest.username);
        assertEquals("/v3.0/sms", digest.uri);
        assertEquals(Digest.Algorithm.SHA256, digest.algorithm);
        assertEquals(Digest.QOP.AUTH_INT, digest.qop);
        assertEquals("a21df219fd9bb2efb71554eb9ebb47f6a7a61769a289f9ab4fcbe41d7544e28d", digest.response);
        assertEquals(1591624807, digest.timestamp);
        assertArrayEquals(Base64.decodeBase64("MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg=="), digest.cnonce);
        assertArrayEquals(Base64.decodeBase64("MTU5MTYyNDgwNzoUte6YsXIJmUo1EsA4yrYDCVbPrvCrEtqGq6CHTMhImg=="), digest.snonce);
    }

    @ParameterizedTest
    @MethodSource("verifyErrorCases")
    void verifyErrors(String guid, String originalUri, byte[] originalCnonce, String expectedError) {
        String body = "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"37b88436-b69c-45f3-ad26-b945153ad9a8\","+
                "\"redirect-url\":\"http://api.local/4f1f647d10e8296a2ed4d21e3639f1ee\",\"status-code\":30,\"status-text\":"+
                "\"INSIDE FORM URL SENT\"},\"warnings\":[\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded "+
                "for the account\"]}";

        String responseHeader = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, "+
                "cnonce=\"MTU5MTg2NjU3Mzo38zMeHvu4qcbhR8X158atP/BB4dDb5DbOMRT656yS7Q==\", "+
                "snonce=\"MTU5MTg2NjU3MzpvnttqUse7hfrkUHtPS8tWE1jl0D0G/DgMmEFwbk5/jw==\", qop=auth-int, "+
                "response=\"624478f45d33bbadc7cf0ae9b34462efd7b9736111f295e6330fe0bc3b20acda\"";

        ResponseDigest responseDigest = new ResponseDigest(new BasicHeader("Authorization", responseHeader));
        responseDigest.setOriginalUri(originalUri);
        responseDigest.setOriginalCnonce(originalCnonce);
        responseDigest.setBody(body);

        Exception exception = assertThrows(ResponseException.class, () -> responseDigest.verify(guid, "something wrong"));

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedError));
    }

    private static Stream<Arguments> verifyErrorCases() {
        byte[] validCnonce = Base64.decodeBase64("MTU5MTg2NjU3Mzo38zMeHvu4qcbhR8X158atP/BB4dDb5DbOMRT656yS7Q==");
        byte[] invalidCnonce = Base64.decodeBase64("MTU5MTg2NjU3MzpvnttqUse7hfrkUHtPS8tWE1jl0D0G/DgMmEFwbk5/jw==");

        return Stream.of(
                Arguments.of("wrong-guid", "/v3.0/sms", validCnonce, "Digest mismatch: username mismatch"),
                Arguments.of("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "http://another.local", validCnonce, "Digest mismatch: uri mismatch"),
                Arguments.of("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "/v3.0/sms", invalidCnonce, "Digest mismatch: cnonce mismatch"),
                Arguments.of("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "/v3.0/sms", validCnonce, "Digest mismatch")
        );
    }

    @Test
    void verifySuccessFullChecks() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String body = "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"37b88436-b69c-45f3-ad26-b945153ad9a8\","+
                "\"redirect-url\":\"http://api.local/4f1f647d10e8296a2ed4d21e3639f1ee\",\"status-code\":30,\"status-text\":"+
                "\"INSIDE FORM URL SENT\"},\"warnings\":[\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded "+
                "for the account\"]}";

        String responseHeader = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, "+
                "cnonce=\"MTU5MTg2NjU3Mzo38zMeHvu4qcbhR8X158atP/BB4dDb5DbOMRT656yS7Q==\", "+
                "snonce=\"MTU5MTg2NjU3MzpvnttqUse7hfrkUHtPS8tWE1jl0D0G/DgMmEFwbk5/jw==\", qop=auth-int, "+
                "response=\"dda7026eebbeeee19fda191fd951d470b2064e3e1bc416365835abc775352552\"";

        ResponseDigest responseDigest = new ResponseDigest(new BasicHeader("Authorization", responseHeader));
        responseDigest.setOriginalUri("/v3.0/sms");
        responseDigest.setOriginalCnonce(Base64.decodeBase64("MTU5MTg2NjU3Mzo38zMeHvu4qcbhR8X158atP/BB4dDb5DbOMRT656yS7Q=="));
        responseDigest.setBody(body);
        responseDigest.verify("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "tPMOogw7YBumh6RpXxi2nvGW0C9lJq3L");
    }

    @Test
    void verifySuccessMinimalChecks() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String body = "{\"acquirer-details\":{},\"error\":{},\"gw\":{\"gateway-transaction-id\":\"37b88436-b69c-45f3-ad26-b945153ad9a8\","+
                "\"redirect-url\":\"http://api.local/4f1f647d10e8296a2ed4d21e3639f1ee\",\"status-code\":30,\"status-text\":"+
                "\"INSIDE FORM URL SENT\"},\"warnings\":[\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded "+
                "for the account\"]}";

        String responseHeader = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, "+
                "cnonce=\"MTU5MTg2NjU3Mzo38zMeHvu4qcbhR8X158atP/BB4dDb5DbOMRT656yS7Q==\", "+
                "snonce=\"MTU5MTg2NjU3MzpvnttqUse7hfrkUHtPS8tWE1jl0D0G/DgMmEFwbk5/jw==\", qop=auth-int, "+
                "response=\"dda7026eebbeeee19fda191fd951d470b2064e3e1bc416365835abc775352552\"";

        ResponseDigest responseDigest = new ResponseDigest(new BasicHeader("Authorization", responseHeader));
        responseDigest.setBody(body);
        responseDigest.verify("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "tPMOogw7YBumh6RpXxi2nvGW0C9lJq3L");
    }

    @Test
    void verifyCallback() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String jsonFromPost = "{\"result-data\":{\"gw\":{\"gateway-transaction-id\":\"8d77f986-de7f-4d47-97ef-9de7f8561684\",\"status-code\":7,\"status-text\":\"SUCCESS\"},"+
                "\"error\":{},\"acquirer-details\":{\"eci-sli\":\"503\",\"terminal-mid\":\"3201210\",\"transaction-id\":\"7146311464333929\","+
                "\"result-code\":\"000\",\"status-text\":\"Approved\",\"status-description\":\"Approved\"},\"warnings\":"+
                "[\"Soon counters will be exceeded for the merchant\",\"Soon counters will be exceeded for the account\","+
                "\"Soon counters will be exceeded for the terminal group\",\"Soon counters will be exceeded for the terminal\"]}}";

        String signFromPost = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, "+
                "cnonce=\"MTU5MTg2OTQ3OTpbmPfGQxVAh5z7MdWnRjF1cavfwKyxiLVrX4p7IHNwWA==\", "+
                "snonce=\"MTU5MTg2OTQ4MTqfPxash/0hfNpI/gHuaoSiV+6PwVKYEawxchE0nxHTkA==\", qop=auth-int, "+
                "response=\"87bd753875e28da54dfcb5e61614e10a7120aba9a3f8bed0e6eaa9acb85aa9f9\"";

        ResponseDigest responseDigest = new ResponseDigest(signFromPost);
        responseDigest.setOriginalUri("/v3.0/sms");
        responseDigest.setOriginalCnonce(Base64.decodeBase64("MTU5MTg2OTQ3OTpbmPfGQxVAh5z7MdWnRjF1cavfwKyxiLVrX4p7IHNwWA=="));
        responseDigest.setBody(jsonFromPost);
        responseDigest.verify("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", "tPMOogw7YBumh6RpXxi2nvGW0C9lJq3L");

        CallbackResult parsedResult = GatewayResponse.fromJson(jsonFromPost, CallbackResult.class);
        assertEquals("8d77f986-de7f-4d47-97ef-9de7f8561684", parsedResult.getResultData().getGw().getGatewayTransactionId());
    }
}

package com.github.transactpro.gateway.model.digest;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

abstract public class Digest {
    @Getter
    @Setter
    protected String username;
    @Getter
    protected String uri;
    @Getter
    protected QOP qop;
    @Getter
    protected Algorithm algorithm;
    @Getter
    protected byte[] cnonce;
    @Getter
    protected String response;

    @Setter
    protected String body;

    protected byte[] generateNonce(int length) throws IOException {
        String timeStampUTC = String.valueOf(System.currentTimeMillis() / 1000L);

        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(timeStampUTC.getBytes());
        result.write(':');
        result.write(bytes);

        return result.toByteArray();
    }

    protected String calculate(ByteArrayOutputStream data, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        String hmacAlgorithm = algorithm.getHmacAlgorithm();
        Mac mac = Mac.getInstance(hmacAlgorithm);

        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), hmacAlgorithm);
        mac.init(secretKeySpec);

        byte[] digest = mac.doFinal(data.toByteArray());
        return Hex.encodeHexString(digest);
    }

    public enum QOP {
        AUTH("auth"),
        AUTH_INT("auth-int");

        public static QOP create(String value) throws ValidationException {
            switch (value) {
                case "auth":
                    return AUTH;
                case "auth-int":
                    return AUTH_INT;
                default:
                    throw new ValidationException("Unknown QOP value " + value);
            }
        }

        @Getter
        private final String value;

        QOP(String value) {
            this.value = value;
        }
    }

    public enum Algorithm {
        SHA256("SHA-256");

        private static final HashMap<String, String> ALGORITHMS_MAP = new HashMap<String, String>(){{
            put("sha-256", "HmacSHA256");
        }};

        public static Algorithm create(String value) throws ValidationException {
            if (value.equals(SHA256.value)) {
                return SHA256;
            }

            throw new ValidationException("Unknown algorithm " + value);
        }

        @Getter
        private final String value;

        Algorithm(String value) {
            this.value = value;
        }

        public String getHmacAlgorithm() {
            return ALGORITHMS_MAP.getOrDefault(value.toLowerCase(), "HmacSHA256");
        }
    }
}

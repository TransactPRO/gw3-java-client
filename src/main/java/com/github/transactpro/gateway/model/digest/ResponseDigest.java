package com.github.transactpro.gateway.model.digest;

import com.github.transactpro.gateway.model.digest.exception.DigestMismatchException;
import com.github.transactpro.gateway.model.digest.exception.DigestMissingException;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.utils.Base64;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicHeaderValueParser;
import org.apache.hc.core5.http.message.ParserCursor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResponseDigest extends Digest {
    @Getter
    protected int timestamp;
    @Getter
    protected byte[] snonce;

    @Setter
    protected String originalUri;
    @Setter
    protected byte[] originalCnonce;

    public ResponseDigest(String responseDigest) {
        this(new BasicHeader("Authorization", responseDigest));
    }

    public ResponseDigest(Header authorizationHeader) throws DigestMissingException, DigestMismatchException {
        if (authorizationHeader == null || authorizationHeader.getValue() == null || authorizationHeader.getValue().isEmpty()) {
            throw new DigestMissingException("Authorization header is missing");
        }

        HashMap<String, String> values = new HashMap<String, String>(){{
            put("username", null);
            put("uri", null);
            put("response", null);
            put("algorithm", null);
            put("qop", null);
            put("cnonce", null);
            put("snonce", null);
        }};

        String authValue = authorizationHeader.getValue();
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(authValue, new ParserCursor(0, authValue.length()));
        for (int i = 0; i < elements.length; i++) {
            String key = elements[i].getName();
            String value = elements[i].getValue();
            if (i == 0) {
                key = key.toLowerCase().replaceFirst("digest ", "");
            }

            if (values.containsKey(key)) {
                values.put(key, value);
            }
        }

        try {
            for (Map.Entry<String, String> item : values.entrySet()) {
                if (item.getValue() == null || item.getValue().isEmpty()) {
                    throw new DigestMismatchException("Digest mismatch: empty value for " + item.getKey());
                }

                switch (item.getKey()) {
                    case "username":
                        username = item.getValue();
                        break;
                    case "uri":
                        uri = item.getValue();
                        break;
                    case "response":
                        response = item.getValue();
                        break;
                    case "algorithm":
                        algorithm = Algorithm.create(item.getValue());
                        break;
                    case "qop":
                        qop = QOP.create(item.getValue());
                        break;
                    case "cnonce":
                        cnonce = Base64.decodeBase64(item.getValue());
                        break;
                    case "snonce":
                        snonce = Base64.decodeBase64(item.getValue());

                        int timestampDelimiterPos = new String(snonce).indexOf((byte) ':');
                        if (timestampDelimiterPos == -1) {
                            throw new DigestMismatchException("Digest mismatch: corrupted value for snonce (missing timestamp)");
                        }

                        try {
                            timestamp = Integer.parseInt(new String(Arrays.copyOfRange(snonce, 0, timestampDelimiterPos)), 10);
                        } catch (NumberFormatException e) {
                            throw new DigestMismatchException("Digest mismatch: corrupted value for snonce (unexpected timestamp value)");
                        }

                        break;
                }
            }
        } catch (ValidationException e) {
            throw new DigestMismatchException("Digest mismatch: format error: " + e.getMessage(), e);
        }
    }

    public void verify(String objectGUID, String secret) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        if (!objectGUID.equalsIgnoreCase(username)) {
            throw new DigestMismatchException("Digest mismatch: username mismatch");
        }

        if (originalUri != null && !originalUri.equals(uri)) {
            throw new DigestMismatchException("Digest mismatch: uri mismatch");
        }

        if (originalCnonce != null && !Arrays.equals(originalCnonce, cnonce)) {
            throw new DigestMismatchException("Digest mismatch: cnonce mismatch");
        }

        Charset cs = StandardCharsets.UTF_8;
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        data.write(username.getBytes(cs));
        data.write(cnonce);
        data.write(snonce);
        data.write(qop.getValue().getBytes(cs));
        data.write(uri.getBytes(cs));
        if (qop == QOP.AUTH_INT) {
            data.write(body.getBytes(cs));
        }
        String expectedDigest = calculate(data, secret);

        if (!MessageDigest.isEqual(expectedDigest.getBytes(cs), response.getBytes(cs))) {
            throw new DigestMismatchException("Digest mismatch");
        }
    }
}

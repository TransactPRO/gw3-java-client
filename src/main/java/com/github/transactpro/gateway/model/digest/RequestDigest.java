package com.github.transactpro.gateway.model.digest;

import lombok.Setter;
import org.apache.hc.client5.http.utils.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RequestDigest extends Digest {
    private static final int NONCE_LENGTH = 32;

    @Setter
    protected String secret;

    public void init() throws IOException {
        this.algorithm = Algorithm.SHA256;
        this.qop = QOP.AUTH_INT;
        this.cnonce = generateNonce(NONCE_LENGTH);
    }

    public void setUri(String fullUrl) throws MalformedURLException {
        uri = new URL(fullUrl).getPath();
    }

    public String createHeader() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        Charset cs = StandardCharsets.UTF_8;
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        data.write(username.getBytes(cs));
        data.write(cnonce);
        data.write(qop.getValue().getBytes(cs));
        data.write(uri.getBytes(cs));
        if (qop == QOP.AUTH_INT) {
            data.write(body.getBytes(cs));
        }

        response = calculate(data, secret);
        return String.format(
                "Digest username=%s, uri=\"%s\", algorithm=%s, cnonce=\"%s\", qop=%s, response=\"%s\"",
                username,
                uri,
                algorithm.getValue(),
                Base64.encodeBase64String(cnonce),
                qop.getValue(),
                response
        );
    }
}

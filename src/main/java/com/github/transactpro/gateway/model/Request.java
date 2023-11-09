package com.github.transactpro.gateway.model;

import com.github.transactpro.gateway.model.digest.RequestDigest;
import com.github.transactpro.gateway.model.request.Authorization;
import com.github.transactpro.gateway.model.request.Data;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Accessors(chain = true)
public class Request {

    @SerializedName("auth-data")
    @Valid
    private Authorization authorization = new Authorization();
    @Valid
    private Data data;

    private transient RequestDigest digest = new RequestDigest();

    public void init() {
        data = new Data();
    }

    public String getUri(String baseUri, String requestPath) throws MalformedURLException {
        if (requestPath.startsWith("http")) {
            return requestPath;
        }

        return baseUri + requestPath;
    }

    public String getSign(String username, String secret, String fullUrl, String body) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        digest.init();
        digest.setUsername(username);
        digest.setSecret(secret);
        digest.setUri(fullUrl);
        digest.setBody(body);

        return digest.createHeader();
    }
}

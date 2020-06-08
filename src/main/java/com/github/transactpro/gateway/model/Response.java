package com.github.transactpro.gateway.model;

import com.github.transactpro.gateway.model.digest.ResponseDigest;
import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.response.CsvResponse;
import com.github.transactpro.gateway.model.response.GatewayResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Carry all necessary data of gateway response.
 */
@Getter
@Accessors(chain = true)
public class Response<T> {
    private final Class<T> responseType;

    private final Integer statusCode;

    private final String body;

    private final Map<String, String> headers;

    @Setter
    ResponseDigest digest;

    public Response(Class<T> responseType, Integer statusCode, String body, Map<String, String> headers) {
        this.responseType = responseType;
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    public Boolean isSuccessful() {
        return (statusCode >= 200 && statusCode < 400) || statusCode == 402;
    }

    public T parse() throws ResponseException {
        if (responseType.equals(CsvResponse.class)) {
            return createCsvResponse();
        } else {
            return GatewayResponse.fromJson(body, responseType);
        }
    }

    private T createCsvResponse() throws ResponseException {
        try {
            T result = responseType.newInstance();
            ((CsvResponse) result).init(body);

            return result;
        } catch (Exception e) {
            throw new ResponseException("Cannot create CSV response: " + e.getMessage(), e);
        }
    }
}

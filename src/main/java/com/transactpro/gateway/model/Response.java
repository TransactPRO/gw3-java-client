package com.transactpro.gateway.model;

import lombok.Getter;

import java.util.Map;

/**
 * Carry all necessary data of gateway response.
 */
@Getter
public class Response {

    /**
     * Response HTTP status code
     */
    private Integer statusCode;

    /**
     * Response body - JSON string
     */
    private String body;

    /**
     * Response headers
     */
    private Map<String, String> headers;

    public Response(Integer statusCode, String body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }
}

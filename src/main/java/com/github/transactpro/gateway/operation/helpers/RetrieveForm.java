package com.github.transactpro.gateway.operation.helpers;

import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;

import java.net.URL;

public class RetrieveForm extends Operation<Object> {
    {
        method = "GET";
        responseType = Object.class;
    }

    public RetrieveForm(PaymentResponse paymentResponse) throws ResponseException {
        if (paymentResponse == null
                || paymentResponse.getGw() == null
                || paymentResponse.getGw().getRedirectUrl() == null
        ) {
            throw new ResponseException("Response doesn't contain link to an HTML form");
        }

        requestUri = paymentResponse.getGw().getRedirectUrl().toString();
    }

    public RetrieveForm(URL url) {
        requestUri = url.toString();
    }

    public RetrieveForm(String url) throws ResponseException {
        if (url == null || url.isEmpty()) {
            throw new ResponseException("URL cannot be empty");
        }

        requestUri = url;
    }
}

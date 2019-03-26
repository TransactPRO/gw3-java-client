package com.gateway.model;

import com.gateway.model.response.AcquirerDetails;
import com.gateway.model.response.Error;
import com.gateway.model.response.Gateway;

public class Response {
    private Gateway gateway;
    private Error error;
    private AcquirerDetails acquirerDetails;
    private String msg;
    private Integer status;
}

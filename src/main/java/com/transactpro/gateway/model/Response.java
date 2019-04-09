package com.transactpro.gateway.model;

import com.transactpro.gateway.model.response.AcquirerDetails;
import com.transactpro.gateway.model.response.Error;
import com.transactpro.gateway.model.response.Gateway;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Response {

    @SerializedName("gw")
    private Gateway gateway;
    private Error error;
    private AcquirerDetails acquirerDetails;
    private String msg;
    private Integer status;

}

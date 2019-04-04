package com.gateway.model;

import com.gateway.model.response.AcquirerDetails;
import com.gateway.model.response.Error;
import com.gateway.model.response.Gateway;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    @SerializedName("gw")
    private Gateway gateway;
    private Error error;
    private AcquirerDetails acquirerDetails;
    private String msg;
    private Integer status;

}

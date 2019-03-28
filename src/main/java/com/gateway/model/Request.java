package com.gateway.model;

import com.gateway.model.request.Authorization;
import com.gateway.model.request.Data;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class Request {

    @SerializedName("auth-data")
    @Valid
    @Setter
    private Authorization authorization;
    @Valid
    @Getter
    @Setter
    private Data data;

    public Request() {
        this.data = new Data();
    }
}

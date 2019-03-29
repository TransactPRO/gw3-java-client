package com.gateway.model;

import com.gateway.model.request.Authorization;
import com.gateway.model.request.Data;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;

public class Request {

    @SerializedName("auth-data")
    @Valid
    @Setter
    private Authorization authorization;
    @Valid
    @Getter
    @Setter
    @Accessors(chain = true)
    private Data data;

    public Request() {
        this.data = new Data();
    }
}

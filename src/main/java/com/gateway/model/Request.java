package com.gateway.model;

import com.gateway.model.request.Authorization;
import com.gateway.model.request.Data;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class Request {

    @SerializedName("auth-data")
    private Authorization authorization;
    private Data data;

    public Authorization getAuthorization() {
        return authorization;
    }

    public Request setAuthorization(Authorization authorization) {
        this.authorization = authorization;
        return this;
    }

    public Data getData() {
        return data;
    }

    public Request setData(Data data) {
        this.data = data;
        return this;
    }
}

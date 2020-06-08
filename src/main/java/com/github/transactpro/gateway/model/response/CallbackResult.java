package com.github.transactpro.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CallbackResult extends GenericResponse {
    @SerializedName("result-data")
    private PaymentResponse resultData;
}

package com.transactpro.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class AcquirerDetails {

    @SerializedName("eci-sli")
    private Integer eciSLi;
    @SerializedName("terminal-mid")
    private String terminalId;
    private String transactionId;
    private String resultCode;
    private String statusText;
    private String statusDescription;
}

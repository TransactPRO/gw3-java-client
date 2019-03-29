package com.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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

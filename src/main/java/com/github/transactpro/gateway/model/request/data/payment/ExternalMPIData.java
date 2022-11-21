package com.github.transactpro.gateway.model.request.data.payment;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExternalMPIData {
    @SerializedName("protocolVersion")
    private String protocolVersion;
    @SerializedName("dsTransId")
    private String dsTransId;
    private String xid;
    private String cavv;
    @SerializedName("transStatus")
    private String transStatus;
}

package com.github.transactpro.gateway.model.request.data.payment;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExternalTokenData {
    private String cryptogram;
    private String eci;
    @SerializedName("transStatus")
    private String transStatus;
    @SerializedName("dsTransID")
    private String dsTransID;
    @SerializedName("acsTransID")
    private String acsTransID;
    @SerializedName("cardHolderAuthenticated")
    private Boolean cardHolderAuthenticated;
}

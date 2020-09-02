package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.constants.Status;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.net.URL;
import java.util.ArrayList;

@Getter
public class PaymentResponse extends GenericResponse {
    @Getter
    public static class AcquirerDetails {
        @SerializedName("dynamic-descriptor")
        private String dynamicDescriptor;
        @SerializedName("eci-sli")
        private String eciSli;
        @SerializedName("result-code")
        private String resultCode;
        @SerializedName("status-description")
        private String statusDescription;
        @SerializedName("status-text")
        private String statusText;
        @SerializedName("terminal-mid")
        private String terminalMid;
        @SerializedName("transaction-id")
        private String transactionId;
    }

    @Getter
    public static class GW {
        @SerializedName("gateway-transaction-id")
        private String gatewayTransactionId;
        @SerializedName("merchant-transaction-id")
        private String merchantTransactionId;
        @SerializedName("original-gateway-transaction-id")
        private String originalGatewayTransactionId;
        @SerializedName("parent-gateway-transaction-id")
        private String parentGatewayTransactionId;
        @SerializedName("redirect-url")
        private URL redirectUrl;
        @SerializedName("status-code")
        private Status statusCode;
        @SerializedName("status-text")
        private String statusText;
    }

    @SerializedName("acquirer-details")
    private AcquirerDetails acquirerDetails;
    private GW gw;
    private ArrayList<String> warnings;
}

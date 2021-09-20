package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.constants.CardFamily;
import com.github.transactpro.gateway.model.response.constants.Status;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

public class StatusResponse extends TransactionsList<StatusResponse.Element> {
    @Getter
    public static class TransactionStatus {
        @SerializedName("gateway-transaction-id")
        private String gatewayTransactionId;
        @SerializedName("status-code")
        private Status statusCode;
        @SerializedName("status-code-general")
        private Status statusCodeGeneral;
        @SerializedName("status-text")
        private String statusText;
        @SerializedName("status-text-general")
        private String statusTextGeneral;
        @SerializedName("card-mask")
        private String cardMask;
        @SerializedName("card-family")
        private CardFamily cardFamily;
    }

    @Getter
    public static class Element extends TransactionsList.Element {
        private ArrayList<TransactionStatus> status;
    }
}

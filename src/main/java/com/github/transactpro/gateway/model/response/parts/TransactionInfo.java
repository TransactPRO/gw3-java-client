package com.github.transactpro.gateway.model.response.parts;

import com.github.transactpro.gateway.model.response.constants.Status;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.Date;

@Getter
public class TransactionInfo {
    @SerializedName("account-guid")
    private String accountGuid;
    @SerializedName("acq-terminal-id")
    private String acqTerminalId;
    @SerializedName("acq-transaction-id")
    private String acqTransactionId;
    @SerializedName("amount")
    private int amount;
    @SerializedName("approval-code")
    private String approvalCode;
    @SerializedName("cardholder-name")
    private String cardholderName;
    @SerializedName("currency")
    private String currency;
    @SerializedName("date-finished")
    private Date dateFinished;
    @SerializedName("eci-sli")
    private String eciSli;
    @SerializedName("gateway-transaction-id")
    private String gatewayTransactionId;
    @SerializedName("merchant-transaction-id")
    private String merchantTransactionId;
    @SerializedName("status-code")
    private Status statusCode;
    @SerializedName("status-code-general")
    private Status statusCodeGeneral;
    @SerializedName("status-text")
    private String statusText;
    @SerializedName("status-text-general")
    private String statusTextGeneral;
}

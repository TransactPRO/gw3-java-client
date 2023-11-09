package com.github.transactpro.gateway.model.request.data;


import com.github.transactpro.gateway.model.request.data.command.CardVerificationMode;
import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.github.transactpro.gateway.validation.base.CommandTransactionIdGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Command {

    @NotNull(groups = {CommandTransactionIdGroup.class})
    private String gatewayTransactionId;
    private String formId;
    private String terminalMid;
    private String[] gatewayTransactionIds;
    private String[] merchantTransactionIds;
    private CardVerificationMode cardVerification;
    private PaymentMethodDataSource paymentMethodDataSource;
    private String paymentMethodDataToken;
}
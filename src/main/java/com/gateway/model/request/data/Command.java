package com.gateway.model.request.data;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Command {
    private String gatewayTransactionId;
    private String formId;
    private String terminalMid;
    private String[] gatewayTransactionIds;
    private String[] merchantTransactionIds;
}
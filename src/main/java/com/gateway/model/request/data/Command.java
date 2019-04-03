package com.gateway.model.request.data;


import com.gateway.validation.base.CommandTransactionIdGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
}
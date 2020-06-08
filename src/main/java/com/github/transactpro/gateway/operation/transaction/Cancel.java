package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.base.CommandInterface;
import com.github.transactpro.gateway.operation.interfaces.request.base.OrderInterface;
import com.github.transactpro.gateway.validation.base.CommandTransactionIdGroup;

public class Cancel extends Operation<PaymentResponse> implements CommandInterface<Cancel>, OrderInterface<Cancel> {
    {
        requestUri = "/cancel";
        responseType = PaymentResponse.class;
    }

    public Class<?> getValidationGroups() {
        return CommandTransactionIdGroup.class;
    }
}
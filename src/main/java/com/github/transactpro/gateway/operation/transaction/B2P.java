package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.github.transactpro.gateway.validation.TransactionGroup;

public class B2P extends Operation<PaymentResponse> implements TransactionInterface<B2P> {
    {
        requestUri = "/b2p";
        responseType = PaymentResponse.class;
    }

    public Class<?> getValidationGroups() {
        return TransactionGroup.class;
    }
}
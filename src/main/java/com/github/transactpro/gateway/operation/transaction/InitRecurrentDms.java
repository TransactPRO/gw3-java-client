package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.github.transactpro.gateway.validation.TransactionGroup;

public class InitRecurrentDms extends Operation<PaymentResponse> implements TransactionInterface<InitRecurrentDms> {
    {
        requestUri = "/recurrent/dms/init";
        responseType = PaymentResponse.class;
    }

    @Override
    public Class<?> getValidationGroups() {
        return TransactionGroup.class;
    }
}
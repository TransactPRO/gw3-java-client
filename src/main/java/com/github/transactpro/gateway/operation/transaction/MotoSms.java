package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;
import com.github.transactpro.gateway.validation.TransactionGroup;

public class MotoSms  extends Operation<PaymentResponse> implements TransactionInterface<MotoSms> {
    {
        requestUri = "/moto/sms";
        responseType = PaymentResponse.class;
    }

    public Class<?> getValidationGroups() {
        return TransactionGroup.class;
    }
}
package com.github.transactpro.gateway.operation.token;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.TransactionInterface;

import javax.validation.groups.Default;

public class CreateToken extends Operation<PaymentResponse> implements TransactionInterface<CreateToken> {
    {
        requestUri = "/token/create";
        responseType = PaymentResponse.class;
    }

    @Override
    public Class<?> getValidationGroups() {
        return Default.class;
    }
}

package com.github.transactpro.gateway.operation.transaction;

import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.ChargeInterface;
import com.github.transactpro.gateway.validation.CommandAmountGroup;

public class DmsCharge extends Operation<PaymentResponse> implements ChargeInterface<DmsCharge> {
    {
        requestUri = "/charge-dms";
        responseType = PaymentResponse.class;
    }

    public Class<?> getValidationGroups() {
        return CommandAmountGroup.class;
    }
}
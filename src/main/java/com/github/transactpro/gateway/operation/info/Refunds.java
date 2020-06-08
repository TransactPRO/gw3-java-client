package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.RefundsResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Refunds extends Operation<RefundsResponse> implements InfoInterface<Refunds> {
    {
        requestUri = "/refunds";
        responseType = RefundsResponse.class;
    }
}

package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.LimitsResponse;
import com.github.transactpro.gateway.operation.Operation;

public class Limits extends Operation<LimitsResponse> {
    {
        requestUri = "/limits";
        responseType = LimitsResponse.class;
    }
}

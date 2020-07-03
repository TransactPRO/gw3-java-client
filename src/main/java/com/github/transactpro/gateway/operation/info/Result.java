package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.ResultResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Result extends Operation<ResultResponse> implements InfoInterface<Result> {
    {
        requestUri = "/result";
        responseType = ResultResponse.class;
    }
}

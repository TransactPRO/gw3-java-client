package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.HistoryResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class History extends Operation<HistoryResponse> implements InfoInterface<History> {
    {
        requestUri = "/history";
        responseType = HistoryResponse.class;
    }
}

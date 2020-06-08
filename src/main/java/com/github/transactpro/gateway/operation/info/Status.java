package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.StatusResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Status extends Operation<StatusResponse> implements InfoInterface<Status> {
    {
        requestUri = "/status";
        responseType = StatusResponse.class;
    }
}

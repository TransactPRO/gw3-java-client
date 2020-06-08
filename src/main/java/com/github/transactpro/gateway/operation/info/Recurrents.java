package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.response.RecurringTransactionsResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.operation.interfaces.request.InfoInterface;

public class Recurrents extends Operation<RecurringTransactionsResponse> implements InfoInterface<Recurrents> {
    {
        requestUri = "/recurrents";
        responseType = RecurringTransactionsResponse.class;
    }
}
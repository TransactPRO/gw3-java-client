package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.parts.Error;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class TransactionsList<T extends TransactionsList.Element> extends GenericResponse {
    @Getter
    public static class Element {
        private Error error;

        @SerializedName("gateway-transaction-id")
        private String gatewayTransactionId;
    }

    private ArrayList<T> transactions;
}

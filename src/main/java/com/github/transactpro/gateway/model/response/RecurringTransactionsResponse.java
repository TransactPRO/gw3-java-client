package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.parts.TransactionInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

public class RecurringTransactionsResponse extends TransactionsList<RecurringTransactionsResponse.Element> {
    @Getter
    public static class Element extends TransactionsList.Element {
        @SerializedName("recurrents")
        private ArrayList<TransactionInfo> subsequent;
    }
}

package com.github.transactpro.gateway.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.Date;

public class ResultResponse extends TransactionsList<ResultResponse.Element> {
    @Getter
    public static class Element extends TransactionsList.Element {
        @SerializedName("date-created")
        private Date dateCreated;
        @SerializedName("date-finished")
        private Date dateFinished;
        @SerializedName("result-data")
        private PaymentResponse resultData;
    }
}

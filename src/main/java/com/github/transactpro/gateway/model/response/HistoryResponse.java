package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.constants.Status;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

public class HistoryResponse extends TransactionsList<HistoryResponse.Element> {
    @Getter
    public static class HistoryEvent {
        @SerializedName("date-updated")
        private Date dateUpdated;
        @SerializedName("status-code-new")
        private Status statusCodeNew;
        @SerializedName("status-code-old")
        private Status statusCodeOld;
        @SerializedName("status-text-new")
        private String statusTextNew;
        @SerializedName("status-text-old")
        private String statusTextOld;
    }

    @Getter
    public static class Element extends TransactionsList.Element {
        private ArrayList<HistoryEvent> history;
    }
}

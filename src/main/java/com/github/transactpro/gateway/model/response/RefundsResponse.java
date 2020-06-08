package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.parts.TransactionInfo;
import lombok.Getter;

import java.util.ArrayList;

public class RefundsResponse extends TransactionsList<RefundsResponse.Element> {
    @Getter
    public static class Element extends TransactionsList.Element {
        private ArrayList<TransactionInfo> refunds;
    }
}

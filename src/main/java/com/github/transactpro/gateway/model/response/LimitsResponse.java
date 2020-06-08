package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.parts.Limit;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class LimitsResponse extends GenericResponse {
    private String type;
    private String title;
    @SerializedName("acq-terminal-id")
    private String acqTerminalId;
    @SerializedName("counters")
    private ArrayList<Limit> limits;
    @SerializedName("childs")
    private ArrayList<LimitsResponse> children;
}

package com.github.transactpro.gateway.model.response.constants;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public enum Enrollment {
    @SerializedName("n")
    NO(false),
    @SerializedName("y")
    YES(true);

    @Getter
    private final boolean value;

    Enrollment(boolean value) {
        this.value = value;
    }
}

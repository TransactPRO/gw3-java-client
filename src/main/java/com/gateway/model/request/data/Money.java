package com.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Money {
    @NotNull
    private Integer amount;
    @NotNull
    private String currency;
}

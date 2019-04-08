package com.gateway.model.request.data;

import com.gateway.validation.base.AmountGroup;
import com.gateway.validation.base.MoneyGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class Money {
    @NotNull(groups = {MoneyGroup.class, AmountGroup.class})
    private Integer amount;
    @NotNull(groups = {MoneyGroup.class})
    private String currency;
}

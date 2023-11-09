package com.github.transactpro.gateway.model.request.data;

import com.github.transactpro.gateway.validation.base.AmountGroup;
import com.github.transactpro.gateway.validation.base.MoneyGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Money {

    @NotNull(groups = {MoneyGroup.class, AmountGroup.class})
    private Integer amount;
    @NotNull(groups = {MoneyGroup.class})
    private String currency;
}

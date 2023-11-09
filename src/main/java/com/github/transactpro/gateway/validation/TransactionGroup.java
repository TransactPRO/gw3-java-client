package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.MoneyGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, MoneyGroup.class})
public interface TransactionGroup {
}

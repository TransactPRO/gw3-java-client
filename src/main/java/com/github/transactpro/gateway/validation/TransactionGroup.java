package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.MoneyGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, MoneyGroup.class})
public interface TransactionGroup {
}

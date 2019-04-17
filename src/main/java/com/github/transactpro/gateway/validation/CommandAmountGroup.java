package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.AmountGroup;
import com.github.transactpro.gateway.validation.base.CommandTransactionIdGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, AmountGroup.class, CommandTransactionIdGroup.class})
public interface CommandAmountGroup {
}

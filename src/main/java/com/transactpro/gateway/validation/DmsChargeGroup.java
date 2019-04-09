package com.transactpro.gateway.validation;

import com.transactpro.gateway.validation.base.AmountGroup;
import com.transactpro.gateway.validation.base.CommandTransactionIdGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, AmountGroup.class, CommandTransactionIdGroup.class})
public interface DmsChargeGroup {
}

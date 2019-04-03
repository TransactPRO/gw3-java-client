package com.gateway.validation;

import com.gateway.validation.base.AmountGroup;
import com.gateway.validation.base.CommandTransactionIdGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, AmountGroup.class, CommandTransactionIdGroup.class})
public interface DmsChargeGroup {
}

package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.GwTransactionIdGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, GwTransactionIdGroup.class})
public interface VerifyCardGroup {
}

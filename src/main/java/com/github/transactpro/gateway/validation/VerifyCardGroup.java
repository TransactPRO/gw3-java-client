package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.GwTransactionIdGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, GwTransactionIdGroup.class})
public interface VerifyCardGroup {
}

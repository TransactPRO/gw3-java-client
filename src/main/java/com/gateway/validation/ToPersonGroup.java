package com.gateway.validation;

import com.gateway.operation.transaction.Credit;

import javax.validation.GroupSequence;

@GroupSequence({Credit.class})
public interface ToPersonGroup {
}

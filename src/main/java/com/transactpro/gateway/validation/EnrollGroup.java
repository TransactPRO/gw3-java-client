package com.transactpro.gateway.validation;

import com.transactpro.gateway.validation.base.DataGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, DataGroup.class})
public interface EnrollGroup {
}

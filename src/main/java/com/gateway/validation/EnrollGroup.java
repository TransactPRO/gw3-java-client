package com.gateway.validation;

import com.gateway.validation.base.DataGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, DataGroup.class})
public interface EnrollGroup {
}

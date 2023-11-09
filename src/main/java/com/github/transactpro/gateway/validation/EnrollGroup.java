package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.DataGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, DataGroup.class})
public interface EnrollGroup {
}

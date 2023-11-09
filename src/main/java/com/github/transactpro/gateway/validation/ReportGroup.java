package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.ReportFilterGroup;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, ReportFilterGroup.class})
public interface ReportGroup {
}

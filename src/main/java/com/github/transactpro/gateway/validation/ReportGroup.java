package com.github.transactpro.gateway.validation;

import com.github.transactpro.gateway.validation.base.ReportFilterGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ReportFilterGroup.class})
public interface ReportGroup {
}

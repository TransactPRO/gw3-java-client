package com.github.transactpro.gateway.model.request;

import com.github.transactpro.gateway.validation.base.ReportFilterGroup;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ReportFilterData {
    @Positive(groups = {ReportFilterGroup.class})
    private Long dtCreatedFrom;
    @Positive(groups = {ReportFilterGroup.class})
    private Long dtCreatedTo;
    @Positive(groups = {ReportFilterGroup.class})
    private Long dtFinishedFrom;
    @Positive(groups = {ReportFilterGroup.class})
    private Long dtFinishedTo;
}

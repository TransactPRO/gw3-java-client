package com.github.transactpro.gateway.operation.reporting;

import com.github.transactpro.gateway.model.request.ReportRequest;
import com.github.transactpro.gateway.model.response.CsvResponse;
import com.github.transactpro.gateway.operation.Operation;
import com.github.transactpro.gateway.validation.ReportGroup;

public class Report extends Operation<CsvResponse> {
    {
        requestUri = "/report";
        responseType = CsvResponse.class;
    }

    @Override
    protected ReportRequest buildRequest() {
        return new ReportRequest();
    }

    @Override
    public ReportRequest getRequest() {
        return (ReportRequest) request;
    }

    @Override
    public Class<?> getValidationGroups() {
        return ReportGroup.class;
    }

    public Long getCreatedFrom() {
        return getRequest().getFilterData().getDtCreatedFrom();
    }

    public Report setCreatedFrom(Long timestamp) {
        getRequest().getFilterData().setDtCreatedFrom(timestamp);
        return this;
    }

    public Long getCreatedTo() {
        return getRequest().getFilterData().getDtCreatedTo();
    }

    public Report setCreatedTo(Long timestamp) {
        getRequest().getFilterData().setDtCreatedTo(timestamp);
        return this;
    }

    public Long getFinishedFrom() {
        return getRequest().getFilterData().getDtFinishedFrom();
    }

    public Report setFinishedFrom(Long timestamp) {
        getRequest().getFilterData().setDtFinishedFrom(timestamp);
        return this;
    }

    public Long getFinishedTo() {
        return getRequest().getFilterData().getDtFinishedTo();
    }

    public Report setFinishedTo(Long timestamp) {
        getRequest().getFilterData().setDtFinishedTo(timestamp);
        return this;
    }
}

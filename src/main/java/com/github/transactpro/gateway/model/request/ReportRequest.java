package com.github.transactpro.gateway.model.request;

import com.github.transactpro.gateway.model.Request;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@Accessors(chain = true)
public class ReportRequest extends Request {
    @Valid
    private ReportFilterData filterData;

    @Override
    public void init() {
        filterData = new ReportFilterData();
    }

    @Override
    public String getUri(String baseUri, String requestPath) throws MalformedURLException {
        URL baseURL = new URL(new URL(baseUri), "/");
        String trimmedRequestPath = requestPath.startsWith("/") ? requestPath.substring(1) : requestPath;

        return baseURL.toString() + trimmedRequestPath;
    }
}

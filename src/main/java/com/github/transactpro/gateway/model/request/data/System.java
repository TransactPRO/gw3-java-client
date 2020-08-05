package com.github.transactpro.gateway.model.request.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class System {

    private String userIp;
    private String xForwardedFor;

    private String browserAcceptHeader;
    private Boolean browserJavaEnabled;
    private Boolean browserJavascriptEnabled;
    private String browserLanguage;
    private String browserColorDepth;
    private String browserScreenHeight;
    private String browserScreenWidth;
    private String browserTz;
    private String browserUserAgent;
}

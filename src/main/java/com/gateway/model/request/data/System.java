package com.gateway.model.request.data;

public class System {
    private String userIp;
    private String xForwardedFor;

    public String getUserIp() {
        return userIp;
    }

    public System setUserIp(String userIp) {
        this.userIp = userIp;
        return this;
    }

    public String getxForwardedFor() {
        return xForwardedFor;
    }

    public System setxForwardedFor(String xForwardedFor) {
        this.xForwardedFor = xForwardedFor;
        return this;
    }
}

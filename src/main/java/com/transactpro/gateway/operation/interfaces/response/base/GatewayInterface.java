package com.transactpro.gateway.operation.interfaces.response.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface GatewayInterface extends OperationInterface {

    default String getGatewayTransactionId() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getGateway() != null) {
                return this.getOperation().getResponse().getGateway().getTransactionId();
            }
        }
        return null;
    }

    default Integer getGatewayStatusCode() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getGateway() != null) {
                return this.getOperation().getResponse().getGateway().getStatusCode();
            }
        }
        return null;
    }

    default String getGatewayStatusText() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getGateway() != null) {
                return this.getOperation().getResponse().getGateway().getStatusText();
            }
        }
        return null;
    }

    default String getGatewayRedirectUrl() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getGateway() != null) {
                return this.getOperation().getResponse().getGateway().getRedirectUrl();
            }
        }
        return null;
    }
}

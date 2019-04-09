package com.transactpro.gateway.operation.interfaces.response.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface ErrorInterface extends OperationInterface {

    default Integer getErrorCode() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getError() != null) {
                return this.getOperation().getResponse().getError().getCode();
            }
        }
        return null;
    }

    default String getErrorMessage() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getError() != null) {
                return this.getOperation().getResponse().getError().getMessage();
            }
        }
        return null;
    }
}

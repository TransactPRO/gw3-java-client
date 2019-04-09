package com.transactpro.gateway.operation.interfaces.response.base;

import com.transactpro.gateway.operation.interfaces.OperationInterface;

public interface AcquirerDetailsInterface extends OperationInterface {

    default Integer getAcquirerDetailsEciSLi() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getEciSLi();
            }
        }
        return null;
    }

    default String getAcquirerDetailsTerminalId() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getTerminalId();
            }
        }
        return null;
    }

    default String getAcquirerDetailsTransactionId() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getTransactionId();
            }
        }
        return null;
    }

    default String getAcquirerDetailsResultCode() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getResultCode();
            }
        }
        return null;
    }

    default String getAcquirerDetailsStatusText() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getStatusText();
            }
        }
        return null;
    }

    default String getAcquirerDetailsStatusDescription() {
        if (this.getOperation().getResponse() != null) {
            if (this.getOperation().getResponse().getAcquirerDetails() != null) {
                return this.getOperation().getResponse().getAcquirerDetails().getStatusDescription();
            }
        }
        return null;
    }
}

package com.transactpro.gateway.operation;


import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.model.Response;
import com.transactpro.gateway.operation.interfaces.response.ResponseInterface;
import lombok.Getter;
import lombok.Setter;

import javax.validation.groups.Default;

/**
 * Base class for operations - info, transaction, verify
 */
public abstract class Operation implements Operable, ResponseInterface {

    @Getter
    protected Request request;
    @Getter
    @Setter
    protected Response response;
    @Getter
    protected String method = "POST";

    public Operation() {
        request = new Request();
    }

    /**
     * Default method to check response validity.
     *
     * @return
     */
    public boolean isSuccessful() {
        return response != null && getErrorCode() == null;
    }

    /**
     * Default validation group
     *
     * @return validation group for Javax validation
     */
    @Override
    public Class getValidationGroups() {
        return Default.class;
    }

    /**
     * Map Status enum to gateway response code.
     *
     * @return Status enum or null
     */
    public Status status() {
        if (response != null) {
            if (response.getGateway() != null) {
                return Status.getByCode(response.getGateway().getStatusCode());
            }
        }
        return null;
    }
}

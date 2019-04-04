package com.gateway.operation;


import com.gateway.model.Request;
import com.gateway.model.Response;
import com.gateway.model.response.Error;
import lombok.Getter;
import lombok.Setter;

import javax.validation.groups.Default;

/**
 * Base class for operations - info, transaction, verify
 */
public abstract class Operation implements Operable {

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
        if (getError() != null) {
            return getError().getMessage() == null;
        }
        return false;
    }

    /**
     * Default validation group
     *
     * @return
     */
    @Override
    public Class getValidationGroups() {
        return Default.class;
    }

    /**
     *
     * @return
     */
    public Error getError() {
        return response != null ? getError() : null;
    }
}

package com.transactpro.gateway.operation;


import com.transactpro.gateway.model.Request;
import com.transactpro.gateway.model.Response;
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
     * Default validation group
     *
     * @return validation group for Javax validation
     */
    @Override
    public Class getValidationGroups() {
        return Default.class;
    }
}

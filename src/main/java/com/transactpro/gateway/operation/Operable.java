package com.transactpro.gateway.operation;

public interface Operable {
    String getRequestUri();

    Class<?> getValidationGroups();
}

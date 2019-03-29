package com.gateway.operation;

public interface Operable {
    String getRequestUri();

    Class<?> getValidationGroups();
}

package com.github.transactpro.gateway.model.digest.exception;

import com.github.transactpro.gateway.model.exception.ResponseException;

public class DigestMissingException extends ResponseException {
    public DigestMissingException(String message) {
        super(message);
    }

    public DigestMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigestMissingException(Throwable cause) {
        super(cause);
    }

    public DigestMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

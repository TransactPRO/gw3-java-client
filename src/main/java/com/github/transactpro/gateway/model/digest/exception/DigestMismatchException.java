package com.github.transactpro.gateway.model.digest.exception;

import com.github.transactpro.gateway.model.exception.ResponseException;

public class DigestMismatchException extends ResponseException {
    public DigestMismatchException(String message) {
        super(message);
    }

    public DigestMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigestMismatchException(Throwable cause) {
        super(cause);
    }

    public DigestMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

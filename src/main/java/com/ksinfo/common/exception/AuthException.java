package com.ksinfo.common.exception;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String            message;

    public AuthException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.ksinfo.common.exception;

public class PasswordChangeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String            message;

    public PasswordChangeException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

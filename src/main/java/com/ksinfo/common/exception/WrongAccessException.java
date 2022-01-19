package com.ksinfo.common.exception;

public class WrongAccessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String            message;

    public WrongAccessException(String message) {
        super();
        this.message = message;
    }

    public WrongAccessException(String message, Throwable cause) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
	
}
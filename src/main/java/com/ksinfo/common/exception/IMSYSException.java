package com.ksinfo.common.exception;

public class IMSYSException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String            message;

    public IMSYSException(String message) {
        super();
        this.message = message;
    }

    public IMSYSException(String message, Throwable cause) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
	
}
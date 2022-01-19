package com.ksinfo.employees.exception;

public class EmpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String            message;

    public EmpException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

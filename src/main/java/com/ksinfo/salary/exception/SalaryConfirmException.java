package com.ksinfo.salary.exception;

public class SalaryConfirmException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String            message;

    public SalaryConfirmException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

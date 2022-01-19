package com.ksinfo.salary.exception;

public class SalaryPayrollException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String            message;

    public SalaryPayrollException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

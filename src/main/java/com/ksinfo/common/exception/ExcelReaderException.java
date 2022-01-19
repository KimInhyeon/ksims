package com.ksinfo.common.exception;

public class ExcelReaderException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    private String            message;
    
    public ExcelReaderException(String message) {
    	super();
    	this.message = message;
    }
    
    public ExcelReaderException(String message, Throwable cause) {
    	super();
    	this.message=message;
    }

    @Override
    public String getMessage() {
    	return message;
    }

}

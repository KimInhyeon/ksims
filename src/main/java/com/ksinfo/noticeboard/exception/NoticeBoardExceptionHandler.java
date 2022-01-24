package com.ksinfo.noticeboard.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.ksinfo.noticeboard.util.Script;

@RestController
@ControllerAdvice
public class NoticeBoardExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public String Exception(CustomException e) {
		return Script.back(e.getMessage().toString());
	}
}

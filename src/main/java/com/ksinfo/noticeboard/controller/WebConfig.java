package com.ksinfo.noticeboard.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nhncorp.lucy.security.xss.XssFilter;

@Configuration
public class WebConfig {
	
	@Bean
	public XssFilter xssFilter() {
		return XssFilter.getInstance("lucy-xss.xml",true);
	}
	
}

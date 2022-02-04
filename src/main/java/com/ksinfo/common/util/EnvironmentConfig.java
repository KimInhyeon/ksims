package com.ksinfo.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.nhncorp.lucy.security.xss.XssFilter;

@Configuration
@PropertySource("classpath:properties/appraisal.properties")
@PropertySource("classpath:properties/salary.properties")
@PropertySource("classpath:properties/file_manage.properties")
public class EnvironmentConfig {
	@Bean
	public XssFilter xssFilter() {
		return XssFilter.getInstance("lucy-xss.xml",true);
	}
}

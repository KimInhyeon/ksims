package com.ksinfo.common.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/appraisal.properties")
@PropertySource("classpath:properties/salary.properties")
@PropertySource("classpath:properties/file_manage.properties")
public class EnvironmentConfig {

}

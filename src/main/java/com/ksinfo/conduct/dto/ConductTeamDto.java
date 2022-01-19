package com.ksinfo.conduct.dto;

import java.util.Date;

public class ConductTeamDto {
	
	String emp_id;
	String emp_name;
	String field_name;
	String field_code;
	Date work_year_month;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public Date getWork_year_month() {
		return work_year_month;
	}
	public void setWork_year_month(Date work_year_month) {
		this.work_year_month = work_year_month;
	}
		
}

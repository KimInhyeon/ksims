package com.ksinfo.conduct.dto;

import java.util.Date;

public class ConductScanDto {
	String emp_id;
	String field_code;
	Date work_year_month;
	String file_name;
	String file_url;
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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

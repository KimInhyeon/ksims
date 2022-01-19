package com.ksinfo.appraisal.dto;

public class AppraisalFieldListDto {
	private String field_idx;
	private String field_code;
	private String field_name;
	private String work_start;
	private String work_end;
	private java.util.Date reg_date;
	private String field_addr;
	
	public String getWork_start() {
		return work_start;
	}
	public void setWork_start(String work_start) {
		this.work_start = work_start;
	}
	public String getWork_end() {
		return work_end;
	}
	public void setWork_end(String work_end) {
		this.work_end = work_end;
	}
	public String getField_idx() {
		return field_idx;
	}
	public void setField_idx(String field_idx) {
		this.field_idx = field_idx;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public java.util.Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(java.util.Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getField_addr() {
		return field_addr;
	}
	public void setField_addr(String field_addr) {
		this.field_addr = field_addr;
	}
	
}

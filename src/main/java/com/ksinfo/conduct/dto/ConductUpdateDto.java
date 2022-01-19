package com.ksinfo.conduct.dto;

import java.util.Date;

public class ConductUpdateDto {
	String emp_id;
	String field_code;
	Date work_year_month;
	int plan_work_days;//총근무일
	double work_days;//실제 근무일
	double paid_vacation_days;//휴일
	double worktime; //근무시간
	String file_name; //파일명
	String file_url; //파일 경로 및 시스템파일명
	String rec_update_id;//레코드를 업데이트한 아이디
	

	public int getPlan_work_days() {
		return plan_work_days;
	}
	public void setPlan_work_days(int plan_work_days) {
		this.plan_work_days = plan_work_days;
	}
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
	public double getWork_days() {
		return work_days;
	}
	public void setWork_days(double work_days) {
		this.work_days = work_days;
	}
	public double getPaid_vacation_days() {
		return paid_vacation_days;
	}
	public void setPaid_vacation_days(double paid_vacation_days) {
		this.paid_vacation_days = paid_vacation_days;
	}
	public double getWorktime() {
		return worktime;
	}
	public void setWorktime(double worktime) {
		this.worktime = worktime;
	}
	public String getRec_update_id() {
		return rec_update_id;
	}
	public void setRec_update_id(String rec_update_id) {
		this.rec_update_id = rec_update_id;
	}
}

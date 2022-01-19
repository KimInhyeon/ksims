package com.ksinfo.conduct.dto;

import java.util.Date;

public class ConductWorkDto {
	String emp_id;
	String reg_emp_id;//근무표를 등록한 사람
	String field_code;
	String field_name;
	String emp_name;
	Date work_year_month;//근무월
	int plan_work_days;//총근무일 
	double work_days;//실제 근무일
	double paid_vacation_days;//휴일
	double worktime; //근무시간
	String file_name; //파일 원래 이름
	String file_url; // 바뀐파일명
	String rec_create_id;//최초로 레코드를 작성한 아이디
	String rec_update_id;//
	boolean logical_del_flg;
	String leader_code;
	String comp_flg;
	
	String sheet_number;

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
	public String getLeader_code() {
		return leader_code;
	}
	public void setLeader_code(String leader_code) {
		this.leader_code = leader_code;
	}
	public String getRec_update_id() {
		return rec_update_id;
	}
	public void setRec_update_id(String rec_update_id) {
		this.rec_update_id = rec_update_id;
	}
	public String getRec_create_id() {
		return rec_create_id;
	}
	public void setRec_create_id(String rec_create_id) {
		this.rec_create_id = rec_create_id;
	}
	public String getReg_emp_id() {
		return reg_emp_id;
	}
	public void setReg_emp_id(String reg_emp_id) {
		this.reg_emp_id = reg_emp_id;
	}
	public boolean isLogical_del_flg() {
		return logical_del_flg;
	}
	public void setLogical_del_flg(boolean logical_del_flg) {
		this.logical_del_flg = logical_del_flg;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getWork_year_month() {
		return work_year_month;
	}
	public void setWork_year_month(Date work_year_month) {
		this.work_year_month = work_year_month;
	}
	public double getPaid_vacation_days() {
		return paid_vacation_days;
	}
	public void setPaid_vacation_days(double paid_vacation_days) {
		this.paid_vacation_days = paid_vacation_days;
	}
	public double getWork_days() {
		return work_days;
	}
	public void setWork_days(double work_days) {
		this.work_days = work_days;
	}
	public double getWorktime() {
		return worktime;
	}
	public void setWorktime(double worktime) {
		this.worktime = worktime;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getComp_flg() {
		return comp_flg;
	}
	public void setComp_flg(String comp_flg) {
		this.comp_flg = comp_flg;
	}
	public String getSheet_number() {
		return sheet_number;
	}
	public void setSheet_number(String sheet_number) {
		this.sheet_number = sheet_number;
	}
	
	
}

package com.ksinfo.field.dto;


public class FieldInfoDetailDto {
	private String field_name;
	private String field_addr;
	private String field_difficulty;
	private String work_start_time;
	private String work_end_time;
	private String work_break_time1;
	private String work_break_time2;
	private String field_memo;
	private String field_code;
	private String reg_date;
	private String leader_check;
	private String emp_name;
	//　追加カラム
	private String field_tool;
	private String field_env;
	
	private String position_name;
	private String emp_gender;
	
	public String getField_tool() {
		return field_tool;
	}
	public void setField_tool(String field_tool) {
		this.field_tool = field_tool;
	}
	public String getField_env() {
		return field_env;
	}
	public void setField_env(String field_env) {
		this.field_env = field_env;
	}
	public String getLeader_check() {
		return leader_check;
	}
	public void setLeader_check(String leader_check) {
		this.leader_check = leader_check;
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
	public String getField_addr() {
		return field_addr;
	}
	public void setField_addr(String field_addr) {
		this.field_addr = field_addr;
	}
	public String getField_difficulty() {
		return field_difficulty;
	}
	public void setField_difficulty(String field_difficulty) {
		this.field_difficulty = field_difficulty;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getWork_start_time() {
		return work_start_time;
	}
	public void setWork_start_time(String work_start_time) {
		this.work_start_time = work_start_time;
	}
	public String getWork_end_time() {
		return work_end_time;
	}
	public void setWork_end_time(String work_end_time) {
		this.work_end_time = work_end_time;
	}
	public String getWork_break_time1() {
		return work_break_time1;
	}
	public void setWork_break_time1(String work_break_time1) {
		this.work_break_time1 = work_break_time1;
	}
	public String getWork_break_time2() {
		return work_break_time2;
	}
	public void setWork_break_time2(String work_break_time2) {
		this.work_break_time2 = work_break_time2;
	}
	public String getField_memo() {
		return field_memo;
	}
	public void setField_memo(String field_memo) {
		this.field_memo = field_memo;
	}
//	public String getEmp_id() {
//		return emp_id;
//	}
//	public void setEmp_id(String emp_id) {
//		this.emp_id = emp_id;
//	}
	
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_n(String position_name) {
		this.position_name = position_name;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	
	
	
}

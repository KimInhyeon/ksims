package com.ksinfo.appraisal.dto;

public class AppraisalModalDto {
	private String emp_id;
	private String emp_name;
	private String leader_code;
	private boolean self_app_flg=false;
	private boolean leader_app_flg=false;
	private boolean customer_app_flg=false;
	private boolean perfomance_app_flg=false;
	private boolean accept_flg=false;
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
	public String getLeader_code() {
		return leader_code;
	}
	public void setLeader_code(String leader_code) {
		this.leader_code = leader_code;
	}
	public boolean isSelf_app_flg() {
		return self_app_flg;
	}
	public void setSelf_app_flg(boolean self_app_flg) {
		this.self_app_flg = self_app_flg;
	}
	public boolean isLeader_app_flg() {
		return leader_app_flg;
	}
	public void setLeader_app_flg(boolean leader_app_flg) {
		this.leader_app_flg = leader_app_flg;
	}
	public boolean isCustomer_app_flg() {
		return customer_app_flg;
	}
	public void setCustomer_app_flg(boolean customer_app_flg) {
		this.customer_app_flg = customer_app_flg;
	}
	public boolean isPerfomance_app_flg() {
		return perfomance_app_flg;
	}
	public void setPerfomance_app_flg(boolean perfomance_app_flg) {
		this.perfomance_app_flg = perfomance_app_flg;
	}
	public boolean isAccept_flg() {
		return accept_flg;
	}
	public void setAccept_flg(boolean accept_flg) {
		this.accept_flg = accept_flg;
	}
	
	
}

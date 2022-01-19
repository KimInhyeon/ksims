package com.ksinfo.appraisal.dto;

public class AppraisalEmpDto {
	private String emp_id;
	private String emp_name;
	private int work_year;
	private double sum_app;
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
	public int getWork_year() {
		return work_year;
	}
	public void setWork_year(int work_year) {
		this.work_year = work_year;
	}
	public double getSum_app() {
		return sum_app;
	}
	public void setSum_app(double sum_app) {
		this.sum_app = sum_app;
	}
	
}

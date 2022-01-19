package com.ksinfo.salary.dto;

import org.springframework.stereotype.Component;

@Component
public class SalaryConfirmDto {
	private String empId;
	private String empName;
	private String positionName;
	private int salary;
	private String compFlg;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getCompFlg() {
		return compFlg;
	}
	public void setCompFlg(String compFlg) {
		this.compFlg = compFlg;
	}
	
}

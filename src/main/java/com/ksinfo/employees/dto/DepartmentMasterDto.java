package com.ksinfo.employees.dto;

import java.sql.Timestamp;

public class DepartmentMasterDto {
	
	private int departmentMstIdx;
	private String departmentCode;
	private String departmentName;
	private String recCreateId;
	private Timestamp recCreateDate;
	private String recUpdateId;
	private Timestamp recUpdateDate;

	public int getDepartmentMstIdx() {
		return departmentMstIdx;
	}
	public void setDepartmentMstIdx(int departmentMstIdx) {
		this.departmentMstIdx = departmentMstIdx;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRecCreateId() {
		return recCreateId;
	}
	public void setRecCreateId(String recCreateId) {
		this.recCreateId = recCreateId;
	}
	public Timestamp getRecCreateDate() {
		return recCreateDate;
	}
	public void setRecCreateDate(Timestamp recCreateDate) {
		this.recCreateDate = recCreateDate;
	}
	public String getRecUpdateId() {
		return recUpdateId;
	}
	public void setRecUpdateId(String recUpdateId) {
		this.recUpdateId = recUpdateId;
	}
	public Timestamp getRecUpdateDate() {
		return recUpdateDate;
	}
	public void setRecUpdateDate(Timestamp recUpdateDate) {
		this.recUpdateDate = recUpdateDate;
	}
}

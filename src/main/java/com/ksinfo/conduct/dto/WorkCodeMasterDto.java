package com.ksinfo.conduct.dto;

import java.sql.Timestamp;

public class WorkCodeMasterDto {
	
	private String workCode;
	private String workName;
	private String recCreateId;
	private Timestamp recCreateDate;
	private String recUpdateId;
	private Timestamp recUpdateDate;

	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
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

package com.ksinfo.employees.dto;

import java.sql.Timestamp;

public class PositionMasterDto {
	
	private int positionMstIdx;
	private String positionCode;
	private String positionName;
	private String recCreateId;
	private Timestamp recCreateDate;
	private String recUpdateId;
	private Timestamp recUpdateDate;

	public int getPositionMstIdx() {
		return positionMstIdx;
	}
	public void setPositionMstIdx(int positionMstIdx) {
		this.positionMstIdx = positionMstIdx;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

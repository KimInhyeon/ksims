package com.ksinfo.employees.dto;

public class PubFileDto {
	
	private long pubFileIdx;
	private String empId;
	private String pubFileName;
	private String pubFileUrl;
	private java.util.Date pubFileUpdateDate;
	private boolean pubLogicalDelFlg;
	
	
	public long getPubFileIdx() {
		return pubFileIdx;
	}
	public void setPubFileIdx(long pubFileIdx) {
		this.pubFileIdx = pubFileIdx;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPubFileName() {
		return pubFileName;
	}
	public void setPubFileName(String pubFileName) {
		this.pubFileName = pubFileName;
	}
	public String getPubFileUrl() {
		return pubFileUrl;
	}
	public void setPubFileUrl(String pubFileUrl) {
		this.pubFileUrl = pubFileUrl;
	}
	public java.util.Date getPubFileUpdateDate() {
		return pubFileUpdateDate;
	}
	public void setPubFileUpdateDate(java.util.Date pubFileUpdateDate) {
		this.pubFileUpdateDate = pubFileUpdateDate;
	}
	public boolean isPubLogicalDelFlg() {
		return pubLogicalDelFlg;
	}
	public void setPubLogicalDelFlg(boolean pubLogicalDelFlg) {
		this.pubLogicalDelFlg = pubLogicalDelFlg;
	}


}

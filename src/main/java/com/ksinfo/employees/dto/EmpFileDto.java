package com.ksinfo.employees.dto;

public class EmpFileDto {
	
	private int empFileIdx;
	private String empId;
	private int empIdx;
	private boolean photoFlg;
	private String fileName;
	private String fileUrl;
	private String fileUpdateDate;
	private boolean logicalDelFlg;

	public int getEmpFileIdx() {
		return empFileIdx;
	}
	public void setEmpFileIdx(int empFileIdx) {
		this.empFileIdx = empFileIdx;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public boolean isPhotoFlg() {
		return photoFlg;
	}
	public void setPhotoFlg(boolean photoFlg) {
		this.photoFlg = photoFlg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileUpdateDate() {
		return fileUpdateDate;
	}
	public void setFileUpdateDate(String fileUpdateDate) {
		this.fileUpdateDate = fileUpdateDate;
	}
	public boolean isLogicalDelFlg() {
		return logicalDelFlg;
	}
	public void setLogicalDelFlg(boolean logicalDelFlg) {
		this.logicalDelFlg = logicalDelFlg;
	}
	public int getEmpIdx() {
		return empIdx;
	}
	public void setEmpIdx(int empIdx) {
		this.empIdx = empIdx;
	}

	
}

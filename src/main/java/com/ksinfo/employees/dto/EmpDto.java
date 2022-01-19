package com.ksinfo.employees.dto;

import com.ksinfo.common.util.ObjectUtil;

public class EmpDto {
	
	private int empIdx;
	private long historyNo;
	private long maxHistoryNo;
	private int empSeq;
	private String empId;
	private String password;
	private boolean partnerCompFlg;
	private String positionCode;
	private String positionName;
	private String fieldCode;
	private String empName;
	private String empAge;
	private String empGender;
	private String departmentCode;
	private String empAddr;
	private String phoneNumber;
	private int dependentCount;
	private java.util.Date hiredDate;
	private java.util.Date stayExpirationDate;
	private java.util.Date dormInDate;
	private java.util.Date dormOutDate;
	private boolean empCareerFlg;
	private int empCareerPoint;
	private boolean healthInsuranceFlg;
	private boolean pensionFlg;
	private boolean empInsuranceFlg;
	private String authCode;
	private boolean resignFlg;
	private java.util.Date resignDate;
	private boolean logicalDelFlg;
	//追加-20210607
	private String nationality;
	private String stayExpirationNumber;
	private String empNameKana;
	private String empNameEng;
	private boolean empSpouseFlg;
	private String residentRegistrationNumber;
	private String companyMail;
	private String myNumber;
	private String pensionNumber;
	private String bankBookNumber;
	private String emergencyPhoneNumber;
	//追加-20210712
	private String empPostNo;
	private String bankName;
	private String bankNumGubun;
	private String bankBran;
	private String empSectionName;
	private int empPayLevel;
	//追加-20210802
	private String empFile;
	private String empUrl;
	
	//追加-20220110
	private String empRelation;
	
	public String getEmpFile() {
		return empFile;
	}
	public void setEmpFile(String empFile) {
		this.empFile = empFile;
	}
	public String getEmpUrl() {
		return empUrl;
	}
	public void setEmpUrl(String empUrl) {
		this.empUrl = empUrl;
	}
	public long getHistoryNo() {
		return historyNo;
	}
	public void setHistoryNo(long historyNo) {
		this.historyNo = historyNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getStayExpirationNumber() {
		if (!ObjectUtil.isEmpty(stayExpirationNumber)) {
			stayExpirationNumber = stayExpirationNumber.trim();
		}
		return stayExpirationNumber;
	}
	public void setStayExpirationNumber(String stayExpirationNumber) {
		this.stayExpirationNumber = stayExpirationNumber;
	}
	public String getEmpNameKana() {
		return empNameKana;
	}
	public void setEmpNameKana(String empNameKana) {
		this.empNameKana = empNameKana;
	}
	public String getEmpNameEng() {
		return empNameEng;
	}
	public void setEmpNameEng(String empNameEng) {
		this.empNameEng = empNameEng;
	}
	public boolean isEmpSpouseFlg() {
		return empSpouseFlg;
	}
	public void setEmpSpouseFlg(boolean empSpouseFlg) {
		this.empSpouseFlg = empSpouseFlg;
	}
	public String getResidentRegistrationNumber() {
		return residentRegistrationNumber;
	}
	public void setResidentRegistrationNumber(String residentRegistrationNumber) {
		this.residentRegistrationNumber = residentRegistrationNumber;
	}
	public String getCompanyMail() {
		return companyMail;
	}
	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}
	public String getMyNumber() {
		if (!ObjectUtil.isEmpty(myNumber)) {
			myNumber = myNumber.trim();
		}
		return myNumber;
	}
	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}
	public String getPensionNumber() {
		return pensionNumber;
	}
	public void setPensionNumber(String pensionNumber) {
		this.pensionNumber = pensionNumber;
	}
	public String getBankBookNumber() {
		return bankBookNumber;
	}
	public void setBankBookNumber(String bankBookNumber) {
		this.bankBookNumber = bankBookNumber;
	}
	public String getEmergencyPhoneNumber() {
		return emergencyPhoneNumber;
	}
	public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
		this.emergencyPhoneNumber = emergencyPhoneNumber;
	}
	
	public int getEmpIdx() {
		return empIdx;
	}
	public void setEmpIdx(int empIdx) {
		this.empIdx = empIdx;
	}
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPartnerCompFlg() {
		return partnerCompFlg;
	}
	public void setPartnerCompFlg(boolean partnerCompFlg) {
		this.partnerCompFlg = partnerCompFlg;
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
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getDependentCount() {
		return dependentCount;
	}
	public void setDependentCount(int dependentCount) {
		this.dependentCount = dependentCount;
	}
	public java.util.Date getHiredDate() {
		return hiredDate;
	}
	public void setHiredDate(java.util.Date hiredDate) {
		this.hiredDate = hiredDate;
	}
	public java.util.Date getStayExpirationDate() {
		return stayExpirationDate;
	}
	public void setStayExpirationDate(java.util.Date stayExpirationDate) {
		this.stayExpirationDate = stayExpirationDate;
	}
	public java.util.Date getDormInDate() {
		return dormInDate;
	}
	public void setDormInDate(java.util.Date dormInDate) {
		this.dormInDate = dormInDate;
	}
	public java.util.Date getDormOutDate() {
		return dormOutDate;
	}
	public void setDormOutDate(java.util.Date dormOutDate) {
		this.dormOutDate = dormOutDate;
	}
	public boolean isEmpCareerFlg() {
		return empCareerFlg;
	}
	public void setEmpCareerFlg(boolean empCareerFlg) {
		this.empCareerFlg = empCareerFlg;
	}
	public int getEmpCareerPoint() {
		return empCareerPoint;
	}
	public void setEmpCareerPoint(int empCareerPoint) {
		this.empCareerPoint = empCareerPoint;
	}
	public boolean isHealthInsuranceFlg() {
		return healthInsuranceFlg;
	}
	public void setHealthInsuranceFlg(boolean healthInsuranceFlg) {
		this.healthInsuranceFlg = healthInsuranceFlg;
	}
	public boolean isPensionFlg() {
		return pensionFlg;
	}
	public void setPensionFlg(boolean pensionFlg) {
		this.pensionFlg = pensionFlg;
	}
	public boolean isEmpInsuranceFlg() {
		return empInsuranceFlg;
	}
	public void setEmpInsuranceFlg(boolean empInsuranceFlg) {
		this.empInsuranceFlg = empInsuranceFlg;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public boolean isResignFlg() {
		return resignFlg;
	}
	public void setResignFlg(boolean resignFlg) {
		this.resignFlg = resignFlg;
	}
	public java.util.Date getResignDate() {
		return resignDate;
	}
	public void setResignDate(java.util.Date resignDate) {
		this.resignDate = resignDate;
	}
	public boolean isLogicalDelFlg() {
		return logicalDelFlg;
	}
	public void setLogicalDelFlg(boolean logicalDelFlg) {
		this.logicalDelFlg = logicalDelFlg;
	}
	public String getEmpPostNo() {
		return empPostNo;
	}
	public void setEmpPostNo(String empPostNo) {
		this.empPostNo = empPostNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBran() {
		return bankBran;
	}
	public void setBankBran(String bankBran) {
		this.bankBran = bankBran;
	}
	public String getBankNumGubun() {
		return bankNumGubun;
	}
	public void setBankNumGubun(String bankNumGubun) {
		this.bankNumGubun = bankNumGubun;
	}
	public String getEmpSectionName() {
		return empSectionName;
	}
	public void setEmpSectionName(String empSectionName) {
		this.empSectionName = empSectionName;
	}
	public int getEmpPayLevel() {
		return empPayLevel;
	}
	public void setEmpPayLevel(int empPayLevel) {
		this.empPayLevel = empPayLevel;
	}
	
	public String getEmpRelation() {
		return empRelation;
	}
	public void setEmpRelation(String empRelation) {
		this.empRelation = empRelation;
	}
	public long getMaxHistoryNo() {
		return maxHistoryNo;
	}
	public void setMaxHistoryNo(long maxHistoryNo) {
		this.maxHistoryNo = maxHistoryNo;
	}

}

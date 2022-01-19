package com.ksinfo.order.dto;

import org.springframework.stereotype.Component;

@Component
public class ObtainOrdeDto {

	private String pjectNum;
	private String estimateNum;
	private String registrationDate;
	private Integer persNum;
	private String persName;
	private String upriceClassify;
	private String upriceAmount;
	private Integer startStandardWorkTime;
	private Integer endStandardWorkTime;
	private String excessUprice;
	private String deductionUprice;
	
	
	public String getPjectNum() {
		return pjectNum;
	}
	public void setPjectNum(String pjectNum) {
		this.pjectNum = pjectNum;
	}
	public String getEstimateNum() {
		return estimateNum;
	}
	public void setEstimateNum(String estimateNum) {
		this.estimateNum = estimateNum;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Integer getPersNum() {
		return persNum;
	}
	public void setPersNum(Integer persNum) {
		this.persNum = persNum;
	}
	public String getPersName() {
		return persName;
	}
	public void setPersName(String persName) {
		this.persName = persName;
	}
	public String getUpriceClassify() {
		return upriceClassify;
	}
	public void setUpriceClassify(String upriceClassify) {
		this.upriceClassify = upriceClassify;
	}
	public String getUpriceAmount() {
		return upriceAmount;
	}
	public void setUpriceAmount(String upriceAmount) {
		this.upriceAmount = upriceAmount;
	}
	public Integer getStartStandardWorkTime() {
		return startStandardWorkTime;
	}
	public void setStartStandardWorkTime(Integer startStandardWorkTime) {
		this.startStandardWorkTime = startStandardWorkTime;
	}
	public Integer getEndStandardWorkTime() {
		return endStandardWorkTime;
	}
	public void setEndStandardWorkTime(Integer endStandardWorkTime) {
		this.endStandardWorkTime = endStandardWorkTime;
	}
	public String getExcessUprice() {
		return excessUprice;
	}
	public void setExcessUprice(String excessUprice) {
		this.excessUprice = excessUprice;
	}
	public String getDeductionUprice() {
		return deductionUprice;
	}
	public void setDeductionUprice(String deductionUprice) {
		this.deductionUprice = deductionUprice;
	}

	
}

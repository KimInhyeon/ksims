package com.ksinfo.salary.dto;

import org.springframework.stereotype.Component;

@Component
public class InsuranceDto {
	private Double over;
	private Double less;
	private String link;
	private Double healthAll;
	private Double healthHalf;
	private Double healthCareAll;
	private Double healthCareHalf;
	private Double pensionAll;
	private Double pensionHalf;
	private int insuranceNo;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Double getHealthAll() {
		return healthAll;
	}
	public void setHealthAll(Double healthAll) {
		this.healthAll = healthAll;
	}
	public Double getHealthHalf() {
		return healthHalf;
	}
	public void setHealthHalf(Double healthHalf) {
		this.healthHalf = healthHalf;
	}
	public Double getHealthCareAll() {
		return healthCareAll;
	}
	public void setHealthCareAll(Double healthCareAll) {
		this.healthCareAll = healthCareAll;
	}
	public Double getealthCareHalf() {
		return healthCareHalf;
	}
	public void setHealthCareHalf(Double healthCareHalf) {
		this.healthCareHalf = healthCareHalf;
	}
	public Double getPensionAll() {
		return pensionAll;
	}
	public void setPensionAll(Double pensionAll) {
		this.pensionAll = pensionAll;
	}
	public Double getPensionHalf() {
		return pensionHalf;
	}
	public void setPensionHalf(Double pensionHalf) {
		this.pensionHalf = pensionHalf;
	}
	public int getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(int insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public Double getOver() {
		return over;
	}
	public void setOver(Double over) {
		this.over = over;
	}
	public Double getLess() {
		return less;
	}
	public void setLess(Double less) {
		this.less = less;
	}
	
	

}

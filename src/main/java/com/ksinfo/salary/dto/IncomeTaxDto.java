package com.ksinfo.salary.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class IncomeTaxDto {
	private int over;
	private int less;
	private int supportFamilyZero;	
	private int supportFamilyOne;
	private int supportFamilyTwo;
	private int supportFamilyThree;
	private int supportFamilyFour;
	private int supportFamilyFive;
	private int supportFamilySix;
	private int supportFamilySeven;
	private int extra;
	private int incomeTaxNo;
	private Date modDateTable;
	
	public int getOver() {
		return over;
	}
	public void setOver(int over) {
		this.over = over;
	}
	public int getLess() {
		return less;
	}
	public void setLess(int less) {
		this.less = less;
	}
	public int getSupportFamilyZero() {
		return supportFamilyZero;
	}
	public void setSupportFamilyZero(int supportFamilyZero) {
		this.supportFamilyZero = supportFamilyZero;
	}
	public int getSupportFamilyOne() {
		return supportFamilyOne;
	}
	public void setSupportFamilyOne(int supportFamilyOne) {
		this.supportFamilyOne = supportFamilyOne;
	}
	public int getSupportFamilyTwo() {
		return supportFamilyTwo;
	}
	public void setSupportFamilyTwo(int supportFamilyTwo) {
		this.supportFamilyTwo = supportFamilyTwo;
	}
	public int getSupportFamilyThree() {
		return supportFamilyThree;
	}
	public void setSupportFamilyThree(int supportFamilyThree) {
		this.supportFamilyThree = supportFamilyThree;
	}
	public int getSupportFamilyFour() {
		return supportFamilyFour;
	}
	public void setSupportFamilyFour(int supportFamilyFour) {
		this.supportFamilyFour = supportFamilyFour;
	}
	public int getSupportFamilyFive() {
		return supportFamilyFive;
	}
	public void setSupportFamilyFive(int supportFamilyFive) {
		this.supportFamilyFive = supportFamilyFive;
	}
	public int getSupportFamilySix() {
		return supportFamilySix;
	}
	public void setSupportFamilySix(int supportFamilySix) {
		this.supportFamilySix = supportFamilySix;
	}
	public int getSupportFamilySeven() {
		return supportFamilySeven;
	}
	public void setSupportFamilySeven(int supportFamilySeven) {
		this.supportFamilySeven = supportFamilySeven;
	}
	public int getExtra() {
		return extra;
	}
	public void setExtra(int extra) {
		this.extra = extra;
	}
	public int getIncomeTaxNo() {
		return incomeTaxNo;
	}
	public void setIncomeTaxNo(int incomeTaxNo) {
		this.incomeTaxNo = incomeTaxNo;
	}
	public Date getModDateTable() {
		return modDateTable;
	}
	public void setModDateTable(Date modDateTable) {
		this.modDateTable = modDateTable;
	}
	
	

}

package com.ksinfo.salary.dto;

import org.springframework.stereotype.Component;

@Component
public class SalarymgtDto {
	
	private int salNo;
	private int conductIdx;
	private String year;	//지급기준연도
	private String month;	//지급기준월
	private String paymentYear;	//지급한 연도--comp_flg true변경시 같이 변경
	private String paymentMonth;	//지급한 날짜--comp_flg true변경시 같이 변경
	private String departmentName;
	private String empId;
	private String empName;	//사원명 
	private int planWorkDays;	//노동일수
	private float workDays;
	private float paidVacationDays; //유급휴가
	
	private int personalReasonVacationDays;
	private int absenteeismDays;
	private int tardinessCount;
	private int leaveEarlyCount;
	private float overtime; //시간외근무
	private Integer salary;
	private int basePay;
	private int positionPay;
	private int certificationPay;
	private int housingAllowance;
	private int overtimePay;
	private int transportationPay;
	private int endYearPay;
	private int fixedOvertimePay;
	private int extraCost;
	private int incentive;
	private int extraPay;
	private int absentDeductied;
	private int totalSalary;
	private int taxableProperty;
	private int caringInsurance;
	private int healthInsurance;
	private int pension;
	private int employedInsurance;
	private int companyInsurance;
	private int incomeTax;
	private int citizenTax;
	private int dormDeduction;
	private int lifeInsurance;
	private int accumulationMoney;
	private int debtFinished;
	private int totalDeduction;
	private String compFlg;
	private String rec_id;
	
	private String healthInsuranceFlg;	//건강보험 flg
	private String pensionFlg;	//후생연금 flg
	private String empInsuranceFlg;	//고용보험 flg	
	private int overtimePaySum; //잔업수당 계산
	private int dependentCount; //부양가족 수
	
	public int getSalNo() {
		return salNo;
	}
	public void setSalNo(int salNo) {
		this.salNo = salNo;
	}
	public void setConductIdx(int conductIdx) {
		this.conductIdx = conductIdx;
	}
	public int getConductIdx() {
		return conductIdx;
	}
	public void setCondcutIdx(int condcutIdx) {
		this.conductIdx = condcutIdx;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPaymentYear() {
		return paymentYear;
	}
	public void setPaymentYear(String paymentYear) {
		this.paymentYear = paymentYear;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getPlanWorkDays() {
		return planWorkDays;
	}
	public void setPlanWorkDays(int planWorkDays) {
		this.planWorkDays = planWorkDays;
	}
	public float getWorkDays() {
		return workDays;
	}
	public void setWorkDays(float workDays) {
		this.workDays = workDays;
	}
	public float getPaidVacationDays() {
		return paidVacationDays;
	}
	public void setPaidVacationDays(float paidVacationDays) {
		this.paidVacationDays = paidVacationDays;
	}
	public int getPersonalReasonVacationDays() {
		return personalReasonVacationDays;
	}
	public void setPersonalReasonVacationDays(int personalReasonVacationDays) {
		this.personalReasonVacationDays = personalReasonVacationDays;
	}
	public int getAbsenteeismDays() {
		return absenteeismDays;
	}
	public void setAbsenteeismDays(int absenteeismDays) {
		this.absenteeismDays = absenteeismDays;
	}
	public int getTardinessCount() {
		return tardinessCount;
	}
	public void setTardinessCount(int tardinessCount) {
		this.tardinessCount = tardinessCount;
	}
	public int getLeaveEarlyCount() {
		return leaveEarlyCount;
	}
	public void setLeaveEarlyCount(int leaveEarlyCount) {
		this.leaveEarlyCount = leaveEarlyCount;
	}
	public float getOvertime() {
		return overtime;
	}
	public void setOvertime(float overtime) {
		this.overtime = overtime;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public int getBasePay() {
		return basePay;
	}
	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}
	public int getPositionPay() {
		return positionPay;
	}
	public void setPositionPay(int positionPay) {
		this.positionPay = positionPay;
	}
	public int getCertificationPay() {
		return certificationPay;
	}
	public void setCertificationPay(int certificationPay) {
		this.certificationPay = certificationPay;
	}
	public int getHousingAllowance() {
		return housingAllowance;
	}
	public void setHousingAllowance(int housingAllowance) {
		this.housingAllowance = housingAllowance;
	}
	public int getOvertimePay() {
		return overtimePay;
	}
	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}
	public int getTransportationPay() {
		return transportationPay;
	}
	public void setTransportationPay(int transportationPay) {
		this.transportationPay = transportationPay;
	}
	public int getEndYearPay() {
		return endYearPay;
	}
	public void setEndYearPay(int endYearPay) {
		this.endYearPay = endYearPay;
	}
	public int getFixedOvertimePay() {
		return fixedOvertimePay;
	}
	public void setFixedOvertimePay(int fixedOvertimePay) {
		this.fixedOvertimePay = fixedOvertimePay;
	}
	public int getExtraCost() {
		return extraCost;
	}
	public void setExtraCost(int extraCost) {
		this.extraCost = extraCost;
	}
	public int getIncentive() {
		return incentive;
	}
	public void setIncentive(int incentive) {
		this.incentive = incentive;
	}
	public int getExtraPay() {
		return extraPay;
	}
	public void setExtraPay(int extraPay) {
		this.extraPay = extraPay;
	}
	public int getAbsentDeductied() {
		return absentDeductied;
	}
	public void setAbsentDeductied(int absentDeductied) {
		this.absentDeductied = absentDeductied;
	}
	public int getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}
	public int getTaxableProperty() {
		return taxableProperty;
	}
	public void setTaxableProperty(int taxableProperty) {
		this.taxableProperty = taxableProperty;
	}
	public int getCaringInsurance() {
		return caringInsurance;
	}
	public void setCaringInsurance(int caringInsurance) {
		this.caringInsurance = caringInsurance;
	}
	public int getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(int healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public int getPension() {
		return pension;
	}
	public void setPension(int pension) {
		this.pension = pension;
	}
	public int getEmployedInsurance() {
		return employedInsurance;
	}
	public void setEmployedInsurance(int employedInsurance) {
		this.employedInsurance = employedInsurance;
	}
	public int getCompanyInsurance() {
		return companyInsurance;
	}
	public void setCompanyInsurance(int companyInsurance) {
		this.companyInsurance = companyInsurance;
	}
	public int getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	public int getCitizenTax() {
		return citizenTax;
	}
	public void setCitizenTax(int citizenTax) {
		this.citizenTax = citizenTax;
	}
	public int getDormDeduction() {
		return dormDeduction;
	}
	public void setDormDeduction(int dormDeduction) {
		this.dormDeduction = dormDeduction;
	}
	public int getLifeInsurance() {
		return lifeInsurance;
	}
	public void setLifeInsurance(int lifeInsurance) {
		this.lifeInsurance = lifeInsurance;
	}
	public int getAccumulationMoney() {
		return accumulationMoney;
	}
	public void setAccumulationMoney(int accumulationMoney) {
		this.accumulationMoney = accumulationMoney;
	}
	public int getDebtFinished() {
		return debtFinished;
	}
	public void setDebtFinished(int debtFinished) {
		this.debtFinished = debtFinished;
	}
	public int getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(int totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public String getCompFlg() {
		return compFlg;
	}
	public void setCompFlg(String compFlg) {
		this.compFlg = compFlg;
	}
	public String getRec_id() {
		return rec_id;
	}
	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}
	
	public String getHealthInsuranceFlg() {
		return healthInsuranceFlg;
	}
	public void setHealthInsuranceFlg(String healthInsuranceFlg) {
		this.healthInsuranceFlg = healthInsuranceFlg;
	}
	public String getPensionFlg() {
		return pensionFlg;
	}
	public void setPensionFlg(String pensionFlg) {
		this.pensionFlg = pensionFlg;
	}
	public String getEmpInsuranceFlg() {
		return empInsuranceFlg;
	}
	public void setEmpInsuranceFlg(String empInsuranceFlg) {
		this.empInsuranceFlg = empInsuranceFlg;
	}
	public int getOvertimePaySum() {
		return overtimePaySum;
	}
	public void setOvertimePaySum(int overtimePaySum) {
		this.overtimePaySum = overtimePaySum;
	}
	public int getDependentCount() {
		return dependentCount;
	}
	public void setDependentCount(int dependentCount) {
		this.dependentCount = dependentCount;
	}
	
}

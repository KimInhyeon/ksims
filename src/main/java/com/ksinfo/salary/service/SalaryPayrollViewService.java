package com.ksinfo.salary.service;

import java.sql.SQLException;

import com.ksinfo.salary.dto.SalarymgtDto;

public interface SalaryPayrollViewService {

	// 21/04/21 Youm
	public SalarymgtDto salaryPayrollList(String emp_id,String year,String month) throws SQLException;
	public SalarymgtDto salaryPayrollPastList(String emp_id) throws SQLException;
	public SalarymgtDto salaryPayrollCdt(String emp_id, String work_year_month) throws SQLException;
	public SalarymgtDto SalaryInsuranceSet(int pay) throws SQLException;
	public int SalaryIncomeSet(int pay,int dependentCount) throws SQLException;
	public String SalaryNextEmp(String year_month,String emp_id) throws SQLException;
	
}

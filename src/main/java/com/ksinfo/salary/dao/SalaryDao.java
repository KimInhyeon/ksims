package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.salary.dto.SalarymgtDto;


public interface SalaryDao {
	
	// 21/04/21
	public List<SalarymgtDto> salaryEmpList(java.util.Map<String, String> condition) throws SQLException;
	public List<SalarymgtDto> salaryEmpOne(@Param("identificationNo") String identificationNo, @Param("year") String year, @Param("month") String month) throws SQLException;
	
	public List<SalarymgtDto> salaryDropDownList(String nowYear, String nowMonth);
	public List<SalarymgtDto> salaryDropDownOne(String year,String month) throws SQLException;
	public List<SalarymgtDto> salaryDropDownEmpList(String emp_id, String targetDate);
	
	public SalarymgtDto salaryPayrollList(String emp_id,String year,String month) throws SQLException;
	public SalarymgtDto salaryPayrollPastList(String emp_id) throws SQLException;
	public SalarymgtDto salaryPayrollCdt(String emp_id, String work_year_month) throws SQLException;
	public SalarymgtDto SalaryInsuranceSet(int pay) throws SQLException;
	public int SalaryIncomeSet(int pay, int dependentCount) throws SQLException;
	public String SalaryNextEmp(String year_month, String emp_id) throws SQLException;
	
	public void salaryPayrollInsert(SalarymgtDto dto) throws SQLException;
	public void salaryPayrollUpdate(SalarymgtDto dto) throws SQLException;
	
	public int salaryEmpListCount(@Param("work_year_month") String work_year_month) throws SQLException;
}

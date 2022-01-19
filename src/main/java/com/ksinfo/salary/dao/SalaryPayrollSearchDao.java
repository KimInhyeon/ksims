package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.List;

import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.salary.dto.SalarymgtDto;


public interface SalaryPayrollSearchDao {
	
	public List<SalarymgtDto> salaryPayrollSearch(java.util.Map<String, String> condition) throws SQLException;
	public List<EmpDto> empNameDropDown() throws SQLException;
}

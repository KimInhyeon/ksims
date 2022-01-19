package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.Map;

import com.ksinfo.employees.dto.EmpDto;

public interface EmployeesChangeService {

	public EmpDto employeesChange(int empIdx) throws SQLException;
	
	public EmpDto employeesHistory(Map<String, Integer> paramMap) throws SQLException;
	
	public void empManageUpdate(EmpDto empDto) throws SQLException;
	public void empEmployeeUpdate(EmpDto empDto) throws SQLException;

	public void preEmployeeUpdate(EmpDto empDto) throws SQLException;
	public void deleteEmp(EmpDto empDto) throws SQLException;
}

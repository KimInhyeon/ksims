package com.ksinfo.employees.service;

import java.sql.SQLException;

import com.ksinfo.employees.dto.EmpDto;

public interface EmployeesChangePasswordService {
	public String getPassword(String empId) throws SQLException;
	public void changePassword(EmpDto empDto) throws SQLException;

}

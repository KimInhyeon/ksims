package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.employees.dto.EmpDto;

public interface EmployeesListService {
	
	List<EmpDto> employeeList(@Param("start") int start, @Param("end") int end, @Param("auth") String auth, @Param("empId") String empId) throws SQLException;
	int employeeCount() throws SQLException;
	
}

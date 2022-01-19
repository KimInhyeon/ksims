package com.ksinfo.employees.service;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.dto.EmpDtoResp;
import com.ksinfo.employees.dto.EmpIdDto;

public interface EmployeesWriteService {
	public void empInsert(EmpDto empDto) throws SQLException;
	public EmpIdDto findLastEmpId() throws SQLException;
	public int duplicationCheck(@Param("empId") String empId) throws SQLException;
	public EmpDtoResp findLastEmployee() throws SQLException;
}

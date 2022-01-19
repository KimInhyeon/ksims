package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.List;

import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.PositionMasterDto;

public interface EmployeesCommonService {
	public List<PositionMasterDto> positionDropBox() throws SQLException;
	public List<DepartmentMasterDto> departDropBox() throws SQLException;
}

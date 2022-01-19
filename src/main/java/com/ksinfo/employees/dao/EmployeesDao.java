package com.ksinfo.employees.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.dto.EmpDtoResp;
import com.ksinfo.employees.dto.EmpFileDto;
import com.ksinfo.employees.dto.EmpIdDto;
import com.ksinfo.employees.dto.PositionMasterDto;

public interface EmployeesDao {
	public String getPassword(String empId) throws SQLException;
	public void changePassword(EmpDto EmpDto) throws SQLException;
	public void empInsert(EmpDto empDto) throws SQLException;
	public EmpDto employeesChange(int empIdx) throws SQLException;
	
	public EmpDto employeesHistory(Map<String, Integer> paramMap) throws SQLException;
	
	public void empManageUpdate(EmpDto empDto) throws SQLException;
	public void empEmployeeUpdate(EmpDto empDto) throws SQLException;
	public List<EmpDto> employeeList(int start, int end, String auth, String empId) throws SQLException;
	public int employeeCount() throws SQLException;
	
	public List<PositionMasterDto> positionDropBox() throws SQLException;
	public List<DepartmentMasterDto> departDropBox() throws SQLException;
	public EmpIdDto findLastEmpId() throws SQLException;
	public int duplicationCheck(@Param("empId") String empId) throws SQLException;
	
	public List<EmpFileDto> getFileList(String empId) throws SQLException;
	public void registFile(EmpFileDto empDto) throws SQLException;
	public void deleteFile(int empFileIdx) throws Exception;
	
	public EmpDtoResp findLastEmployee() throws SQLException;
	public void preEmpUpdate(EmpDto empDto) throws SQLException;
	public void deleteEmp(EmpDto empDto) throws SQLException;
}

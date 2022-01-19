package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.EmpDto;

@Service
public class EmployeesChangeServiceImpl implements EmployeesChangeService {

	@Inject
	EmployeesDao dao;

	@Override
	public EmpDto employeesChange(int empIdx) throws SQLException {
		// TODO Auto-generated method stub
		return dao.employeesChange(empIdx);
	}

	@Override
	public EmpDto employeesHistory(Map<String, Integer> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return dao.employeesHistory(paramMap);
	}

	@Override
	public void empManageUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		 dao.empManageUpdate(empDto);
	}

	@Override
	public void empEmployeeUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		dao.empEmployeeUpdate(empDto);
	}

	@Override
	public void preEmployeeUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		dao.preEmpUpdate(empDto);
	}
	
	@Override
	public void deleteEmp(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		dao.deleteEmp(empDto);
	}
}

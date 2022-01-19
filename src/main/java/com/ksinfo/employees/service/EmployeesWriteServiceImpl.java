package com.ksinfo.employees.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.dto.EmpDtoResp;
import com.ksinfo.employees.dto.EmpIdDto;

@Service
public class EmployeesWriteServiceImpl implements EmployeesWriteService {

	@Inject
	EmployeesDao dao;

	@Override
	public int duplicationCheck(String empId) throws SQLException {
		// TODO Auto-generated method stub
		return dao.duplicationCheck(empId);
	}

	@Override
	public void empInsert(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		dao.empInsert(empDto);
	}

	@Override
	public EmpIdDto findLastEmpId() throws SQLException {
		// TODO Auto-generated method stub
		return dao.findLastEmpId();
	}
	
	@Override
	public EmpDtoResp findLastEmployee() throws SQLException {
		return dao.findLastEmployee();
	}
}

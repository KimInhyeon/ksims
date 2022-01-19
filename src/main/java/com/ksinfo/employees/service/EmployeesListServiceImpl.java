package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.EmpDto;

@Service
public class EmployeesListServiceImpl implements EmployeesListService {

	@Inject
	EmployeesDao dao;
	
	@Override
	public List<EmpDto> employeeList(int start, int end, String auth, String empId) throws SQLException {
		// TODO Auto-generated method stub
		return dao.employeeList(start, end, auth, empId);
	}

	@Override
	public int employeeCount() throws SQLException {
		// TODO Auto-generated method stub
		return dao.employeeCount();
	}
}

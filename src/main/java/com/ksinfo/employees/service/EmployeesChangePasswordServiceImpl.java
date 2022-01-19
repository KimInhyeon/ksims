package com.ksinfo.employees.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.EmpDto;

@Service
public class EmployeesChangePasswordServiceImpl implements EmployeesChangePasswordService {

	@Inject
	EmployeesDao dao;

	@Override
	public String getPassword(String empId) throws SQLException {
		return dao.getPassword(empId);
	}

	@Override
	public void changePassword(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		dao.changePassword(empDto);
	}

}

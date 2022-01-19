package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.PositionMasterDto;

@Service
public class EmployeesCommonServiceImpl implements EmployeesCommonService {

	@Inject
	EmployeesDao dao;

	@Override
	public List<PositionMasterDto> positionDropBox() throws SQLException {
		// TODO Auto-generated method stub
		return dao.positionDropBox();
	}

	@Override
	public List<DepartmentMasterDto> departDropBox() throws SQLException {
		// TODO Auto-generated method stub
		return dao.departDropBox();
	}
}

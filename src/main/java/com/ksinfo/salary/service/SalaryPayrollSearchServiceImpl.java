package com.ksinfo.salary.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.salary.dao.SalaryPayrollSearchDaoImpl;
import com.ksinfo.salary.dto.SalarymgtDto;

@Service
public class SalaryPayrollSearchServiceImpl implements SalaryPayrollSearchService {

	@Inject
	SalaryPayrollSearchDaoImpl sPDao = new SalaryPayrollSearchDaoImpl();
	
	@Override
	public List<SalarymgtDto> salaryPayrollSearch(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		return sPDao.salaryPayrollSearch(condition);
	}
	
	@Override
	public List<EmpDto> empNameDropDown() throws SQLException {
		// TODO Auto-generated method stub
		return sPDao.empNameDropDown();
	}
	
}

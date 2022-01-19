package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.salary.dto.SalarymgtDto;

@Repository
public class SalaryPayrollSearchDaoImpl extends SqlSessionFactoryService implements SalaryPayrollSearchDao{
	
	private static SalaryPayrollSearchDaoImpl instance = new SalaryPayrollSearchDaoImpl();
	
	public static SalaryPayrollSearchDaoImpl getInstance() {
		return instance;
	}

	public static void setInstance(SalaryPayrollSearchDaoImpl instance) {
		SalaryPayrollSearchDaoImpl.instance = instance;
	}
	
	@Override
	public List<SalarymgtDto> salaryPayrollSearch(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryPayrollSearch", condition);
	}
	
	@Override
	public List<EmpDto> empNameDropDown() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.empNameDropDown");
	}
}

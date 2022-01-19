package com.ksinfo.salary.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.salary.dto.SalaryConfirmDto;

@Repository
public class SalaryConfirmDaoImpl extends SqlSessionFactoryService implements SalaryConfirmDao{
	private static SalaryConfirmDaoImpl instance = new SalaryConfirmDaoImpl();
	
	public static SalaryConfirmDaoImpl getInstance() {
		return instance;
	}

	public static void setInstance(SalaryConfirmDaoImpl instance) {
		SalaryConfirmDaoImpl.instance = instance;
	}

	@Override
	public List<SalaryConfirmDto> getTargetSalary(String yearMonth, String year, String month) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("yearMonth", yearMonth);
		paramMap.put("year", year);
		paramMap.put("month", month);

		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.getTargetSalary", paramMap);
	}

	@Override
	public int getConductCountForSalary(String yearMonth) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.getConductCountForSalary", yearMonth);
	}

	@Override
	public int getTargetSalaryCount(String year, String month) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("year", year);
		paramMap.put("month", month);
		
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.getTargetSalaryCount", paramMap);
	}

	@Override
	public int getEmpCountForSalary() {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.getEmpCountForSalary");
	}

	@Override
	public int confirmSalaryList(String year, String month) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("year", year);
		paramMap.put("month", month);
		
		return getSqlSessionTemplate().update("com.ksinfo.salary.dao.SalaryDao.confirmSalaryList", paramMap);
	}
}

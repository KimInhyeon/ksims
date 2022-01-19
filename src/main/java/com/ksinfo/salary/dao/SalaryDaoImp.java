package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.salary.dto.SalarymgtDto;

@Repository
public class SalaryDaoImp extends SqlSessionFactoryService implements SalaryDao {

	private static SalaryDaoImp instance = new SalaryDaoImp();
	Map<String, Object> paramMap = new HashMap<String, Object>();

	
	// 21/04/21 Youm
	@Override
	public List<SalarymgtDto> salaryEmpList(java.util.Map<String, String> condition) throws SQLException {
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryEmpList", condition);
	}
	
	@Override
	public List<SalarymgtDto> salaryEmpOne(String identificationNo, String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("identificationNo", identificationNo);
        paramMap.put("year", year);
        paramMap.put("month", month);
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryEmpOne", paramMap);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownList(String nowYear, String nowMonth) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("year", nowYear);
        paramMap.put("month", nowMonth);
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryDropDownList",paramMap);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownOne(String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("year", year);
        paramMap.put("month", month);
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryDropDownOne",paramMap);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownEmpList(String emp_id, String targetDate) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("emp_id", emp_id);
        paramMap.put("targetDate", targetDate);
        
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.salaryDropDownEmpList",paramMap);
	}
	@Override
	public SalarymgtDto salaryPayrollList(String emp_id, String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		paramMap.put("emp_id", emp_id);
		paramMap.put("year", year);
		paramMap.put("month", month);
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryPayrollList",paramMap);
	}
	@Override
	public SalarymgtDto salaryPayrollPastList(String emp_id) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryPayrollPastList",emp_id);
	}
	@Override
	public SalarymgtDto salaryPayrollCdt(String emp_id, String work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		paramMap.put("emp_id", emp_id);
		paramMap.put("work_year_month", work_year_month);
		
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryPayrollCdt",paramMap);
	}
	@Override
	public SalarymgtDto SalaryInsuranceSet(int pay) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryInsuranceSet",pay);
	}
	@Override
	public int SalaryIncomeSet(int pay, int dependentCount) throws SQLException {
		// TODO Auto-generated method stub
		paramMap.put("pay", pay);
		paramMap.put("dependentCount", dependentCount);
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryIncomeSet",paramMap);
	}
	@Override
	public String SalaryNextEmp(String year_month, String emp_id) throws SQLException {
		// TODO Auto-generated method stub
		paramMap.put("year_month", year_month);
		paramMap.put("emp_id", emp_id);
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryNextEmp",paramMap);
	}
	
	@Override
	public void salaryPayrollInsert(SalarymgtDto dto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.salary.dao.SalaryDao.salaryPayrollInsert",dto);
	}
	@Override
	public void salaryPayrollUpdate(SalarymgtDto dto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.salary.dao.SalaryDao.salaryPayrollUpdate",dto);
	}
	
	@Override
	public int salaryEmpListCount(String work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.salaryEmpListCount", work_year_month);
	}

	public static SalaryDaoImp getInstance() {
		return instance;
	}

	public static void setInstance(SalaryDaoImp instance) {
		SalaryDaoImp.instance = instance;
	}

}


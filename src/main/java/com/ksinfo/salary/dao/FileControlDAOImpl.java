package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.salary.dto.IncomeTaxDto;
import com.ksinfo.salary.dto.InsuranceDto;
import com.ksinfo.salary.dto.SalaryDto;

@Repository
public class FileControlDAOImpl extends SqlSessionFactoryService implements FileControlDAO {

	@Override
	public void incomeTaxInsert(IncomeTaxDto paramClass) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.salary.dao.SalaryDao.incomeTaxInsert", paramClass);
		
	}

	@Override
	public List<IncomeTaxDto> incomeTaxChkList() throws SQLException {
		// TODO Auto-generated method stub

		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.incomeTaxChkList");
	}

	@Override
	public void incomeTaxDelete() throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().delete("com.ksinfo.salary.dao.SalaryDao.incomeTaxDelete");
		
	}
	@Override
	public void insuranceInsert(InsuranceDto paramClass2) throws SQLException {
		// TODO Auto-generated method stub
		
		getSqlSessionTemplate().insert("com.ksinfo.salary.dao.SalaryDao.insuranceInsert", paramClass2);
		
		
	}
	
	@Override
	public List<InsuranceDto> insuranceChkList() throws SQLException {
		// TODO Auto-generated method stub

		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.insuranceChkList");
	}
	
	@Override
	public void insuranceDelete() throws SQLException {
		// TODO Auto-generated method stub

		getSqlSessionTemplate().delete("com.ksinfo.salary.dao.SalaryDao.insuranceDelete");
	}
	
	@Override
	public List<SalaryDto> selectAll_Salary(Map<String,Object> period) throws SQLException {
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.selectAll_SalaryForPrint", period);
	}
	
	@Override
	public List<SalaryDto> selectAll_SalaryForPaging(Map<String,Object> paraMap) throws SQLException {
		return getSqlSessionTemplate().selectList("com.ksinfo.salary.dao.SalaryDao.selectAll_SalaryForPaging", paraMap);
	}	

	@Override
	public int selected_SalaryCount(Map<String,Object> period) throws SQLException {
		// TODO Auto-generated method stub
				
		return getSqlSessionTemplate().selectOne("com.ksinfo.salary.dao.SalaryDao.selected_SalaryCount", period);
	}
	
}

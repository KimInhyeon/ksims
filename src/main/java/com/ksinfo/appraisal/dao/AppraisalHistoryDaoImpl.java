package com.ksinfo.appraisal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalEmpDto;
import com.ksinfo.appraisal.dto.AppraisalHistoryDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalHistoryDaoImpl extends SqlSessionFactoryService implements AppraisalHistoryDao {

	@Override
	public List<AppraisalHistoryDto> getMyList(Map<String, Object> paraMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getMyList",paraMap);
	}

	@Override
	public AppraisalEmpDto getEmployee(String emp_id) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getEmployee",emp_id);
	}

	@Override
	public List<AppraisalEmpDto> getEmployees(Map<String, Object> paraMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getEmployees",paraMap);
	}
	
	@Override
	public int getEmployeesCnt() {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getEmployeesCnt");
	}
	
	@Override
	public int getMyListCnt(Map<String, Object> paraMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getMyListCnt",paraMap);
	}
}

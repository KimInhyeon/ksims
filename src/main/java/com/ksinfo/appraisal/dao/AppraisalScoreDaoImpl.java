package com.ksinfo.appraisal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalScoreDaoImpl extends SqlSessionFactoryService implements AppraisalScoreDao {

	@Override
	public List<AppraisalMgtDto> getAllList(Map<String,Object> paraMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getAllScore",paraMap);
	}

	@Override
	public List<AppraisalMgtDto> getMyList(Map<String,Object> paraMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getMyScore",paraMap);
	}
	
	@Override
	public int countAllList() {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getAllScoreCnt");
	}
	
	@Override
	public int countMyList(Map<String, Object> paraMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getMycoreCnt",paraMap);
	}

}

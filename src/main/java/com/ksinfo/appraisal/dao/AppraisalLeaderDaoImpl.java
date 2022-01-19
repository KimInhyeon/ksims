package com.ksinfo.appraisal.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalLeaderDaoImpl extends SqlSessionFactoryService implements AppraisalLeaderDao {
	@Override
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String,Object> paramMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getSelfAndLeaderApp",paramMap);
	}
	@Override
	public int updateLeaderAppr(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().insert("com.ksinfo.appraisal.dao.AppraisalMainDao.updateLeaderAppr", dto);
	}
	@Override
	public int writeLeaderAppr(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().update("com.ksinfo.appraisal.dao.AppraisalMainDao.writeLeaderAppr", dto);
	}
	@Override
	public String getLeaderCode(Map<String, Object> paramMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getFieldAuth", paramMap);
	}
	public int writeApprUniChk(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.writeApprUniChk", dto);
	}
}

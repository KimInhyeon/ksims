package com.ksinfo.appraisal.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalSelfDaoImpl extends SqlSessionFactoryService implements AppraisalSelfDao {

	@Override
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String,Object> map) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getSelfAndLeaderApp",map);
	}

	@Override
	public int writeSelfAppr(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().insert("com.ksinfo.appraisal.dao.AppraisalMainDao.writeSelfAppr", dto);
	}

	@Override
	public int updateSelfAppr(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().update("com.ksinfo.appraisal.dao.AppraisalMainDao.updateSelfAppr", dto);
	}
	
	public int writeApprUniChk(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.writeApprUniChk", dto);
	}

}

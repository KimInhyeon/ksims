package com.ksinfo.appraisal.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalPerformanceDaoImpl extends SqlSessionFactoryService implements AppraisalPerFormanceDao {

	@Override
	public AppraisalMgtDto getAppraisal(Map<String, Object> paramMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getPerformAppr", paramMap);
	}

	@Override
	public int writePerfrAppraisal(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().insert("com.ksinfo.appraisal.dao.AppraisalMainDao.writePerformAppr", dto);
	}

	@Override
	public int updatePerfrAppraisal(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().update("com.ksinfo.appraisal.dao.AppraisalMainDao.updatePerformAppr", dto);
	}
	
	public int writeApprUniChk(AppraisalMgtDto dto) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.writeApprUniChk", dto);
	}

}

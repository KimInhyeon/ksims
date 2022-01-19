package com.ksinfo.appraisal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalConfirmDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalConfirmDaoImpl extends SqlSessionFactoryService implements AppraisalConfirmDao{
	@Override
	public List<AppraisalConfirmDto> getApprList() {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getApprList");
	}
	
	@Override
	public int putAppraisal(AppraisalConfirmDto dto) {
		getSqlSessionTemplate().update("com.ksinfo.appraisal.dao.AppraisalMainDao.acceptFlg_on",dto.getAppraisal_idx());
		return getSqlSessionTemplate().insert("com.ksinfo.appraisal.dao.AppraisalMainDao.putAppraisal", dto);
	}
}

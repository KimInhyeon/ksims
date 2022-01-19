package com.ksinfo.appraisal.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalSelfDao;
import com.ksinfo.appraisal.dto.AppraisalMgtDto;

@Service("appraisalSelfService")
public class AppraisalSelfServiceImpl implements AppraisalSelfService {
	@Inject
	AppraisalSelfDao dao;
	
	@Override
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String,Object> map) {
		return dao.getSelfAndLeaderApp(map);
	}

	@Override
	public int writeSelfAppr(AppraisalMgtDto dto) {
		return dao.writeSelfAppr(dto);
	}

	@Override
	public int updateSelfAppr(AppraisalMgtDto dto) {
		return dao.updateSelfAppr(dto);
	}
	
	@Override
	public int writeApprUniChk(AppraisalMgtDto dto) {
		return dao.writeApprUniChk(dto);
	}

}

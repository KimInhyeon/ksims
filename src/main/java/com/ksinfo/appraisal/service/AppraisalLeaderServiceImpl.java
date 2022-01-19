package com.ksinfo.appraisal.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalLeaderDao;
import com.ksinfo.appraisal.dto.AppraisalMgtDto;

@Service("appraisalLeaderService")
public class AppraisalLeaderServiceImpl implements AppraisalLeaderService {

	@Inject
	AppraisalLeaderDao dao;

	@Override
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String, Object> paramMap) {
		return dao.getSelfAndLeaderApp(paramMap);
	}

	@Override
	public int writeLeaderAppr(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return dao.writeLeaderAppr(dto);
	}

	@Override
	public int updateLeaderAppr(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return dao.updateLeaderAppr(dto);
	}

	@Override
	public String getLeaderCode(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return dao.getLeaderCode(paramMap);
	}

	public int writeApprUniChk(AppraisalMgtDto dto) {
		return dao.writeApprUniChk(dto);
	}
}

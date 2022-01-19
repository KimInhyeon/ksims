package com.ksinfo.appraisal.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalPerFormanceDao;
import com.ksinfo.appraisal.dto.AppraisalMgtDto;

@Service("appraisalPerformanceService")
public class AppraisalPerformanceServiceImpl implements AppraisalPerformanceService {
	@Inject
	AppraisalPerFormanceDao dao;
	
	@Override
	public AppraisalMgtDto getAppraisal(Map<String, Object> paramMap) {
		return dao.getAppraisal(paramMap);
	}

	@Override
	public int writePerfrAppraisal(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return dao.writePerfrAppraisal(dto);
	}

	@Override
	public int updatePerfrAppraisal(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return dao.updatePerfrAppraisal(dto);
	}
	
	@Override
	public int writeApprUniChk(AppraisalMgtDto dto) {
		// TODO Auto-generated method stub
		return dao.writeApprUniChk(dto);
	}

}

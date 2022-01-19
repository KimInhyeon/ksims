package com.ksinfo.appraisal.service;

import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;

public interface AppraisalPerformanceService {
	public AppraisalMgtDto getAppraisal(Map<String,Object> paramMap);
	public int writePerfrAppraisal(AppraisalMgtDto dto);
	public int updatePerfrAppraisal(AppraisalMgtDto dto);
	public int writeApprUniChk(AppraisalMgtDto dto);
}

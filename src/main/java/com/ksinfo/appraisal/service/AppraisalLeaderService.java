package com.ksinfo.appraisal.service;

import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;

public interface AppraisalLeaderService {
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String, Object> map);
	public int writeLeaderAppr(AppraisalMgtDto dto);
	public int updateLeaderAppr(AppraisalMgtDto dto);
	public String getLeaderCode(Map<String, Object> paramMap);
	public int writeApprUniChk(AppraisalMgtDto dto);
}

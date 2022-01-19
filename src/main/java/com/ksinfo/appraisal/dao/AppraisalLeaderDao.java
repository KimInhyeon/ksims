package com.ksinfo.appraisal.dao;

import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;

public interface AppraisalLeaderDao {
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String,Object> paramMap);
	public int writeLeaderAppr(AppraisalMgtDto dto);
	public int updateLeaderAppr(AppraisalMgtDto dto);
	public String getLeaderCode(Map<String, Object> paramMap);
	public int writeApprUniChk(AppraisalMgtDto dto);
}

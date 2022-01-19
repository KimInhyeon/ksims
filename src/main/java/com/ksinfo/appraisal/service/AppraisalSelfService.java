package com.ksinfo.appraisal.service;

import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;


public interface AppraisalSelfService {
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String, Object> map);
	public int writeSelfAppr(AppraisalMgtDto dto);
	public int updateSelfAppr(AppraisalMgtDto dto);
	public int writeApprUniChk(AppraisalMgtDto dto);
}

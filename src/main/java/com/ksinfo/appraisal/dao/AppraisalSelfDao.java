package com.ksinfo.appraisal.dao;

import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;

public interface AppraisalSelfDao {
	public AppraisalMgtDto getSelfAndLeaderApp(Map<String,Object> map);
	public int writeSelfAppr(AppraisalMgtDto dto);
	public int updateSelfAppr(AppraisalMgtDto dto);
	public int writeApprUniChk(AppraisalMgtDto dto);
}

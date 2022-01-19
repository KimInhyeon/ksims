package com.ksinfo.appraisal.dao;

import java.util.List;

import com.ksinfo.appraisal.dto.AppraisalConfirmDto;

public interface AppraisalConfirmDao {
	public List<AppraisalConfirmDto> getApprList();
	public int putAppraisal(AppraisalConfirmDto dto);
}

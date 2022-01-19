package com.ksinfo.appraisal.service;

import java.util.List;

import com.ksinfo.appraisal.dto.AppraisalConfirmDto;

public interface AppraisalConfirmService {
	public List<AppraisalConfirmDto> getApprList();
	public int putAppraisal(AppraisalConfirmDto dto);
}

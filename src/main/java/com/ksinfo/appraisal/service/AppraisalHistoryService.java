package com.ksinfo.appraisal.service;

import java.util.List;
import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalEmpDto;
import com.ksinfo.appraisal.dto.AppraisalHistoryDto;

public interface AppraisalHistoryService {
	public List<AppraisalHistoryDto> getMyList(Map<String,Object> paraMap);
	public int getMyListCnt(Map<String,Object> paraMap);
	public AppraisalEmpDto getEmployee(String emp_id);
	public List<AppraisalEmpDto> getEmployees(Map<String,Object> paraMap);
	public int getEmployeesCnt();
}

package com.ksinfo.appraisal.dao;

import java.util.List;
import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalFieldListDto;
import com.ksinfo.appraisal.dto.AppraisalModalDto;

public interface AppraisalMainDao  {
	
	public List<AppraisalFieldListDto> adminFieldList(Map<String,Object> paramMap);
	
	public List<AppraisalFieldListDto> employeeFieldList(Map<String,Object> paramMap);
	
	public List<AppraisalFieldListDto> getFieldList(Map<String,Object> paramMap);
	
	public List<AppraisalModalDto> getEmployeeList(Map<String,Object> paramMap);
	
	public String getLeaderCode(Map<String,Object> paramMap);
	
}

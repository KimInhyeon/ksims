package com.ksinfo.appraisal.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalMainDaoImpl;
import com.ksinfo.appraisal.dto.AppraisalFieldListDto;
import com.ksinfo.appraisal.dto.AppraisalModalDto;

@Service("appraisalMainService")
public class AppraisalMainSeiviceImpl implements AppraisalMainService{

	@Inject
	private AppraisalMainDaoImpl dao;
	
	@Override
	public List<AppraisalFieldListDto> adminFieldList(Map<String,Object> paramMap) {
		return dao.adminFieldList(paramMap);
	}

	@Override
	public List<AppraisalFieldListDto> employeeFieldList(Map<String,Object> paramMap) {
		return dao.employeeFieldList(paramMap);
	}

	@Override
	public List<AppraisalFieldListDto> getFieldList(Map<String,Object> paramMap) {
		return dao.getFieldList(paramMap);
	}
	
	@Override
	public List<AppraisalModalDto> getEmployeeList(Map<String,Object> paramMap) {
		return dao.getEmployeeList(paramMap);
	}
	
	@Override
	public String getLeaderCode(Map<String, Object> paramMap) {
		return dao.getLeaderCode(paramMap);
	}
}

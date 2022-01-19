package com.ksinfo.appraisal.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalHistoryDao;
import com.ksinfo.appraisal.dto.AppraisalEmpDto;
import com.ksinfo.appraisal.dto.AppraisalHistoryDto;
@Service("appraisalHistoryService")
public class AppraisalHistoryServiceImpl implements AppraisalHistoryService {
	@Inject
	AppraisalHistoryDao dao;
	
	@Override
	public List<AppraisalHistoryDto> getMyList(Map<String,Object> paraMap) {
		return dao.getMyList(paraMap);
	}
	
	@Override
	public AppraisalEmpDto getEmployee(String emp_id) {
		return dao.getEmployee(emp_id);
	}

	@Override
	public List<AppraisalEmpDto> getEmployees(Map<String,Object> paraMap) {
		return dao.getEmployees(paraMap);
	}
	
	@Override
	public int getEmployeesCnt() {
		// TODO Auto-generated method stub
		return dao.getEmployeesCnt();
	}
	
	@Override
	public int getMyListCnt(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return dao.getMyListCnt(paraMap);
	}

}

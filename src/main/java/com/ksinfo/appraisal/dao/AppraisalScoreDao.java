package com.ksinfo.appraisal.dao;

import java.util.List;
import java.util.Map;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;

public interface AppraisalScoreDao {
	public List<AppraisalMgtDto> getAllList(Map<String,Object> paraMap);
	public List<AppraisalMgtDto> getMyList(Map<String,Object> paraMap);
	public int countAllList();
	public int countMyList(Map<String,Object> paraMap);
}

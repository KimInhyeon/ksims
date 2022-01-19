package com.ksinfo.appraisal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.appraisal.dto.AppraisalFieldListDto;
import com.ksinfo.appraisal.dto.AppraisalModalDto;
import com.ksinfo.common.util.SqlSessionFactoryService;

@Repository
public class AppraisalMainDaoImpl extends SqlSessionFactoryService implements AppraisalMainDao {

	@Override
	public List<AppraisalFieldListDto> adminFieldList(Map<String,Object> paramMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.adminFieldList",paramMap);
	}

	@Override
	public List<AppraisalFieldListDto> employeeFieldList(Map<String,Object> paramMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.employeeFieldList",paramMap);
	}

	

	@Override
	public List<AppraisalModalDto> getEmployeeList(Map<String,Object> paramMap) {
		return getSqlSessionTemplate().selectList("com.ksinfo.appraisal.dao.AppraisalMainDao.getEmployeeList",paramMap);
	}
	
	@Override
	public List<AppraisalFieldListDto> getFieldList(Map<String,Object> paramMap) {
		List<AppraisalFieldListDto> list=null;
		String auth_code=(String)paramMap.get("auth_code");
		if(auth_code.equals("01")) {
			list=this.adminFieldList(paramMap);
		}else if(auth_code.equals("02")||auth_code.equals("03")) {
			list=this.employeeFieldList(paramMap);
		}
		return list;
	}
	
	@Override
	public String getLeaderCode(Map<String, Object> paramMap) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.appraisal.dao.AppraisalMainDao.getFieldAuth", paramMap);
	}

}

package com.ksinfo.conduct.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductWorkDto;

@Repository
public class ConductWorkResultDaoImpl extends SqlSessionFactoryService implements ConductWorkResultDao{
	@Override
	public List<ConductWorkDto> getConductWorkResultList(String field_code, String authCode,String emp_id, String work_year_month) throws DataAccessException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put("field_code", field_code);
		paramMap.put("authCode", authCode);
		paramMap.put("emp_id", emp_id);
		paramMap.put("work_year_month", work_year_month);
		return getSqlSessionTemplate().selectList("conduct.conductWorkResultList",paramMap);
	}
}
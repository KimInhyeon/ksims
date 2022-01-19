package com.ksinfo.conduct.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductWorkDto;

@Repository
public class ConductWorkDaoImpl extends SqlSessionFactoryService implements ConductWorkDao{
	
	@Override
	public List<ConductWorkDto> getConductWorkList(String field_code) throws DataAccessException{
		
		//String field_code = workDto.getField_code();
		return getSqlSessionTemplate().selectList("conduct.conductWorkList",field_code);
	}
}

package com.ksinfo.conduct.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductUpdateDto;

@Repository
public class ConductUpdateDaoImpl extends SqlSessionFactoryService implements ConductUpdateDao{
	@Override
	public int setConductUpdate(ConductUpdateDto dto) throws DataAccessException{
		
		return getSqlSessionTemplate().update("conduct.conductUpdateList", dto);
	}
}

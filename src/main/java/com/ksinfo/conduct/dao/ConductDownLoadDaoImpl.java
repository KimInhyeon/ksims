package com.ksinfo.conduct.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductScanDto;

@Repository
public class ConductDownLoadDaoImpl extends SqlSessionFactoryService implements ConductDownLoadDao{
	
	@Override
	public ConductScanDto getConductDownLoad(ConductScanDto dto) throws DataAccessException{
		
		return getSqlSessionTemplate().selectOne("conductDownLoad", dto);
	}
}

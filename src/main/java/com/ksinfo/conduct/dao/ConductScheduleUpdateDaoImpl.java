package com.ksinfo.conduct.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductScanDto;
import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.ConductScheduleUpdateDto;

@Repository
public class ConductScheduleUpdateDaoImpl extends SqlSessionFactoryService implements ConductScheduleUpdateDao{
	
	@Override
	public List<ConductScheduleUpdateDto> getConductScheduleUpdate(ConductScheduleUpdateDto dto) throws DataAccessException{
		
		return getSqlSessionTemplate().selectList("ConductScheduleUpdate", dto);
	}
}

package com.ksinfo.conduct.dao;

import java.util.List;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductScanDto;
import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;

@Repository
public class ConductScheduleDaoImpl extends SqlSessionFactoryService implements ConductScheduleDao{
	
	@Override
	public List<ConductScheduleDto> getConductSchedule(ConductScheduleDto dto) throws DataAccessException{
		
		return getSqlSessionTemplate().selectList("conductSchedule", dto);
	}
	@Override
	public List<WorkCodeMasterDto> workDropBox() throws  DataAccessException{
		
		return getSqlSessionTemplate().selectList("workDropBox");
	}

}

package com.ksinfo.conduct.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductTeamDto;

@Repository
public class ConductTeamDaoImpl extends SqlSessionFactoryService implements ConductTeamDao{

	@Override
	public List<ConductTeamDto> getConductTeamList(ConductTeamDto teamDto) throws DataAccessException{
			
		String emp_id = teamDto.getEmp_id();
		return getSqlSessionTemplate().selectList("conduct.conductTeamList",emp_id);
	}
	
@Override
	public List<ConductTeamDto> getPastTeamList(ConductTeamDto teamDto) throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.pastTeamList",teamDto);
	}
	
}
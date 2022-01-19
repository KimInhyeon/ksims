package com.ksinfo.conduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductTeamDao;
import com.ksinfo.conduct.dto.ConductTeamDto;

@Service
public class ConductTeamServiceImpl implements ConductTeamService{
	
	@Inject
	private ConductTeamDao teamDao;
	
	@Override
	public List<ConductTeamDto> getConductTeamList(ConductTeamDto teamDto) throws DataAccessException{
		return teamDao.getConductTeamList(teamDto);
	}
	
	@Override
	public List<ConductTeamDto> getPastTeamList(ConductTeamDto teamDto) throws DataAccessException {
		// TODO Auto-generated method stub
		return teamDao.getPastTeamList(teamDto);
	}
}

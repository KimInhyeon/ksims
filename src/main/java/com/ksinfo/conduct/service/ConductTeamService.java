package com.ksinfo.conduct.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductTeamDto;

public interface ConductTeamService {
	
	public List<ConductTeamDto> getConductTeamList(ConductTeamDto teamDto) throws DataAccessException;
	public List<ConductTeamDto> getPastTeamList(ConductTeamDto teamDto) throws DataAccessException;
}

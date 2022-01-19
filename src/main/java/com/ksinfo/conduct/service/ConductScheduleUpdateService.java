package com.ksinfo.conduct.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.ConductScheduleUpdateDto;

public interface ConductScheduleUpdateService {
	public List<ConductScheduleUpdateDto> getConductScheduleUpdate(ConductScheduleUpdateDto dto) throws DataAccessException;
	
}

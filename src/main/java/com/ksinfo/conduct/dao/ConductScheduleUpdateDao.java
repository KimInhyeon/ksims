package com.ksinfo.conduct.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.ConductScheduleUpdateDto;


public interface ConductScheduleUpdateDao {
	public List<ConductScheduleUpdateDto> getConductScheduleUpdate(ConductScheduleUpdateDto dto) throws DataAccessException;
}

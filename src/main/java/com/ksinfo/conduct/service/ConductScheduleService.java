package com.ksinfo.conduct.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;

public interface ConductScheduleService {
	public List<ConductScheduleDto> getConductSchedule(ConductScheduleDto dto) throws DataAccessException;
	public List<WorkCodeMasterDto> workDropBox() throws DataAccessException;
	
}

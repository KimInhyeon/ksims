package com.ksinfo.conduct.dao;

import java.util.List;

import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;

public interface ConductScheduleDao {
	public List<ConductScheduleDto> getConductSchedule(ConductScheduleDto dto) throws DataAccessException;
	public List<WorkCodeMasterDto> workDropBox() throws DataAccessException;
}

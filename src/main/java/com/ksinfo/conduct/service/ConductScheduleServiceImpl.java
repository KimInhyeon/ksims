package com.ksinfo.conduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductScheduleDao;
import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;

@Service
public class ConductScheduleServiceImpl implements ConductScheduleService{
	
	@Inject
	private ConductScheduleDao ScheduleDao;
	
	
	@Override
	public List<ConductScheduleDto> getConductSchedule(ConductScheduleDto dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return ScheduleDao.getConductSchedule(dto);
	}
	@Override
	public List<WorkCodeMasterDto> workDropBox() throws DataAccessException {
		// TODO Auto-generated method stub
		return ScheduleDao.workDropBox();
	}
}

package com.ksinfo.conduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductScheduleUpdateDao;
import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.ConductScheduleUpdateDto;

@Service
public class ConductScheduleUpdateServiceImpl implements ConductScheduleUpdateService{
	@Inject
	private ConductScheduleUpdateDao dao;

	@Override
	public List<ConductScheduleUpdateDto> getConductScheduleUpdate(ConductScheduleUpdateDto dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return dao.getConductScheduleUpdate(dto);
	}
}

package com.ksinfo.conduct.service;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductUpdateDao;
import com.ksinfo.conduct.dto.ConductUpdateDto;

@Service
public class ConductUpdateServiceImpl implements ConductUpdateSerivce{
	@Inject
	private ConductUpdateDao dao;
	
	@Override
	public int setConductUpdate(ConductUpdateDto dto) throws DataAccessException{
			return dao.setConductUpdate(dto);
	}
}

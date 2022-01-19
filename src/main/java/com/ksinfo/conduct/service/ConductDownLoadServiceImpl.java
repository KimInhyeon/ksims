package com.ksinfo.conduct.service;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductDownLoadDao;
import com.ksinfo.conduct.dto.ConductScanDto;

@Service
public class ConductDownLoadServiceImpl implements ConductDownLoadService{
	@Inject
	private ConductDownLoadDao downDao;
	
	@Override
	public ConductScanDto getConductDownLoad(ConductScanDto dto)throws DataAccessException{
		return downDao.getConductDownLoad(dto);
	}
}

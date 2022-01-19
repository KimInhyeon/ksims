package com.ksinfo.conduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductWorkDao;
import com.ksinfo.conduct.dto.ConductWorkDto;

@Service
public class ConductWorkServiceImpl implements ConductWorkService{
	
	@Inject
	private ConductWorkDao dao;
	
	@Override
	public List<ConductWorkDto> getConductWorkList(String field_code) throws DataAccessException{
		return dao.getConductWorkList(field_code);
	}
}

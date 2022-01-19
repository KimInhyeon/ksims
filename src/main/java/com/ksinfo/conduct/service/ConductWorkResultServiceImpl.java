package com.ksinfo.conduct.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductWorkResultDao;
import com.ksinfo.conduct.dto.ConductWorkDto;

@Service
public class ConductWorkResultServiceImpl implements ConductWorkResultService{
	@Inject
	private ConductWorkResultDao dao;
	@Override
	public List<ConductWorkDto> getConductWorkResultList(String field_code, String authCode,String emp_id,String work_year_month) throws DataAccessException{
		return dao.getConductWorkResultList(field_code, authCode, emp_id, work_year_month);
	}
}

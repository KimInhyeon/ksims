package com.ksinfo.conduct.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductWorkDto;

public interface ConductWorkService {
	
	public List<ConductWorkDto> getConductWorkList(String field_code) throws DataAccessException;
	
}

package com.ksinfo.conduct.service;



import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductUpdateDto;

public interface ConductUpdateSerivce {

	public int setConductUpdate(ConductUpdateDto dto) throws DataAccessException;
	
}

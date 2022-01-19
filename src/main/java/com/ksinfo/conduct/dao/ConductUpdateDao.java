package com.ksinfo.conduct.dao;



import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductUpdateDto;

public interface ConductUpdateDao {
	public int setConductUpdate(ConductUpdateDto dto) throws DataAccessException;
}

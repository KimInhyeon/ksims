package com.ksinfo.conduct.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductWorkDto;

public interface ConductWorkDao {
	public List<ConductWorkDto> getConductWorkList(String field_code) throws DataAccessException;
}

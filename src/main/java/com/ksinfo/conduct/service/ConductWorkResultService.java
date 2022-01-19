package com.ksinfo.conduct.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductWorkDto;

public interface ConductWorkResultService {
	public List<ConductWorkDto> getConductWorkResultList(String field_code, String authCode, String emp_id, String work_year_month) throws DataAccessException;
}

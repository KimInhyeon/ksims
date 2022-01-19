package com.ksinfo.conduct.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductWorkDto;

public interface ConductWorkResultDao {
	public List<ConductWorkDto> getConductWorkResultList(String field_code, String authCode, String emp_id,  String work_year_month) throws DataAccessException;
}

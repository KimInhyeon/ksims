package com.ksinfo.conduct.dao;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScanDto;

public interface ConductDownLoadDao {
	public ConductScanDto getConductDownLoad(ConductScanDto dto) throws DataAccessException;
}

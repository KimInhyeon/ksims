package com.ksinfo.conduct.service;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductScanDto;

public interface ConductDownLoadService {
	public ConductScanDto getConductDownLoad(ConductScanDto dto) throws DataAccessException;
}

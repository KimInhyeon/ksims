package com.ksinfo.common.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.common.dto.LoginDto;

public interface LoginService {
	
	public List<LoginDto> getLoginMemberList(LoginDto dto) throws DataAccessException;

}

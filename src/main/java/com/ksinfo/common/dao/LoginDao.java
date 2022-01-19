package com.ksinfo.common.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.common.dto.LoginDto;

public interface LoginDao {
	
	public List<LoginDto> getLoginMemberList(LoginDto dto) throws DataAccessException;

}

package com.ksinfo.common.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.common.dao.LoginDaoImpl;
import com.ksinfo.common.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDaoImpl dao;
	
	@Override
	public List<LoginDto> getLoginMemberList(LoginDto dto) throws DataAccessException {
		
		return dao.getLoginMemberList(dto);
	
	}

}

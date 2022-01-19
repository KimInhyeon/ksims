package com.ksinfo.common.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.dto.LoginDto;
import com.ksinfo.common.util.SqlSessionFactoryService;


@Repository
public class LoginDaoImpl extends SqlSessionFactoryService implements LoginDao {
	
	@Override
	public List<LoginDto> getLoginMemberList(LoginDto dto) throws DataAccessException {

		String emp_id = dto.getEmp_id();
		return getSqlSessionTemplate().selectList("login.selectMemberList", emp_id);
		
	}

}

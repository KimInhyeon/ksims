package com.ksinfo.order.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksinfo.order.dto.ClientInfoDto;

@Repository
public class ClientInfoDaoImpl implements ClientInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ClientInfoDto> clientList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int ClientInfo(ClientInfoDto clientInfoDto) {

		System.out.println("client -- dao");

//		return 0;
		return sqlSession.insert("obtainOrder.clientIns", clientInfoDto);
	}

}

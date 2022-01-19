package com.ksinfo.order.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.order.dao.ClientInfoDao;
import com.ksinfo.order.dto.ClientInfoDto;

@Service("clientInfoService")
public class ClientInfoServiceImpl implements ClientInfoService {

	@Inject
	ClientInfoDao ClientDao;
	
	@Override
	public List<ClientInfoDto> clientList() {
		return null;
	}

	@Override
	public int ClientInfo(ClientInfoDto clientInfoDto) {
		
		System.out.println("service --> " + clientInfoDto);
		
		return ClientDao.ClientInfo(clientInfoDto);
	}

}

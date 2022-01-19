package com.ksinfo.order.service;

import java.util.List;

import com.ksinfo.order.dto.ClientInfoDto;

public interface ClientInfoService {

	public List<ClientInfoDto> clientList();
	public int  ClientInfo(ClientInfoDto clientInfoDto);

}

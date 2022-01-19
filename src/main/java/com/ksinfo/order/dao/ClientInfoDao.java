package com.ksinfo.order.dao;

import java.util.List;

import com.ksinfo.order.dto.ClientInfoDto;

public interface ClientInfoDao {

	public List<ClientInfoDto> clientList();
	public int  ClientInfo(ClientInfoDto clientInfoDto);

}

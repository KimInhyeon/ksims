package com.ksinfo.order.service;

import java.util.List;

import com.ksinfo.order.dto.ObtainOrdeDto;

public interface OrderService {

	public List<ObtainOrdeDto> orderList();
	public int ObtainOrde(ObtainOrdeDto dto);
}

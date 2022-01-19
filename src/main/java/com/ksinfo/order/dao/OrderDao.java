package com.ksinfo.order.dao;

import java.util.List;

import com.ksinfo.order.dto.ObtainOrdeDto;

public interface OrderDao {

	public List<ObtainOrdeDto> orderList();
	public int ObtainOrde(ObtainOrdeDto dto);
}

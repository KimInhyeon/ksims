package com.ksinfo.order.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.order.dao.OrderDao;
import com.ksinfo.order.dto.ObtainOrdeDto;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Inject
	OrderDao dao;
	
	@Override
	public List<ObtainOrdeDto> orderList() { 		
		return null;
	}

	@Override
	public int ObtainOrde(ObtainOrdeDto dto) {
		// TODO Auto-generated method stub
		System.out.println("service-->  " + dto);

//		return null;
		return dao.ObtainOrde(dto);
	}

}

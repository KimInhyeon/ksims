package com.ksinfo.order.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksinfo.order.dto.ObtainOrdeDto;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ObtainOrdeDto> orderList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int ObtainOrde(ObtainOrdeDto dto) {

		return sqlSession.insert("obtainOrder.obtainIns", dto);
	}

}

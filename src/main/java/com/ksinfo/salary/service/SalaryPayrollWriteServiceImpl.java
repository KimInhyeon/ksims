package com.ksinfo.salary.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.salary.dao.SalaryDaoImp;
import com.ksinfo.salary.dto.SalarymgtDto;

@Service
public class SalaryPayrollWriteServiceImpl implements SalaryPayrollWriteService {

	@Inject
	SalaryDaoImp sDao = new SalaryDaoImp();
	
	//2021-04-24 Youm
	@Override
	public void salaryPayrollInsert(SalarymgtDto dto) throws SQLException {
		// TODO Auto-generated method stub
		sDao.salaryPayrollInsert(dto);
	}
	@Override
	public void salaryPayrollUpdate(SalarymgtDto dto) throws SQLException {
		// TODO Auto-generated method stub
		sDao.salaryPayrollUpdate(dto);
	}
	
	

}

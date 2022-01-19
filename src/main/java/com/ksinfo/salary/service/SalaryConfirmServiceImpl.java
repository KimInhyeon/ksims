package com.ksinfo.salary.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.salary.dao.SalaryConfirmDaoImpl;
import com.ksinfo.salary.dao.SalaryDaoImp;
import com.ksinfo.salary.dto.SalaryConfirmDto;

@Service
public class SalaryConfirmServiceImpl implements SalaryConfirmService {

	@Inject
	SalaryDaoImp sDao = new SalaryDaoImp();
	@Inject
	SalaryConfirmDaoImpl confirmDao = new SalaryConfirmDaoImpl();
	
	
	@Override
	public List<SalaryConfirmDto> getTargetSalary(String yearMonth, String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		return confirmDao.getTargetSalary(yearMonth, year, month);
	}

	@Override
	public int getTargetSalaryCount(String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		return confirmDao.getTargetSalaryCount(year, month);
	}

	@Override
	public int getConductCountForSalary(String yearMonth) throws SQLException {
		// TODO Auto-generated method stub
		return confirmDao.getConductCountForSalary(yearMonth);
	}

	@Override
	public int getEmpCountForSalary() throws SQLException {
		// TODO Auto-generated method stub
		return confirmDao.getEmpCountForSalary();
	}

	@Override
	public int confirmSalaryList(String year, String month) throws SQLException {
		return confirmDao.confirmSalaryList(year, month);
	}
}

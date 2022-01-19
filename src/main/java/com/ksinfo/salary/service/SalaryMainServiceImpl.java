package com.ksinfo.salary.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.salary.dao.SalaryDaoImp;
import com.ksinfo.salary.dto.SalarymgtDto;

@Service
public class SalaryMainServiceImpl implements SalaryMainService {

	@Inject
	SalaryDaoImp sDao = new SalaryDaoImp();

	// 21/04/21
	@Override
	public List<SalarymgtDto> salaryEmpList(java.util.Map<String, String> condition) throws SQLException {
		return sDao.salaryEmpList(condition);
	}
	
	@Override
	public List<SalarymgtDto> salaryEmpOne(String identificationNo, String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryEmpOne(identificationNo, year, month);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownList(String nowYear, String nowMonth) {
		// TODO Auto-generated method stub
		return sDao.salaryDropDownList(nowYear,nowMonth);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownEmpList(String emp_id, String targetDate) {
		// TODO Auto-generated method stub
		return sDao.salaryDropDownEmpList(emp_id, targetDate);
	}
	
	@Override
	public List<SalarymgtDto> salaryDropDownOne(String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryDropDownOne(year, month);
	}

	@Override
	public int salaryEmpListCount(String work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryEmpListCount(work_year_month);
	}
}

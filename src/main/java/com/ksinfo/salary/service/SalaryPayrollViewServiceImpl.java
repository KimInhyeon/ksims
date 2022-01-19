package com.ksinfo.salary.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.salary.dao.SalaryDaoImp;
import com.ksinfo.salary.dto.SalarymgtDto;

@Service
public class SalaryPayrollViewServiceImpl implements SalaryPayrollViewService {

	@Inject
	SalaryDaoImp sDao = new SalaryDaoImp();

	
	// 21/04/21
	@Override
	public SalarymgtDto salaryPayrollList(String emp_id, String year, String month) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryPayrollList(emp_id, year, month);
	}
	@Override
	public SalarymgtDto salaryPayrollPastList(String emp_id) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryPayrollPastList(emp_id);
	}
	@Override
	public SalarymgtDto salaryPayrollCdt(String emp_id, String work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.salaryPayrollCdt(emp_id, work_year_month);
	}
	@Override
	public SalarymgtDto SalaryInsuranceSet(int pay) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.SalaryInsuranceSet(pay);
	}
	@Override
	public int SalaryIncomeSet(int pay, int dependentCount) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.SalaryIncomeSet(pay,dependentCount);
	}
	@Override
	public String SalaryNextEmp(String year_month, String emp_id) throws SQLException {
		// TODO Auto-generated method stub
		return sDao.SalaryNextEmp(year_month, emp_id);
	}

}

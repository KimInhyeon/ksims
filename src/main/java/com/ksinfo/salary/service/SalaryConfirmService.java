package com.ksinfo.salary.service;

import java.sql.SQLException;
import java.util.List;

import com.ksinfo.salary.dto.SalaryConfirmDto;

public interface SalaryConfirmService {

	
	public List<SalaryConfirmDto>  getTargetSalary(String yearMonth, String year, String month) throws SQLException;
	
	public int getTargetSalaryCount(String year, String month) throws SQLException;
	
	public int getConductCountForSalary(String yearMonth) throws SQLException;
	
	public int getEmpCountForSalary() throws SQLException;
	
	public int confirmSalaryList(String year, String month) throws SQLException;
}

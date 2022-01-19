package com.ksinfo.salary.dao;

import java.util.List;

import com.ksinfo.salary.dto.SalaryConfirmDto;

public interface SalaryConfirmDao {
	
	public List<SalaryConfirmDto>  getTargetSalary(String yearMonth, String year, String month);
	
	public int getConductCountForSalary(String yearMonth);
	
	public int getTargetSalaryCount(String year, String month);
	
	public int getEmpCountForSalary();
	
	public int confirmSalaryList (String year, String month);
}

package com.ksinfo.salary.service;

import java.sql.SQLException;

import com.ksinfo.salary.dto.SalarymgtDto;

public interface SalaryPayrollWriteService {

//	public void salaryInsert(SalaryDto paramClass) throws SQLException;
	//2021-04-24 Youm
	public void salaryPayrollInsert(SalarymgtDto dto) throws SQLException;
	public void salaryPayrollUpdate(SalarymgtDto dto) throws SQLException;

}

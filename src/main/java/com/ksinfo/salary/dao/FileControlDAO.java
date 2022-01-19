package com.ksinfo.salary.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ksinfo.salary.dto.IncomeTaxDto;
import com.ksinfo.salary.dto.InsuranceDto;
import com.ksinfo.salary.dto.SalaryDto;

public interface FileControlDAO {
	public void incomeTaxInsert (IncomeTaxDto paramClass) throws SQLException;
	public List<IncomeTaxDto> incomeTaxChkList() throws SQLException;
	public void incomeTaxDelete () throws SQLException;
	
	public void insuranceInsert (InsuranceDto paramClass2) throws SQLException;
	public List<InsuranceDto> insuranceChkList() throws SQLException;
	public void insuranceDelete () throws SQLException;
	
	public List<SalaryDto> selectAll_Salary(Map<String,Object> period) throws SQLException;
	public List<SalaryDto> selectAll_SalaryForPaging(Map<String,Object> paraMap) throws SQLException;
	public int selected_SalaryCount(Map<String,Object> preiod) throws SQLException;
}

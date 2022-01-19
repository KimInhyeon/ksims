package com.ksinfo.salary.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.salary.dto.SalarymgtDto;

public interface SalaryMainService {

//	public String getInsuranceUpdateDate() throws SQLException;
//	public String getIncometaxUpdateDate() throws SQLException;
//	public List<SalarymgtDto> salarymgtList() throws SQLException;
	
	// 21/04/20
	public List<SalarymgtDto> salaryEmpOne(@Param("identificationNo") String identificationNo, @Param("year") String year, @Param("month") String month) throws SQLException;
	public List<SalarymgtDto> salaryEmpList(java.util.Map<String, String> condition) throws SQLException;
	//21/04/21 Youm
	public int salaryEmpListCount(@Param("work_year_month") String work_year_month) throws SQLException;
	public List<SalarymgtDto> salaryDropDownList(String nowYear,String nowMonth);
	public List<SalarymgtDto> salaryDropDownEmpList(String emp_id, String targetDate) throws SQLException;
	
	public List<SalarymgtDto> salaryDropDownOne(String year,String month) throws SQLException;
	
}

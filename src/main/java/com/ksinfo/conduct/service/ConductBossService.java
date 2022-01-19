package com.ksinfo.conduct.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ksinfo.conduct.dto.ConductBossDto;
import com.ksinfo.conduct.dto.ConductMonthDayDto;
import com.ksinfo.conduct.dto.ConductScheduleEmpDto;
import com.ksinfo.conduct.dto.ConductWorkDto;

public interface ConductBossService {
	public List<ConductBossDto> getConductBossPage(int pageStart,int pageEnd,Date date) throws DataAccessException;
	public int countThisMonth(Date date) throws SQLException;
	public void conductGenerator(ConductWorkDto dto, ConductMonthDayDto day);
//	public int condAfterGenerator(String sid, Date work_year_month, ConductMonthDayDto day);
	// 21/06/04 Youm
	public boolean conductCompFlg(Date date) throws SQLException;
	public void conductCompUpdate(java.util.Map<String, String> condition) throws SQLException;
	public ConductScheduleEmpDto conductScheduleEmp(java.util.Map<String, String> condition) throws SQLException;
	
	public List<ConductWorkDto> currentEmp() throws SQLException;
	public List<ConductWorkDto> currentNewEmp(Date work_year_month) throws SQLException;
	
	
	public String getLeaderCode(ConductWorkDto dto) throws SQLException;
	public double getConductWorktime(String sheet_number) throws SQLException;
	public List<ConductBossDto> conductBossList(Date date) throws SQLException;
	public List<String> conductSheetList(Date date) throws SQLException;
	public List<String> leaderSheetList(ConductWorkDto dto) throws Exception;
	
}

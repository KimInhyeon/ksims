package com.ksinfo.conduct.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ksinfo.conduct.dao.ConductBossDao;
import com.ksinfo.conduct.dto.ConductBossDto;
import com.ksinfo.conduct.dto.ConductMonthDayDto;
import com.ksinfo.conduct.dto.ConductScheduleEmpDto;
import com.ksinfo.conduct.dto.ConductWorkDto;
@Service
public class ConductBossServiceImpl implements ConductBossService{
	
	@Inject
	private ConductBossDao bossDao;
	
	@Override
	public List<ConductBossDto> getConductBossPage(int pageStart, int pageEnd , Date date) throws DataAccessException {
		return bossDao.getConductBossPage(pageStart, pageEnd , date);
	}
	
//	@Override
//	public void conductGenerator(String sid) {
//		bossDao.conductGenerator(sid);
//	}
	@Override
	public void conductGenerator(ConductWorkDto dto, ConductMonthDayDto day) {
		bossDao.conductGenerator(dto,day);
	}
	
//	@Override
//	public void condAfterGenerator(ConductWorkDto dto, ConductMonthDayDto day) {
//		bossDao.condAfterGenerator(dto, day);
//	}
	
	@Override
	public int countThisMonth(Date date) throws SQLException {
		return bossDao.countThisMonth(date);
	}
	// 21/06/04 Youm
	@Override
	public boolean conductCompFlg(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.conductCompFlg(date);
	}
	
	@Override
	public void conductCompUpdate(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		bossDao.conductCompUpdate(condition);
	}
	
	@Override
	public ConductScheduleEmpDto conductScheduleEmp(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.conductScheduleEmp(condition);
	}
	
	@Override
	public List<ConductWorkDto> currentEmp() throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.currentEmp();
	}
	
	@Override
	public List<ConductWorkDto> currentNewEmp(Date work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.currentNewEmp(work_year_month);
	}
	
	@Override
	public String getLeaderCode(ConductWorkDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.getLeaderCode(dto);
	}
	
	@Override
	public double getConductWorktime(String sheet_number) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.getConductWorktime(sheet_number);
	}
	
	@Override
	public List<ConductBossDto> conductBossList(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.conductBossList(date);
	}
	
	@Override
	public List<String> conductSheetList(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return bossDao.conductSheetList(date);
	}
	
	@Override
	public List<String> leaderSheetList(ConductWorkDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bossDao.leaderSheetList(dto);
	}

}

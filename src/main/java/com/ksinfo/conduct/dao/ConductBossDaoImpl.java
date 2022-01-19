package com.ksinfo.conduct.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.conduct.dto.ConductBossDto;
import com.ksinfo.conduct.dto.ConductMonthDayDto;
import com.ksinfo.conduct.dto.ConductScheduleEmpDto;
import com.ksinfo.conduct.dto.ConductWorkDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;

@Repository
public class ConductBossDaoImpl extends SqlSessionFactoryService implements ConductBossDao{

	Map<String, String> condition = null;
	
	@Override
	public List<ConductBossDto> getConductBossPage(int pageStart, int pageEnd ,Date date) throws DataAccessException {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("pageStart",pageStart);
		paraMap.put("pageEnd", pageEnd);
		paraMap.put("date", date);
		return getSqlSessionTemplate().selectList("conduct.conductBossPage",paraMap);
	}
	
	@Override
	public void conductGenerator(ConductWorkDto dto, ConductMonthDayDto day){
		condition = new HashMap<String, String>();
		String sheet_number = dto.getEmp_id()+day.getYear()+day.getMonth()+dto.getField_code();
		
		for(int date=1; date<=day.getMaxDay(); date++) {
			condition.put("emp_id", dto.getEmp_id());
			condition.put("field_code", dto.getField_code());
			condition.put("conduct_year", day.getYear());
			condition.put("conduct_month", day.getMonth());
			condition.put("conduct_day", Integer.toString(date));
			condition.put("sheet_number", sheet_number);
			condition.put("reg_emp_id", dto.getRec_create_id());
			getSqlSessionTemplate().insert("conduct.conductSchedulInsert", condition);
		}
		dto.setSheet_number(sheet_number);
		getSqlSessionTemplate().insert("conduct.conductGenerate", dto);
		
	}
	
	@Override
	public int countThisMonth(Date date) throws SQLException {
		return getSqlSessionTemplate().selectOne("conduct.countThisMonth",date);
	}
	
//	@Override
//	public int condAfterGenerator(String sid, Date work_year_month, ConductMonthDayDto day) {
//		List<ConductWorkDto> list=null;
//		list=getSqlSessionTemplate().selectList("conduct.currentNewEmp",work_year_month);
//		for(int i=0;i<list.size();i++) {
//			condition = new HashMap<String, String>();
//			
//			ConductWorkDto dto=list.get(i);
//			String leader_code=getSqlSessionTemplate().selectOne("conduct.getLeaderCode",dto);
//			dto.setLeader_code(leader_code);
//			dto.setReg_emp_id(sid);
//			dto.setRec_create_id(sid);
//			dto.setPlan_work_days(0);
//			dto.setWorktime(0);
//			
//			//ks_conduct_mgt insert
//			String sheet_number = dto.getEmp_id()+day.getYear()+day.getMonth()+dto.getField_code();
//			dto.setSheet_number(sheet_number);
//			getSqlSessionTemplate().insert("conduct.conductGenerate", dto);
//			
//			for(int date=1; date<=day.getMaxDay(); date++) {
//				condition.put("emp_id", dto.getEmp_id());
//				condition.put("field_code", dto.getField_code());
//				condition.put("conduct_year", day.getYear());
//				condition.put("conduct_month", day.getMonth());
//				condition.put("conduct_day", Integer.toString(date));
//				condition.put("sheet_number", sheet_number);
//				condition.put("reg_emp_id", dto.getRec_create_id());
//				getSqlSessionTemplate().insert("conduct.conductSchedulInsert", condition);
//			}
//			
//		}
//		return list.size();
//	}
	
	// 21/06/04 Youm
	@Override
	public boolean conductCompFlg(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("conduct.conductCompFlg",date);
	}
	
	@Override
	public void conductCompUpdate(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("conduct.conductCompUpdate",condition);
	}
	
	@Override
	public ConductScheduleEmpDto conductScheduleEmp(Map<String, String> condition) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("conduct.conductScheduleEmp",condition);
	}
		
	@Override
	public List<ConductWorkDto> currentEmp() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.currentEmp");
	}
	
	@Override
	public List<ConductWorkDto> currentNewEmp(Date work_year_month) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.currentNewEmp",work_year_month);
	}
	
	@Override
	public String getLeaderCode(ConductWorkDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("conduct.getLeaderCode",dto);
	}
	
	@Override
	public double getConductWorktime(String sheet_number) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("conduct.getConductWorktime",sheet_number);
	}
	
	@Override
	public List<ConductBossDto> conductBossList(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.conductBossList",date);
	}
	
	@Override
	public List<String> conductSheetList(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.conductSheetList",date);
	}
	
	@Override
	public List<String> leaderSheetList(ConductWorkDto dto) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.leaderSheetList",dto);
	}

	@Override
	public List<WorkCodeMasterDto> workDropBox() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("conduct.workDropBox");
	}
	
}

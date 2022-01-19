package com.ksinfo.field.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.field.dto.FieldEmpUpdateDto;
import com.ksinfo.field.dto.FieldAreaDto;
import com.ksinfo.field.dto.FieldEmpAllListDto;
import com.ksinfo.field.dto.FieldEmpLeftListDto;
import com.ksinfo.field.dto.FieldEmpRightListDto;
import com.ksinfo.field.dto.FieldInfoDetailDto;
import com.ksinfo.field.dto.FieldRegistDto;
import com.ksinfo.field.dto.FieldSearchDto;
import com.ksinfo.field.dto.FieldSimpleInfoDto;
import com.ksinfo.field.dto.FieldListDto;
import com.ksinfo.field.dto.FieldMainViewDto;
import com.ksinfo.field.dto.FieldMapSearchDto;

@Repository
public class FieldDaoImpl extends SqlSessionFactoryService implements FieldDao {

	Map<String, Object> paramMap = new HashMap<String, Object>();
	
	@Override
	public List<FieldAreaDto> fieldAreaList() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldAreaList");
	}
	
	@Override
	public FieldRegistDto fieldSelect(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.field.dao.FieldDao.fieldSelect",field_code);
	}
	
	@Override
	public void fieldInsert(FieldRegistDto dto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.field.dao.FieldDao.fieldInsert", dto);
	}
	
	@Override
	public int fieldCodeCheck(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		paramMap.put("field_code", field_code);
		return getSqlSessionTemplate().selectOne("com.ksinfo.field.dao.FieldDao.fieldCodeCheck",paramMap);
	}
	
	@Override
	public int fieldCodeMaxNumber() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.field.dao.FieldDao.fieldCodeMaxNumber");
	}
	
	@Override
	public List<FieldInfoDetailDto> fieldInfoDetail(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		List<FieldInfoDetailDto> fieldDetailList = getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldDetail",field_code);
		return fieldDetailList;
	}
	
	@Override
	public List<FieldEmpLeftListDto> fieldEmpLeftList(String field_code) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldEmpLeftList",field_code);
	}
	
	@Override
	public List<FieldEmpRightListDto> fieldEmpRightList(FieldSearchDto search) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldEmpRightList",search);
	}
	
	@Override
	public void fieldEmpIn(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldEmpIn",dto);
	}
	
	@Override
	public void fieldEmpOut(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldEmpOut",dto);
	}
	
	@Override
	public void fieldLeaderSet(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldLeaderSet",dto);
		if(!dto.getAuth_code().equals("01")) {
			getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldAuthSet",dto);
		}
		
	}
	
	@Override
	public void fieldLeaderNullSet(String field_code) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldLeaderNullSet",field_code);
	}
	
	@Override
	public void fieldLeaderUnset(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldLeaderUnset",dto);
		if(!dto.getAuth_code().equals("01")) {
			getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldAuthUnSet",dto);
		}
	}
	
	@Override
	public void headFieldDel(String emp_id) {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.headFieldDel",emp_id);
	}
	
	@Override
	public String empAuthCode(String emp_id) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.field.dao.FieldDao.empAuthCode",emp_id);
	}
	
	@Override
	public void FieldInfoModify(FieldRegistDto dto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.field.dao.FieldDao.fieldModify",dto);
	}

	@Override
	public List<FieldListDto> fieldAllList(String field_search) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldAllList",field_search);
	}
	
	@Override
	public List<FieldEmpAllListDto> fieldEmpAllList() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldEmpAllList");
	}
	
	@Override
	public List<FieldMainViewDto> fieldMapAllField(FieldMapSearchDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldMapAll",dto);
	}
	
	@Override
	public List<FieldMainViewDto> fieldMapEmpField(FieldMapSearchDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return	getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldMapEmp",dto);
	}
	
	@Override
	public List<FieldSimpleInfoDto> fieldSimpleInfo(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.field.dao.FieldDao.fieldSimpleInfo",field_code);
	}
}

package com.ksinfo.field.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.field.dao.FieldDao;
import com.ksinfo.field.dto.FieldAreaDto;
import com.ksinfo.field.dto.FieldEmpAllListDto;
import com.ksinfo.field.dto.FieldEmpLeftListDto;
import com.ksinfo.field.dto.FieldEmpRightListDto;
import com.ksinfo.field.dto.FieldEmpUpdateDto;
import com.ksinfo.field.dto.FieldInfoDetailDto;
import com.ksinfo.field.dto.FieldListDto;
import com.ksinfo.field.dto.FieldMainViewDto;
import com.ksinfo.field.dto.FieldMapSearchDto;
import com.ksinfo.field.dto.FieldRegistDto;
import com.ksinfo.field.dto.FieldSearchDto;
import com.ksinfo.field.dto.FieldSimpleInfoDto;

@Service
public class FieldServiceImpl implements FieldService {

	@Inject
	FieldDao dao;
	
	@Override
	public List<FieldAreaDto> fieldAreaList() throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldAreaList();
	}
	
	@Override
	public FieldRegistDto fieldSelect(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldSelect(field_code);
	}
	
	@Override
	public void fieldInfoRegist(FieldRegistDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.fieldInsert(dto);
	}
	
	@Override
	public int fieldCodeCheck(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldCodeCheck(field_code);
	}
	
	@Override
	public int fieldCodeMaxNumber() throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldCodeMaxNumber();
	}
	
	@Override
	public List<FieldInfoDetailDto> fieldInfoDetail(String field_code) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldInfoDetail(field_code);
	}
	
	@Override
	public List<FieldEmpLeftListDto> fieldEmpLeftList(String field_code) {
		// TODO Auto-generated method stub
		return dao.fieldEmpLeftList(field_code);
	}
	
	@Override
	public List<FieldEmpRightListDto> fieldEmpRightList(FieldSearchDto search) {
		// TODO Auto-generated method stub
		return dao.fieldEmpRightList(search);
	}
	
	@Override
	public void fieldEmpIn(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		dao.fieldEmpIn(dto);
	}
	
	@Override
	public void fieldEmpOut(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		dao.fieldEmpOut(dto);
	}
	
	@Override
	public void fieldLeaderSet(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		dao.fieldLeaderSet(dto);
	}
	
	@Override
	public void fieldLeaderNullSet(String field_code) {
		// TODO Auto-generated method stub
		dao.fieldLeaderNullSet(field_code);
	}
	
	@Override
	public void fieldLeaderUnset(FieldEmpUpdateDto dto) {
		// TODO Auto-generated method stub
		dao.fieldLeaderUnset(dto);
	}
	
	@Override
	public void headFieldDel(String emp_id) {
		// TODO Auto-generated method stub
		dao.headFieldDel(emp_id);
	}
	
	@Override
	public String empAuthCode(String emp_id) {
		// TODO Auto-generated method stub
		return dao.empAuthCode(emp_id);
	}
	
	@Override
	public void FieldInfoModify(FieldRegistDto dto) throws SQLException {
		// TODO Auto-generated method stub
		dao.FieldInfoModify(dto);
	}
	
	@Override
	public List<FieldListDto> fieldAllList(String field_search) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldAllList(field_search);
	}
	
	@Override
	public List<FieldEmpAllListDto> fieldEmpAllList() throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldEmpAllList();
	}
	
	@Override
	public List<FieldMainViewDto> fieldMapAllField(FieldMapSearchDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldMapAllField(dto);
	}
	@Override
	public List<FieldMainViewDto> fieldMapEmpField(FieldMapSearchDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return dao.fieldMapEmpField(dto);
	}
	@Override
	public List<FieldSimpleInfoDto> fieldSimpleInfo(String field_code) throws SQLException {
		return dao.fieldSimpleInfo(field_code);
	}
	
}

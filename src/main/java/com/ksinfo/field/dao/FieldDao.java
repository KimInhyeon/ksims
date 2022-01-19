package com.ksinfo.field.dao;

import java.sql.SQLException;
import java.util.List;

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


public interface FieldDao {
	//field_regist
	public List<FieldAreaDto> fieldAreaList() throws SQLException;
	public void fieldInsert(FieldRegistDto dto) throws SQLException;
	public int fieldCodeCheck(String field_code) throws SQLException;
	public int fieldCodeMaxNumber() throws SQLException;
	//fieldDetail
	public List<FieldInfoDetailDto> fieldInfoDetail(String field_code) throws SQLException;
	//fieldDetailModal
	public List<FieldEmpLeftListDto> fieldEmpLeftList(String field_code);
	public List<FieldEmpRightListDto> fieldEmpRightList(FieldSearchDto search);
	public void fieldEmpIn(FieldEmpUpdateDto dto);
	public void fieldEmpOut(FieldEmpUpdateDto dto);
	public void fieldLeaderSet(FieldEmpUpdateDto dto);
	public void fieldLeaderNullSet(String field_code);
	public void fieldLeaderUnset(FieldEmpUpdateDto dto);
	public void headFieldDel(String emp_id);
	public String empAuthCode (String emp_id);
	//fieldModify
	public void FieldInfoModify(FieldRegistDto dto) throws SQLException;
	public FieldRegistDto fieldSelect(String field_code) throws SQLException;
	//fieldEmp
	public List<FieldListDto> fieldAllList(String field_search) throws SQLException;
	public List<FieldEmpAllListDto> fieldEmpAllList() throws SQLException;
	//fieldMain
	public List<FieldMainViewDto> fieldMapAllField(FieldMapSearchDto dto) throws SQLException;
	public List<FieldMainViewDto> fieldMapEmpField(FieldMapSearchDto dto) throws SQLException;
	public List<FieldSimpleInfoDto> fieldSimpleInfo(String field_code) throws SQLException;
	
}

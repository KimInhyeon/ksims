package com.ksinfo.employees.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.dto.EmpDtoResp;
import com.ksinfo.employees.dto.EmpFileDto;
import com.ksinfo.employees.dto.EmpIdDto;
import com.ksinfo.employees.dto.PositionMasterDto;

@Repository
public class EmployeesDaoImpl extends SqlSessionFactoryService implements EmployeesDao {

	@Override
	public String getPassword(String empId) throws SQLException {
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.getPassword", empId);
	}

	@Override
	public void changePassword(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.employees.dao.EmployeesDao.changePassword",empDto);
	}

	@Override
	public void empInsert(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.employees.dao.EmployeesDao.empInsert",empDto);
	}

	@Override
	public EmpDto employeesChange(int empIdx) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.employeesChange",empIdx);
	}
	
	@Override
	public EmpDto employeesHistory(Map<String, Integer> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.employeesHistory",paramMap);
	}

	@Override
	public void empManageUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.employees.dao.EmployeesDao.empManageUpdate",empDto);
		
	}

	@Override
	public void empEmployeeUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.employees.dao.EmployeesDao.empEmployeeUpdate",empDto);
	}

	@Override
	public List<EmpDto> employeeList(int start, int end, String auth, String empId) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", start);
        paramMap.put("end", end);
        paramMap.put("auth", auth);
        paramMap.put("empId", empId);
        
        return getSqlSessionTemplate().selectList("com.ksinfo.employees.dao.EmployeesDao.employeeList",paramMap);
	}

	@Override
	public int employeeCount() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.employeeCount");
	}

	@Override
	public List<PositionMasterDto> positionDropBox() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.employees.dao.EmployeesDao.positionDropBox");
	}
	
	@Override
	public List<DepartmentMasterDto> departDropBox() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.employees.dao.EmployeesDao.departDropBox");
	}

	@Override
	public EmpIdDto findLastEmpId() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.findLastEmpId");
	}
	
	@Override
	public int duplicationCheck(String empId) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.duplicationCheck",empId);
	}

	@Override
	public List<EmpFileDto> getFileList(String empId) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("com.ksinfo.employees.dao.EmployeesDao.getFileList",empId);
	}

	@Override
	public void registFile(EmpFileDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.employees.dao.EmployeesDao.registFile",empDto);
	}

	@Override
	public void deleteFile(int empFileIdx) throws Exception {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.employees.dao.EmployeesDao.deleteFile", empFileIdx);
	}
	
	@Override
	public EmpDtoResp findLastEmployee() throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectOne("com.ksinfo.employees.dao.EmployeesDao.findLastEmployee");
	}

	@Override
	public void preEmpUpdate(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().insert("com.ksinfo.employees.dao.EmployeesDao.empPreUpdate",empDto);		
	}

	@Override
	public void deleteEmp(EmpDto empDto) throws SQLException {
		// TODO Auto-generated method stub
		getSqlSessionTemplate().update("com.ksinfo.employees.dao.EmployeesDao.deleteEmp", empDto);
	}
}

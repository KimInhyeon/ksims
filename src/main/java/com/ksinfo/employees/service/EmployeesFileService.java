package com.ksinfo.employees.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.employees.dto.EmpFileDto;
import com.ksinfo.employees.dto.PubFileDto;

public interface EmployeesFileService {

	public List<EmpFileDto> getFileList(String empId) throws SQLException;
	
	public void saveFile(MultipartFile file, String filePath, String empId) throws Exception;
	
	public void deleteFile(int empFileIndxe) throws Exception;

}

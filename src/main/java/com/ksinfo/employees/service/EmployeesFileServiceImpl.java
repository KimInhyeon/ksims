package com.ksinfo.employees.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.employees.dao.EmployeesDao;
import com.ksinfo.employees.dto.EmpFileDto;
import com.ksinfo.employees.dto.PubFileDto;

@Service
public class EmployeesFileServiceImpl implements EmployeesFileService {

	@Inject
	EmployeesDao dao;

	@Override
	public List<EmpFileDto> getFileList(String empId) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getFileList(empId);
	}

	
    public void saveFile(MultipartFile multipartFile, String filePath, String empId) throws IOException {
	    EmpFileDto uploadDto = new EmpFileDto();
	    long curTime = System.currentTimeMillis();
	    String fileUrl = filePath + curTime + "_" + multipartFile.getOriginalFilename();

        try {
        	uploadDto.setEmpId(empId);
    	    uploadDto.setFileName(multipartFile.getOriginalFilename());
    	    //uploadDto.setPhoto_size(multipartFile.getSize());
    	    uploadDto.setFileUrl(curTime + "_" + multipartFile.getOriginalFilename());  
    	  
    	    dao.registFile(uploadDto);
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
      
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(fileUrl);
        fos.write(data);
        fos.close();
    }


	@Override
	public void deleteFile(int empFileIdx) throws Exception {
		dao.deleteFile(empFileIdx);
		
	}

}

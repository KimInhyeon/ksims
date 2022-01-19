package com.ksinfo.salary.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportExcelDAOService {

	public void GetExcelImport(String fileUploadPath, MultipartFile multi, String file_type);
	
}

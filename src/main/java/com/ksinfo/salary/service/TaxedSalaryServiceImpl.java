package com.ksinfo.salary.service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.salary.dao.FileControlDAO;
import com.ksinfo.salary.dao.FileControlDAOImpl;
import com.ksinfo.salary.dto.SalaryDto;
import com.ksinfo.salary.util.CreatePdfFormat;

@Service
public class TaxedSalaryServiceImpl implements TaxedSalaryService {

	@Inject
	FileControlDAO fcDao = new FileControlDAOImpl();
	
	
	public List<SalaryDto> GetSalaryListByYear(List<SalaryDto> sDTO, Map<String,Object> paraMap) {
		
		try {
						
			sDTO = fcDao.selectAll_SalaryForPaging(paraMap);
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sDTO;
	}
	
	public int selected_SalaryCount(int salaryCount, Map<String,Object> period) {
		 
		try {
			
			salaryCount = fcDao.selected_SalaryCount(period);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salaryCount;
	}
	
	public void ExportPDFFromDB(Map<String,Object> period) {
		List<SalaryDto> sDTO = new ArrayList<SalaryDto>();
		CreatePdfFormat cPDF = new CreatePdfFormat();
		
		File directory = new File(period.get("dest").toString());

		if (!directory.exists()) {
			directory.mkdirs();
		}

		try {
			sDTO = fcDao.selectAll_Salary(period);
		    cPDF.CreatePDFBaseInf(sDTO,period);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}

package com.ksinfo.salary.service;

import java.util.List;
import java.util.Map;

import com.ksinfo.salary.dto.SalaryDto;

public interface TaxedSalaryService {

	public List<SalaryDto> GetSalaryListByYear(List<SalaryDto> sDTO, Map<String,Object> paraMap);
	
	public int selected_SalaryCount(int salaryCount, Map<String,Object> period);
	
	public void ExportPDFFromDB(Map<String,Object> period);
}

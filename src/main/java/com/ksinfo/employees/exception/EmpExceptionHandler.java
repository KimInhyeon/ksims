package com.ksinfo.employees.exception;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.EmpIdDto;
import com.ksinfo.employees.dto.PositionMasterDto;
import com.ksinfo.employees.service.EmployeesCommonService;
import com.ksinfo.employees.service.EmployeesWriteService;

@ControllerAdvice
public class EmpExceptionHandler {
	
	@Autowired
	EmployeesWriteService wtService;
	@Autowired
	EmployeesCommonService cmnService;	

	@ExceptionHandler(EmpException.class) 
    public ModelAndView employeeExceptionHandler(EmpException e){ 
		ModelAndView mav = new ModelAndView();
		EmpIdDto curRegistId = new EmpIdDto();
		
		List<PositionMasterDto> pstDropBox = null;
		List<DepartmentMasterDto> dpDropBox = null;
		
		try {
			pstDropBox = cmnService.positionDropBox();
			dpDropBox = cmnService.departDropBox();
			curRegistId = wtService.findLastEmpId();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		mav.addObject("curRegistId", curRegistId);
		mav.addObject("pstDropBox", pstDropBox);
		mav.addObject("dpDropBox", dpDropBox);
		
		mav.addObject("exceptionMessage", e.getMessage());
		mav.setViewName("employees/employeesWriteForm");
		
		return mav;
    } 
}

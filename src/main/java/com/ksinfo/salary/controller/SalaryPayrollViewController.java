package com.ksinfo.salary.controller;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.salary.dto.SalarymgtDto;
import com.ksinfo.salary.service.SalaryMainService;
import com.ksinfo.salary.service.SalaryMainServiceImpl;
import com.ksinfo.salary.service.SalaryPayrollViewService;
import com.ksinfo.salary.service.SalaryPayrollViewServiceImpl;



@Controller
public class SalaryPayrollViewController {

	private static final long serialVersionUID = 1L;
	public static Reader reader;
	
	
	@Autowired
	SalaryPayrollViewService sPVS = new SalaryPayrollViewServiceImpl();

	@Autowired
	SalaryMainService sMS = new SalaryMainServiceImpl();
	
//	Map<String, String> condition = new HashMap<String, String>();
	
    
    @RequestMapping(value = "/SalaryPayroll_View_Detail", method = RequestMethod.POST)
    public ModelAndView Payroll_View_Detail(HttpServletRequest req, Model model, 
    		@RequestParam("work_year_month") String work_year_month,
    		@RequestParam("identificationNo") String identificationNo
    		) throws ServletException, IOException  {

		ModelAndView modelAndView = new ModelAndView();
		List<SalarymgtDto> year_month = null;
		
		try {
			year_month = sMS.salaryDropDownEmpList(identificationNo, work_year_month);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SalarymgtDto salaryPayroll = null;
		DecimalFormat df=new DecimalFormat("#.##");
		
		try {
			salaryPayroll = sPVS.salaryPayrollList(identificationNo, work_year_month.substring(0,4), work_year_month.substring(5,7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String workDays = df.format(salaryPayroll.getWorkDays());
		String paidVacationDays = df.format(salaryPayroll.getPaidVacationDays());
		String overtime = df.format(salaryPayroll.getOvertime());
		
		model.addAttribute("workDays",workDays);
		model.addAttribute("paidVacationDays",paidVacationDays);
		model.addAttribute("overtime",overtime);
		
		
		model.addAttribute("year", work_year_month.substring(0,4));
		model.addAttribute("month", work_year_month.substring(5,7));
		model.addAttribute("year_month", year_month);
		model.addAttribute("identificationNo", identificationNo);
		model.addAttribute("salaryPayroll", salaryPayroll);
		
		modelAndView.setViewName("Salary/payslipListOfOne");
		
		return modelAndView;
    }
	
}

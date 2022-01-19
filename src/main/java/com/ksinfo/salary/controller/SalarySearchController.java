package com.ksinfo.salary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.salary.dto.SalarymgtDto;
import com.ksinfo.salary.service.SalaryPayrollSearchService;
import com.ksinfo.salary.service.SalaryPayrollSearchServiceImpl;

@Controller
@SessionAttributes("adminFlg")
public class SalarySearchController extends HttpServlet{
	static final long serialVersionUID = 1L;

	
	@Autowired
	SalaryPayrollSearchService sPSS = new SalaryPayrollSearchServiceImpl();
	Map<String, String> condition = new HashMap<String, String>();
	
	PageIndexArr pIA = new PageIndexArr();

	private List<EmpDto> EmpDropDown;
	
	
    @RequestMapping(value = "/payslipSearch", method = RequestMethod.GET)
    public ModelAndView PayslipSearchView(HttpServletRequest req, Model model, HttpSession session) throws ServletException, IOException, SQLException  {
		URLCheckUtil.urlCheck(req);
    	ModelAndView modelAndView = new ModelAndView();
    	List<SalarymgtDto> year_month = null;
    	
    	String adminFlg = (String) session.getAttribute("adminFlg");
    	String identificationNo = (String)session.getAttribute("sid");
    	
    	String pastStartDate = req.getParameter("pastStartDate");
    	String pastEndDate = req.getParameter("pastEndDate");
    	String empId = req.getParameter("emp_id");
    	
    	if(adminFlg.equals("01")) {
    		this.EmpDropDown = sPSS.empNameDropDown();
    		condition.put("identificationNo", empId);
    	}else {
    		condition.put("identificationNo", identificationNo);
    	}
    	
    	if(pastStartDate != null && pastEndDate != null) {
    		if(pastStartDate.length() == 7 && pastEndDate.length() == 7) {
    			req.setAttribute("year", pastStartDate.substring(0,4));
    			req.setAttribute("month", pastStartDate.substring(5,7));
    			req.setAttribute("year2", pastEndDate.substring(0,4));
    			req.setAttribute("month2", pastEndDate.substring(5,7));
    		}
    	}
    	
    	condition.put("pastStartDate", pastStartDate);
		condition.put("pastEndDate", pastEndDate);
		condition.put("adminFlg", adminFlg);
    	year_month = sPSS.salaryPayrollSearch(condition);

    	
    	req.setAttribute("emp_id", empId);
    	req.setAttribute("EmpDropDown", this.EmpDropDown);
    	req.setAttribute("year_month", year_month);
    	modelAndView.setViewName("Salary/payslipSearch");
    	
    	
    	pIA.getURLforArray(req, "過去給料照会","0");
    	
        return modelAndView;
    }

}

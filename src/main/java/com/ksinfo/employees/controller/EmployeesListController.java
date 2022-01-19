package com.ksinfo.employees.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.service.EmployeesListService;

@Controller
public class EmployeesListController extends HttpServlet {
	
	@Autowired
	EmployeesListService service;
	
	private static final long serialVersionUID = 1L;
	private PagingModel page;	//Getter & Setter
	int count;   
	PageIndexArr pIA = new PageIndexArr(); 
	
	@RequestMapping(value = "/EmployeesListController", method = RequestMethod.GET)
	public ModelAndView EmployeesList(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
		URLCheckUtil.urlCheck(req);
		List<EmpDto> list = null;
		HttpSession session =req.getSession();
		ModelAndView modelAndView = new ModelAndView();
		
		String auth = (String)session.getAttribute("adminFlg");
		String empId = (String)session.getAttribute("sid");     
		
		try {
			count = service.employeeCount();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		page = new PagingModel(count, curPage);
		
		try {
			
			list = service.employeeList(page.getPageBegin(), page.getPageEnd(), auth, empId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("list", list);
		req.setAttribute("page", page);
		pIA.getURLforArray(req, "社員照会","0");
		
		if(auth.equals("01")) {
			modelAndView.setViewName("/employees/employeesList");	
		} else if(auth.equals("02") || auth.equals("03")) {
			modelAndView.setViewName("/employees/employeesListForEmp");			
		}
		
		return modelAndView;
	}

	public PagingModel getPage() {
		return page;
	}

	public void setPage(PagingModel page) {
		this.page = page;
	}
	
}

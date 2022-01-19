package com.ksinfo.employees.controller;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

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

import com.ksinfo.common.exception.PasswordChangeException;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.SecurityUtil;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.service.EmployeesChangePasswordService;

/**
 * Kim Inhyeon@2017/10/12
 * 
 */
@Controller
public class EmployeesChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Reader reader;
	private EmpDto paramClass;
	
	@Autowired
	EmployeesChangePasswordService service;
	PageIndexArr pIA = new PageIndexArr();
    
	@RequestMapping(value = "/EmployeesChangePasswordController", method = RequestMethod.GET)
	public ModelAndView employeesChangePassword(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{	
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/employees/employeesChangePasswordForm");
        pIA.getURLforArray(req, "暗号変更", "0");
		return modelAndView;
	}
	
	@RequestMapping(value = "/EmployeesChangePasswordController", method = RequestMethod.POST)
	public ModelAndView employeesChangePasswordFormForm(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException, SQLException{	
		SecurityUtil securitiUtil = new SecurityUtil();
		String dbPass=null;
		ModelAndView modelAndView = new ModelAndView();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String identificationNo = (String) session.getAttribute("sid");
		
		String passwordNow = req.getParameter("passwordNow");
		String secPasswordNow = securitiUtil.encryptSHA256(passwordNow);
		
		String password = req.getParameter("passwordChange");
		String secPassword = securitiUtil.encryptSHA256(password);
		
			dbPass = service.getPassword(identificationNo);
			
			//既存パスワードと新しいパスワード比較
			if(dbPass.equals(secPasswordNow)) {
				
				paramClass = new EmpDto();
				paramClass.setPassword(secPassword);
				paramClass.setEmpId(identificationNo);
				service.changePassword(paramClass);
				
				modelAndView.addObject("successMessage", "パスワード変更に成功しました。");
				modelAndView.setViewName("main");
			} else {
				throw new PasswordChangeException("パスワード変更に失敗しました。");
			}
		return modelAndView;
	}
}

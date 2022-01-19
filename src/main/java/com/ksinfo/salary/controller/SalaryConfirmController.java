package com.ksinfo.salary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.exception.IMSYSException;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.salary.dto.SalaryConfirmDto;
import com.ksinfo.salary.exception.SalaryConfirmException;
import com.ksinfo.salary.service.SalaryConfirmServiceImpl;

@Controller
public class SalaryConfirmController {
	static final long serialVersionUID = 1L;
	PageIndexArr pIA = new PageIndexArr();
	
	@Autowired
	SalaryConfirmServiceImpl confirmSevice = new SalaryConfirmServiceImpl();
	
	@RequestMapping(value = "/salaryConfirm", method = RequestMethod.GET)
	public ModelAndView salaryConfirmView(HttpServletRequest req, Model model) throws ServletException, IOException, SQLException  {
		URLCheckUtil.urlCheck(req);
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);		
		ModelAndView modelAndView = new ModelAndView();
		List<SalaryConfirmDto> confirmList = new ArrayList<SalaryConfirmDto>();
		
		modelAndView.addObject("successMessage", req.getParameter("successMessage"));

    	Calendar cal = Calendar.getInstance();
    	
	    String year = String.valueOf(cal.get(Calendar.YEAR));
	    String month = String.valueOf(cal.get(Calendar.MONTH));
	    //String month = String.valueOf(cal.get(Calendar.MONTH)+1);    //DEBUG時現在月設定
	    String curMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
	    
	    if (cal.get(Calendar.MONTH ) < 9) {
	    	curMonth = "0" + curMonth;
	    	month = "0" + month;
	    }
	    
	    String yearMonth = year + "/" + month;
		int conduct_cnt = 0;
		int salary_cnt = 0;
		int emp_cnt = 0;
		
		try {
			confirmList = confirmSevice.getTargetSalary(yearMonth, year, month);
			conduct_cnt = confirmSevice.getConductCountForSalary(yearMonth);
			salary_cnt = confirmSevice.getTargetSalaryCount(year, month);
			emp_cnt = confirmSevice.getEmpCountForSalary();
		} catch (DataAccessException e) {
			String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S01_JV");
			throw new IMSYSException(messages, e);
		}
		int confirmedFlgCnt = 0;
		
		for (int i = 0; i < confirmList.size(); i++) {
			if (confirmList.get(i).getCompFlg() != null) {
				if (confirmList.get(i).getCompFlg().equals("t")) {
					confirmedFlgCnt++;
				}
			}
		}
		
		if (confirmedFlgCnt == confirmList.size()) {
			modelAndView.addObject("monthlyComplete", "ok");
		} else {
			modelAndView.addObject("monthlyComplete", "no");
		}
		
		modelAndView.addObject("year", year);
		modelAndView.addObject("targetMonth", month);
		modelAndView.addObject("curMonth", curMonth);
		modelAndView.addObject("empCnt", emp_cnt);
		modelAndView.addObject("conductCnt", conduct_cnt);
		modelAndView.addObject("salaryCnt", salary_cnt);
		modelAndView.addObject("confirmList", confirmList);
		
		modelAndView.setViewName("Salary/salaryConfirm");
		
		pIA.getURLforArray(req, "給料確定画面","0");
		
		return modelAndView;
	}
		  
	@RequestMapping(value = "/salaryConfirm", method = RequestMethod.POST) 
	public ModelAndView salaryConfirm(HttpServletRequest req, Model model) throws ServletException, IOException, SQLException  {
		ModelAndView modelAndView = new ModelAndView();
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
	    Calendar cal = Calendar.getInstance();
        
	    String year = String.valueOf(cal.get(Calendar.YEAR));
	    String month = String.valueOf(cal.get(Calendar.MONTH));

		if (cal.get(Calendar.MONTH ) < 9) {
	    	month = "0" + month;
	    }
	    
	    String yearMonth = year + "/" + month;
		int conduct_cnt = 0;
		int salary_cnt = 0;
		
		try {
			conduct_cnt = confirmSevice.getConductCountForSalary(yearMonth);
			salary_cnt = confirmSevice.getTargetSalaryCount(year, month);
			confirmSevice.confirmSalaryList(year, month);
		} catch (DataAccessException e) {
			String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S01_JV");
			throw new IMSYSException(messages, e);
		}
		
		if (conduct_cnt != salary_cnt) {
			String messages = MessageUtils.getMessage("KS_IMSYS_SALERR_001_JV");
			throw new SalaryConfirmException(messages);
		}
		
		String messages = MessageUtils.getMessage("KS_IMSYS_SALMSG_001_JV");
		modelAndView.addObject("successMessage", messages);
		modelAndView.setViewName("redirect:salaryConfirm");
		return modelAndView;
	}
}

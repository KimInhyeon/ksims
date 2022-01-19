package com.ksinfo.salary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.EnvironmentConfig;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.salary.dto.SalarymgtDto;
import com.ksinfo.salary.service.SalaryMainService;
import com.ksinfo.salary.service.SalaryMainServiceImpl;
import com.ksinfo.salary.service.SalaryPayrollSearchService;
import com.ksinfo.salary.service.SalaryPayrollSearchServiceImpl;

@Controller
@SessionAttributes("adminFlg")
public class SalaryMainController extends HttpServlet{
	static final long serialVersionUID = 1L;
	private PagingModel page;	//Getter & Setter
	
	@Autowired
	Environment env;
	@Autowired
	EnvironmentConfig environmentConfig;
	
	@Autowired
	SalaryMainService sMS = new SalaryMainServiceImpl();
	@Autowired
	SalaryPayrollSearchService sPSS = new SalaryPayrollSearchServiceImpl();
	
	Map<String, String> condition = new HashMap<String, String>();
	PageIndexArr pIA = new PageIndexArr();
	
    @RequestMapping(value = "/MoveTosalaryMain", method = RequestMethod.GET)
    public ModelAndView MoveToSalaryMain(HttpServletRequest req, Model model) throws ServletException, IOException, SQLException  {
		URLCheckUtil.urlCheck(req);
    	ModelAndView modelAndView = new ModelAndView();
    	HttpSession session = req.getSession();
    	List<SalarymgtDto> list = null;
    	int count = 0;
    	
    	//properties Date
    	//String targetDate = env.getProperty("targetDate");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date now = new Date();
    	String targetDate = sdf.format(now);
System.out.println("targetDate:"+targetDate);    

    	String nowYear = targetDate.substring(0,4);
		String nowMonth = targetDate.substring(5,7);
    	
    	String adminFlg = (String)session.getAttribute("adminFlg");
    	String identificationNo = (String)session.getAttribute("sid");

    	List<SalarymgtDto> year_month = null;		//dropdown list
    	//dropdown select할때 들어가는값
    	String year = req.getParameter("year");
    	String month = req.getParameter("month");
System.out.println("year:"+year);    
System.out.println("month:"+month);    
    	
    	String pastStartDate = req.getParameter("pastStartDate");
		String pastEndDate = req.getParameter("pastEndDate");
		String emp_id = req.getParameter("emp_id");
    	
		//관리자일때 list && 사원일때 list
    	if(adminFlg.equals("01")) {		//　管理者
    		if(pastStartDate == null && pastEndDate==null && emp_id == null) {
    			System.out.println("pastStartDate1");    
    			year_month = sMS.salaryDropDownList(nowYear,nowMonth);	//　先月を基準に今年のリスト
    		}else {
    			System.out.println("pastStartDate2");    
    			year_month = sMS.salaryDropDownOne(year, month);	//　先月を基準に今年のリスト

    			Map<String, String> map = new HashMap<String, String>();
    			map.put("identificationNo", emp_id);
    			map.put("pastStartDate", pastStartDate);
    			map.put("pastEndDate", pastEndDate);
    			map.put("adminFlg", adminFlg);
    			year_month = sPSS.salaryPayrollSearch(map);
    			
    			req.setAttribute("pastEndDate", pastEndDate);
    			req.setAttribute("pastStartDate", pastStartDate);
    			req.setAttribute("emp_id", emp_id);
    		}

    		if(year == null && month == null) {
    			if(year_month.size() != 0) {
    				year = year_month.get(0).getYear();
    				month = year_month.get(0).getMonth();
    			}
    		}
    		//page 처리
    		condition.put("work_year_month", year+"-"+month+"-01");
        	count = sMS.salaryEmpListCount(condition.get("work_year_month"));
        	
    		int curPage = 1;
    		if(req.getParameter("curPage") !=null && req.getParameter("curPage").matches("^[0-9]+$")) {
    			curPage = Integer.parseInt(req.getParameter("curPage"));
    		}
    		
    		page = new PagingModel(count, curPage);
    		
    		condition.put("identificationNo", identificationNo);
    		condition.put("startPage", String.valueOf(page.getPageBegin()));
    		condition.put("endPage", String.valueOf(page.getPageEnd()));
    		
    		try {
    			list = sMS.salaryEmpList(condition);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else {//　社員
    		year_month = sMS.salaryDropDownEmpList(identificationNo,targetDate);
    		if(year == null && month == null && year_month.size() != 0) {
    			year = year_month.get(0).getYear();
    			month = year_month.get(0).getMonth();
    		}

    		try {
    			list = sMS.salaryEmpOne(identificationNo, year, month);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}

    	req.setAttribute("list", list);
		req.setAttribute("year_month",year_month);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		
		//메세지 출력
		if(req.getParameter("message") != null) {
			String messages = MessageUtils.getMessage("KS_IMSYS_PAYMSG_001_JV");
			modelAndView.addObject("successMessage", messages);
		}
		req.setAttribute("page", page);
		modelAndView.setViewName("Salary/salaryMain");
		pIA.getURLforArray(req, "給料照会","0");
    	
        return modelAndView;

    }

}

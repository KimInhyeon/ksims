package com.ksinfo.salary.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.EnvironmentConfig;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.PathCheckUtil;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.salary.dto.SalaryDto;
import com.ksinfo.salary.service.TaxedSalaryService;
import com.ksinfo.salary.service.TaxedSalaryServiceImpl;

@Controller
public class TaxedSalaryListController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private PagingModel page;  
	PageIndexArr pIA = new PageIndexArr(); 
	
	@Autowired
	TaxedSalaryService tSS = new TaxedSalaryServiceImpl();
	
	@Autowired
	Environment env;
	
	@Autowired
	EnvironmentConfig environmentConfig;
	
	String year = "";
	String year2 = "";
	String month="";
	String month2="";
	
	int curPage = 0;
	
    @RequestMapping(value = "/taxedSalarySearch", method = RequestMethod.GET)
    public ModelAndView MoveToSalaryMain(@RequestParam(value="curPage",required = false) String PageVal, 
    		@RequestParam(value="successMessage",required = false) String successMessage,
    		HttpServletRequest req,
    		Model model) throws ServletException, IOException  {
		URLCheckUtil.urlCheck(req);
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		
    	List<SalaryDto> sDTO = null;
    	ModelAndView modelAndView = new ModelAndView();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	int salaryCount = 0;

    	year = req.getParameter("InquiryYear");
    	year2= req.getParameter("InquiryYear2");
    	month = req.getParameter("InquiryMonth");
    	month2 = req.getParameter("InquiryMonth2");
    	
    	if(PageVal==null) {
    		PageVal="1";
    	}
    	curPage = Integer.parseInt(PageVal);
    	    	
		if(year==null || year.equals("") || year.equals("0")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date getYear = new Date();
			year = sdf.format(getYear);
			year = year.substring(0, year.indexOf("-"));
			year2=year;
			month="01";
			month2="12";
		}
    	if((month!=null)&&(month2!=null)){
    		if(month.length()<2) {
    			month="0"+month;
    		}
    		if(month2.length()<2) {
    			month2="0"+month2;
    		}
    	}
		
    	try {
    		condition.put("year", year);
			condition.put("year2", year2);
			condition.put("month",month);
			condition.put("month2",month2);
    		salaryCount = tSS.selected_SalaryCount(salaryCount, condition);
    		
    		/*paging 10 to 40*/
    		page=new PagingModel(salaryCount, curPage);
			page.setTotPage(salaryCount*10/40);
			page.setPageBegin((curPage - 1) * 40+1);
			page.setPageEnd((curPage - 1) * 40+40);
			page.setTotBlock();
			page.setBlockRange();
			
			condition.put("start", page.getPageBegin());
			condition.put("end", page.getPageEnd());
			
			sDTO = tSS.GetSalaryListByYear(sDTO ,condition);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}

    	modelAndView.addObject("page", page);
    	modelAndView.addObject("sDTO", sDTO);
    	modelAndView.addObject("year", year);
    	modelAndView.addObject("year2", year2);
    	modelAndView.addObject("month",month);
    	modelAndView.addObject("month2",month2);
    	
    	modelAndView.setViewName("Salary/taxedSalarySearch");
    	
    	pIA.getURLforArray(req, "給料出力","0");
    	if(successMessage!=null) {
    		modelAndView.addObject("successMessage",successMessage);
    	}

        return modelAndView;

    }
    
	
    @RequestMapping(value = "/ExportPDFFile", method = RequestMethod.GET)
    public void ExportPDFFile(@RequestParam("InquiryYear") String InquiryYear, HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException  {
    	Map<String, Object> condition = new HashMap<String, Object>();
		URLCheckUtil.urlCheck(req);
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
    	
		String dest = env.getProperty("pdfDest");
		
		PathCheckUtil.pathChekAndMakeDir(dest);
		
		String fileName=env.getProperty("pdfFileName");
		String[] headStr=env.getProperty("pdfHeadStr").split(",");
    	
    	year = req.getParameter("InquiryYear");
    	year2= req.getParameter("InquiryYear2");
    	month = req.getParameter("InquiryMonth");
    	month2 = req.getParameter("InquiryMonth2");
    	condition.put("year", year);
		condition.put("year2", year2);
		condition.put("month",month);
		condition.put("month2",month2);
		condition.put("dest", dest);
		condition.put("fileName", fileName);
		condition.put("headStr", headStr);
    	try {

    		tSS.ExportPDFFromDB(condition);
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	//req.setAttribute("successMessage","PDFファイル出力を完了しました。");
    	req.setAttribute("InquiryYear", year);
    	req.setAttribute("InquiryYear2", year2);
    	req.setAttribute("InquiryMonth", month);
    	req.setAttribute("InquiryMonth2",month2);
    	RequestDispatcher rd=req.getRequestDispatcher("/taxedSalarySearch");
    	
    	rd.forward(req, res);
    }
    
    @RequestMapping(value = "/mailSend", method = RequestMethod.GET)
    public void mailSend (HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException  {
    	 try {
   			 String fileName=env.getProperty("excelMailToolDest");
    	     File file = new File(fileName);
    	     Process p= Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath());
    	   
    	     p.waitFor();
    	   
    	  } catch (InterruptedException ex) {
    	     ex.printStackTrace();
    	  } catch (IOException ex) {
    	     ex.printStackTrace();
    	  }

    	 RequestDispatcher rd=req.getRequestDispatcher("main");
    	 rd.forward(req, res);
    }
 }

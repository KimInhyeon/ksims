package com.ksinfo.appraisal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalEmpDto;
import com.ksinfo.appraisal.dto.AppraisalHistoryDto;
import com.ksinfo.appraisal.service.AppraisalHistoryService;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;

@Controller
public class AppraisalHistoryController {
	@Inject
	AppraisalHistoryService appraisalHistoryService;
	
	private PagingModel page;  
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/AppraisalHistoryController",method = RequestMethod.GET)
	public ModelAndView appraisalHistoryController(@RequestParam(name="messagesFlg",required = false) String messagesFlg, HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg");
		String url="/main";
		String curP=req.getParameter("curPage");
		if(curP==null) {
			curP="1";
		}
		int listCount=0;
		Map<String,Object> paraMap=new HashMap<String, Object>();
		if(auth_code.equals("01")) {
			url="/appraisal/appraisalHistory_admin";
			listCount=appraisalHistoryService.getEmployeesCnt();
			page = new PagingModel(listCount, Integer.parseInt(curP));
			paraMap.put("start", page.getPageBegin());
			paraMap.put("end", page.getPageEnd());
			List<AppraisalEmpDto> employee_list=appraisalHistoryService.getEmployees(paraMap);
			if(messagesFlg!=null) {
				if(messagesFlg.equals("1")) {
					String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
					model.addObject("successMessage", messages);
					messagesFlg = "";
				}
				}
			pIA.getURLforArray(req, "承認履歴リスト", "0");
			model.addObject("employee_list",employee_list);
		}else {
			String emp_id=(String)session.getAttribute("sid");
			paraMap.put("emp_id", emp_id);
			listCount=appraisalHistoryService.getMyListCnt(paraMap);
			page = new PagingModel(listCount, Integer.parseInt(curP));
			paraMap.put("start", page.getPageBegin());
			paraMap.put("end", page.getPageEnd());
			AppraisalEmpDto emp=appraisalHistoryService.getEmployee(emp_id);
			List<AppraisalHistoryDto> dto=appraisalHistoryService.getMyList(paraMap);
			model.addObject("emp_info", emp);
			model.addObject("history_list",dto);
			url="/appraisal/appraisalHistory";
			pIA.getURLforArray(req, "承認履歴", "0");
			}
		model.addObject("pageUrl",req.getRequestURI()+"?");
		model.addObject("page", page);
		model.setViewName(url);

		return model;
	}
	
	
	@RequestMapping(value = "/AppraisalHistoryAdmin",method = RequestMethod.GET)
	public ModelAndView appraisalHistoryAdmin(HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg");
		String url="/main";
		Map<String,Object> paraMap=new HashMap<String, Object>();
		String curP=req.getParameter("curPage");
		if(curP==null) {
			curP="1";
		}
		int listCount=0;
		if(auth_code.equals("01")) {
			String emp_id=req.getParameter("emp_id");
			paraMap.put("emp_id", emp_id);
			listCount=appraisalHistoryService.getMyListCnt(paraMap);
			page = new PagingModel(listCount, Integer.parseInt(curP));
			paraMap.put("start", page.getPageBegin());
			paraMap.put("end", page.getPageEnd());
			AppraisalEmpDto emp=appraisalHistoryService.getEmployee(emp_id);
			List<AppraisalHistoryDto> dto=appraisalHistoryService.getMyList(paraMap);
			model.addObject("page",page);
			model.addObject("emp_info", emp);
			model.addObject("history_list",dto);
			model.addObject("pageUrl",req.getRequestURI()+"?emp_id="+emp_id+"&");
			url="/appraisal/appraisalHistory";
			pIA.getURLforArray(req, "承認履歴("+emp_id+")", "0");
		}	
		
		/////サービスタイム
		
		model.setViewName(url);
		return model;
	}
	
	
}//評価履歴


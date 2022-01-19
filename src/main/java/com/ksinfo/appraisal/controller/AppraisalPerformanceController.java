package com.ksinfo.appraisal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.appraisal.service.AppraisalPerformanceService;
import com.ksinfo.common.util.AuthCheckUtil;

@Controller
public class AppraisalPerformanceController {
	@Autowired
	Environment env;
	@Autowired
	private AppraisalPerformanceService appraisalPerformanceService;
	
	@RequestMapping(value = "/AppraisalPerformanceController",method = RequestMethod.POST)
	public ModelAndView appraisalPerformanceController(HttpServletRequest req) {
		ModelAndView model=new ModelAndView();
		String emp_id=req.getParameter("emp_id");
		String field_code=req.getParameter("field_code");
		String appraisal_start_date=req.getParameter("appraisal_start_date");
		String appraisal_end_date=req.getParameter("appraisal_end_date");
		
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		String sessionId = (String) session.getAttribute("sid");
		AuthCheckUtil.authCheckAdminAndSelf(authCode, sessionId, emp_id);
		
		if(appraisal_end_date==null) {
		appraisal_start_date=env.getProperty("appStartDate");
		appraisal_end_date=env.getProperty("appEndDate");
		}
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("emp_id", emp_id);
		paramMap.put("field_code", field_code);
		paramMap.put("appraisal_start_date", appraisal_start_date);
		paramMap.put("appraisal_end_date", appraisal_end_date);
		AppraisalMgtDto dto=appraisalPerformanceService.getAppraisal(paramMap);
		
		model.addObject("dto",dto);
		if(dto.isAccept_flg()) {
			model.setViewName("/appraisal/appraisalPerformanceFreeze");
			}else{
				if(authCode.equals("01")) {
					model.setViewName("/appraisal/appraisalPerformance");
	
				}else {
					model.setViewName("/appraisal/appraisalPerformanceFreeze");
				}
			}
		
		return model;
	}
	
	@RequestMapping(value = "/Appraisal_performance",method = RequestMethod.POST)
	public String appraisal_Performance(HttpServletRequest req) {
		HttpSession session=req.getSession();
		
		AppraisalMgtDto dto=new AppraisalMgtDto();
		dto.setCust_ability(Integer.parseInt(req.getParameter("cust_ability")));
		dto.setCust_ability_cmt(req.getParameter("cust_ability_cmt"));
		dto.setCust_keepwork(Integer.parseInt(req.getParameter("cust_keepwork")));
		dto.setCust_keepwork_cmt(req.getParameter("cust_keepwork_cmt"));
		dto.setCust_sum(Float.parseFloat(req.getParameter("cust_sum")));
		dto.setLd_sales(Integer.parseInt(req.getParameter("ld_sales")));
		dto.setField_role(req.getParameter("field_role"));
		dto.setLd_sales_cmt(req.getParameter("ld_sales_cmt"));
		dto.setLd_promote(Integer.parseInt(req.getParameter("ld_promote")));
		dto.setLd_promote_cmt(req.getParameter("ld_promote_cmt"));
		dto.setSales_sum(Float.parseFloat(req.getParameter("sales_sum")));
		dto.setAppraisal_idx(Integer.parseInt(req.getParameter("appraisal_idx")));
		dto.setEmp_id(req.getParameter("emp_id"));
		dto.setConduct_end_month(req.getParameter("conduct_end_month"));
		dto.setConduct_start_month(req.getParameter("conduct_start_month"));
		dto.setField_code(req.getParameter("field_code"));
		if(dto.getCust_ability_cmt().isEmpty()||dto.getCust_keepwork_cmt().isEmpty()) {
			dto.setCustomer_app_flg(false);
		}else {
			dto.setCustomer_app_flg(true);
		}
		if(dto.getLd_promote_cmt().isEmpty()||dto.getLd_sales_cmt().isEmpty()) {
			dto.setPerfomance_app_flg(false);
		}else {
			dto.setPerfomance_app_flg(true);
		}
		
		int successflg=0;
		
		int idx=appraisalPerformanceService.writeApprUniChk(dto);
		if(idx!=0) {
			dto.setRec_update_id((String)session.getAttribute("sid"));
			successflg=appraisalPerformanceService.updatePerfrAppraisal(dto);
		}else {
			dto.setField_position_code(req.getParameter("field_position_code"));
			dto.setField_env(req.getParameter("field_env"));
			dto.setField_tool(req.getParameter("field_tool"));
			dto.setRec_create_id((String)session.getAttribute("sid"));
			dto.setRec_update_id((String)session.getAttribute("sid"));
			successflg=appraisalPerformanceService.writePerfrAppraisal(dto);
		}
		return "redirect:/AppraisalMainController?successflg="+successflg;
	}
}

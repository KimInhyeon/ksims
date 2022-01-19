package com.ksinfo.appraisal.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalConfirmDto;
import com.ksinfo.appraisal.service.AppraisalConfirmService;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;

@Controller
public class AppraisalConfirmController {
	
	@Inject AppraisalConfirmService appraisalConfirmService;
	PageIndexArr pIA = new PageIndexArr();
	@RequestMapping(value = "/AppraisalConfirmController",method = RequestMethod.GET)
	public ModelAndView appraisalConfirmController(HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		model.addObject("appr_list", appraisalConfirmService.getApprList());
		pIA.getURLforArray(req, "評価承認","0");
		model.setViewName("appraisal/appraisalConfirm");
		return model;
	}
	
	@RequestMapping(value = "/AppraisalConfirmController", method = RequestMethod.POST)
	public String appraisalConfirmAccept(HttpServletRequest req) {
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(auth_code);
		
		String emp_id=(String)session.getAttribute("sid");
		String messagesFlg ="1";
		
		String[] checked=req.getParameterValues("confirmCheck");
		for(int i=0;i<checked.length;i++) {
			AppraisalConfirmDto dto=new AppraisalConfirmDto();
			String idx=checked[i];
			dto.setApp_grade(req.getParameter("app_grade_"+idx));
			dto.setApp_score(Float.parseFloat(req.getParameter("app_score_"+idx)));
			dto.setAppraisal_end_date(req.getParameter("appraisal_end_date_"+idx));
			dto.setAppraisal_idx(Integer.parseInt(req.getParameter("appraisal_idx_"+idx)));
			dto.setAppraisal_start_date(req.getParameter("appraisal_start_date_"+idx));
			dto.setEmp_id(req.getParameter("emp_id_"+idx));
			dto.setPosition_code(req.getParameter("position_code_"+idx));
			dto.setProject_name(req.getParameter("project_name_"+idx));
			dto.setProject_score(Float.parseFloat(req.getParameter("project_score_"+idx)));
			dto.setRec_create_id(emp_id);
			dto.setRec_update_id(emp_id);
			appraisalConfirmService.putAppraisal(dto);
		}
		
		return "redirect:/AppraisalHistoryController?messagesFlg="+ messagesFlg;
	}
}//評価承認

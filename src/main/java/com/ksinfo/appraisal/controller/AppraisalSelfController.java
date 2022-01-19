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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.appraisal.service.AppraisalSelfService;
import com.ksinfo.common.util.AuthCheckUtil;

@Controller
public class AppraisalSelfController {
	@Autowired
	Environment env;
	@Autowired
	private AppraisalSelfService appraisalSelfService;
	
	@RequestMapping(value = "/AppraisalSelfController",method = RequestMethod.POST)
	public ModelAndView appraisalSelfController(@RequestParam String field_code,HttpServletRequest req) {
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg");
		String emp_id="";
		String appraisal_start_date=env.getProperty("appStartDate");
		String appraisal_end_date=env.getProperty("appEndDate");
		
		if(auth_code.equals("01")) {
			emp_id=req.getParameter("emp_id");
		}else {
			emp_id=(String)session.getAttribute("sid");
			}
		AuthCheckUtil.authCheckAdminAndSelf(auth_code,(String)session.getAttribute("sid"),emp_id);
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("field_code", field_code);
		paramMap.put("emp_id", emp_id);
		paramMap.put("appraisal_start_date", appraisal_start_date);
		paramMap.put("appraisal_end_date", appraisal_end_date);
		AppraisalMgtDto dto=appraisalSelfService.getSelfAndLeaderApp(paramMap);

		model.addObject("dto",dto);
		model.addObject("page_type","self_appraisal");
		if(dto.isAccept_flg()) {
			model.setViewName("/appraisal/leaderFreeze");
		}else {
			model.setViewName("/appraisal/leader");
		}	
		return model;
	}
	
	@RequestMapping(value = "/Appraisal_self",method = RequestMethod.POST)
	public String appraisalSelfWriter(HttpServletRequest req) {
		HttpSession session=req.getSession();
		AppraisalMgtDto dto=new AppraisalMgtDto();

		dto.setEmp_id(req.getParameter("emp_id"));
		AuthCheckUtil.authCheckAdminAndSelf((String)session.getAttribute("adminFlg"),(String)session.getAttribute("sid"),dto.getEmp_id());
		dto.setConduct_end_month(req.getParameter("conduct_end_month"));
		dto.setConduct_start_month(req.getParameter("conduct_start_month"));
		dto.setField_code(req.getParameter("field_code"));
		dto.setField_role(req.getParameter("field_role"));
		dto.setField_position_code(req.getParameter("field_position_code"));
		dto.setField_env(req.getParameter("field_env"));
		dto.setField_tool(req.getParameter("field_tool"));
		dto.setSelf_advantage(req.getParameter("self_advantage"));
		dto.setSelf_application(Integer.parseInt(req.getParameter("self_application")));
		dto.setSelf_communication(Integer.parseInt(req.getParameter("self_communication")));
		dto.setSelf_contribution(Integer.parseInt(req.getParameter("self_contribution")));
		dto.setSelf_diligence(Integer.parseInt(req.getParameter("self_diligence")));
		dto.setSelf_eval(req.getParameter("self_eval"));
		dto.setSelf_learning(Integer.parseInt(req.getParameter("self_learning")));
		dto.setSelf_passion(Integer.parseInt(req.getParameter("self_passion")));
		dto.setSelf_responsibility(Integer.parseInt(req.getParameter("self_responsibility")));
		dto.setSelf_sum(Float.parseFloat(req.getParameter("self_sum")));
		dto.setSelf_understand(Integer.parseInt(req.getParameter("self_understand")));
		dto.setSelf_weak(req.getParameter("self_weak"));
		dto.setAppraisal_idx(Integer.parseInt(req.getParameter("appraisal_idx")));
		dto.setRec_update_id((String)session.getAttribute("sid"));	
		int successflg=0;
		
		int idx=appraisalSelfService.writeApprUniChk(dto);
		
		if(idx!=0) {
			successflg=appraisalSelfService.updateSelfAppr(dto);
		}else {
			dto.setRec_create_id((String)session.getAttribute("sid"));
			successflg=appraisalSelfService.writeSelfAppr(dto);
		}
		
		return "redirect:/AppraisalMainController?successflg="+successflg;
	}
	
}

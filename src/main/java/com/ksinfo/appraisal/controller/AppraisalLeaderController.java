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
import com.ksinfo.appraisal.service.AppraisalLeaderService;
import com.ksinfo.common.exception.AuthException;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;

@Controller
public class AppraisalLeaderController {
	@Autowired
	Environment env;
	@Autowired
	private AppraisalLeaderService appraisalLeaderService;
	
	@RequestMapping(value = "/AppraisalLeaderController",method = RequestMethod.POST)
	public ModelAndView appraisalLeaderController(HttpServletRequest req) {
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg");
		String sid=(String)session.getAttribute("sid");
		String emp_id=req.getParameter("emp_id");
		String field_code=req.getParameter("field_code");
		String appraisal_start_date=req.getParameter("appraisal_start_date");
		String appraisal_end_date=req.getParameter("appraisal_end_date");
		if(appraisal_end_date==null) {
		appraisal_start_date=env.getProperty("appStartDate");
		appraisal_end_date=env.getProperty("appEndDate");
		}
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("field_code", field_code);
		paramMap.put("emp_id", emp_id);
		paramMap.put("appraisal_start_date", appraisal_start_date);
		paramMap.put("appraisal_end_date", appraisal_end_date);
		AppraisalMgtDto dto=appraisalLeaderService.getSelfAndLeaderApp(paramMap);

		model.addObject("dto",dto);
		model.addObject("page_type","leader_appraisal");
		
		if(dto.isAccept_flg()) {
			AuthCheckUtil.authCheckAdminAndSelf(auth_code,sid,emp_id);
			model.setViewName("/appraisal/leaderFreeze");
		}else {
			paramMap.replace("emp_id",sid);
			if(auth_code.equals("01")) {
				model.setViewName("/appraisal/leader");
			}else if(appraisalLeaderService.getLeaderCode(paramMap).equals("02")&&!emp_id.equals(sid)){
				model.setViewName("/appraisal/leader");
			}else if(emp_id.equals(sid)) {
				model.setViewName("/appraisal/leaderFreeze");
			}else {
				String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
				throw new AuthException(messages);
			}
		}
		return model;
	}
	
	@RequestMapping(value = "/Appraisal_leader",method = RequestMethod.POST)
	public String appraisalSelfWriter(HttpServletRequest req) {
		HttpSession session=req.getSession();
		AppraisalMgtDto dto=new AppraisalMgtDto();
		
		dto.setEmp_id(req.getParameter("emp_id"));
		dto.setAppriaiser_id(req.getParameter("appriaiser_id"));
		dto.setAppriaiser_name(req.getParameter("appriaiser_name"));
		dto.setConduct_end_month(req.getParameter("conduct_end_month"));
		dto.setConduct_start_month(req.getParameter("conduct_start_month"));
		dto.setField_code(req.getParameter("field_code"));
		dto.setField_role(req.getParameter("field_role"));
		dto.setField_position_code(req.getParameter("field_position_code"));
		dto.setField_env(req.getParameter("field_env"));
		dto.setField_tool(req.getParameter("field_tool"));
		dto.setLd_advantage(req.getParameter("ld_advantage"));
		dto.setLd_application(Integer.parseInt(req.getParameter("ld_application")));
		dto.setLd_communication(Integer.parseInt(req.getParameter("ld_communication")));
		dto.setLd_contribution(Integer.parseInt(req.getParameter("ld_contribution")));
		dto.setLd_diligence(Integer.parseInt(req.getParameter("ld_diligence")));
		dto.setLd_eval(req.getParameter("ld_eval"));
		dto.setLd_learning(Integer.parseInt(req.getParameter("ld_learning")));
		dto.setLd_passion(Integer.parseInt(req.getParameter("ld_passion")));
		dto.setLd_responsibility(Integer.parseInt(req.getParameter("ld_responsibility")));
		dto.setLd_sum(Float.parseFloat(req.getParameter("ld_sum")));
		dto.setLd_understand(Integer.parseInt(req.getParameter("ld_understand")));
		dto.setLd_weak(req.getParameter("ld_weak"));
		dto.setRec_update_id((String)session.getAttribute("sid"));
		dto.setAppraisal_idx(Integer.parseInt(req.getParameter("appraisal_idx")));
		int successflg=0;
		
		int idx=appraisalLeaderService.writeApprUniChk(dto);
		
		if(idx!=0) {
			successflg=appraisalLeaderService.updateLeaderAppr(dto);
		}else {
			dto.setRec_create_id((String)session.getAttribute("sid"));
			successflg=appraisalLeaderService.writeLeaderAppr(dto);
		}
		return "redirect:/AppraisalMainController?successflg="+successflg;
	}
	
	
}

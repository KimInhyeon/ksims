package com.ksinfo.appraisal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalFieldListDto;
import com.ksinfo.appraisal.dto.AppraisalModalDto;
import com.ksinfo.appraisal.service.AppraisalMainService;
import com.ksinfo.common.util.EnvironmentConfig;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;


@Controller
public class AppraisalMainController {
	@Autowired
	Environment env;
	@Autowired
	EnvironmentConfig environmentConfig;
	PageIndexArr pIA = new PageIndexArr();
	
	@Autowired
	AppraisalMainService appraisalMainService;
	
	@RequestMapping(value = "/AppraisalMainController",method = RequestMethod.GET)
	public ModelAndView apporaisalMain(ModelAndView model, HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		URLCheckUtil.urlCheck(req);
		String successFlg= "";
		res.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg"); //auth_code = admin_Flg ログイン権限
		String emp_id=(String)session.getAttribute("sid");//emp_id
		String appraisal_start_date=env.getProperty("appStartDate");
		String appraisal_end_date=env.getProperty("appEndDate");
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("auth_code", auth_code);
		map.put("emp_id", emp_id);
		map.put("appraisal_start_date", appraisal_start_date);
		map.put("appraisal_end_date", appraisal_end_date);
		List<AppraisalFieldListDto> fieldList=appraisalMainService.getFieldList(map);		
		model.addObject("field_List",fieldList);
		model.addObject("appraisal_start_date",appraisal_start_date);
		model.addObject("appraisal_end_date",appraisal_end_date);
		
		if (req.getParameter("successflg") != null) {
			successFlg = req.getParameter("successflg");
		}
		if (successFlg.equals("1")) {
			String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
			model.addObject("successMessage", messages);
		}
		model.setViewName("/appraisal/appraisalMain");
		pIA.getURLforArray(req, "評価管理メイン", "0");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AppraisalModalController", method = RequestMethod.POST)
	public Map<String,Object> appraisalModal(@RequestBody Map<String, Object> map,HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		HttpSession session=req.getSession();	
		String field_code=(String)map.get("field_code");
		String field_name=(String)map.get("field_name");
		
		List<AppraisalModalDto> employeeList=new ArrayList<AppraisalModalDto>();
		if(field_code!="") {
				String auth_code=(String)session.getAttribute("adminFlg");
				String emp_id=(String)session.getAttribute("sid");
				String appraisal_start_date=env.getProperty("appStartDate");
				String appraisal_end_date=env.getProperty("appEndDate");
				Map<String,Object> paraMap=new HashMap<String, Object>();
				paraMap.put("auth_code",auth_code);
				paraMap.put("emp_id",emp_id);
				paraMap.put("field_code",field_code);
				paraMap.put("appraisal_start_date", appraisal_start_date);
				paraMap.put("appraisal_end_date", appraisal_end_date);
				String leader_code=appraisalMainService.getLeaderCode(paraMap);
				map.put("leader_code",leader_code);
				if(auth_code.equals("01")||leader_code.equals("02")) {
					employeeList=appraisalMainService.getEmployeeList(paraMap);
					}else {
					employeeList.add(appraisalMainService.getEmployeeList(paraMap).get(0));
					}
			
		}
		
		map.put("field_name", field_name);
		map.put("field_code",field_code);
		map.put("employee_List", employeeList);	
		map.put("employeeCnt",employeeList.size());


		return map;
	}
	

	
}

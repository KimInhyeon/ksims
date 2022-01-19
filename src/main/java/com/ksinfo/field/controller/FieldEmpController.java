package com.ksinfo.field.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.field.dto.FieldEmpAllListDto;
import com.ksinfo.field.dto.FieldEmpRightListDto;
import com.ksinfo.field.dto.FieldEmpUpdateDto;
import com.ksinfo.field.dto.FieldListDto;
import com.ksinfo.field.dto.FieldSearchDto;
import com.ksinfo.field.service.FieldService;

@Controller
public class FieldEmpController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	FieldService fieldService;
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/fieldEmpForm", method = RequestMethod.GET)
	public ModelAndView fieldEmpForm(HttpServletRequest req, Model model,HttpSession session, @RequestParam String messageFlg) throws ServletException, IOException, SQLException {
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		String field_search;
		String emp_search;
		
		field_search = req.getParameter("field_search");
		emp_search = req.getParameter("emp_search");
		
				
		List<FieldListDto> field_list = fieldService.fieldAllList(field_search);
		List<FieldEmpAllListDto> field_emp_list = fieldService.fieldEmpAllList();
			
		FieldSearchDto r_dto = new FieldSearchDto();
		r_dto.setWord(emp_search);
		List<FieldEmpRightListDto> list_r = fieldService.fieldEmpRightList(r_dto);
		
		req.setAttribute("field_search", field_search);
		req.setAttribute("emp_search", emp_search);
		req.setAttribute("fieldList", field_list);
		req.setAttribute("field_emp_list", field_emp_list);
		req.setAttribute("emp_all_list", list_r);
				
		if (messageFlg.equals("1")) {
			String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
			modelAndView.addObject("successMessage", messages);
		}
		modelAndView.setViewName("/field/fieldEmpForm");
		pIA.getURLforArray(req, "社員配置", "0");
		return modelAndView;
	}
	
	//leaderSet
	@RequestMapping(value = "/fieldEmpLeaderSet", method = RequestMethod.POST)
	public ModelAndView fieldEmpLeaderSet(HttpServletRequest req) throws ServletException, SQLException {
		ModelAndView modelAndView = new ModelAndView();
		String left_check_array[] = req.getParameter("left_check_result").split(",");	//emp_id
		
		for(int i=0; i<left_check_array.length;i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(req.getParameter("field_radio"));
			dto.setEmp_id(left_check_array[i]);
			dto.setAuth_code(fieldService.empAuthCode(left_check_array[i]));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldLeaderSet(dto);
		}//	for End
		
		modelAndView.addObject("messageFlg", "1");
		modelAndView.setViewName("redirect:/fieldEmpForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fieldEmpLeaderUnset", method = RequestMethod.POST)
	public ModelAndView fieldEmpLeaderUnset(HttpServletRequest req) throws SQLException {
		ModelAndView modelAndView = new ModelAndView();
		String left_check_array[] = req.getParameter("left_check_result").split(",");	//emp_id
		String field_code = req.getParameter("field_radio");
		
		for(int i=0; i<left_check_array.length;i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(field_code);
			dto.setEmp_id(left_check_array[i]);
			dto.setAuth_code(fieldService.empAuthCode(left_check_array[i]));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldLeaderUnset(dto);
		}// for End
		fieldService.fieldLeaderNullSet(field_code);	//現場にリーダーがない場合Null処理。
		
		modelAndView.addObject("messageFlg", "1");
		modelAndView.setViewName("redirect:/fieldEmpForm");
		return modelAndView;
	}
	
	//(<<)　ボタン
	@RequestMapping(value = "/fieldEmpIn", method = RequestMethod.POST)
	public ModelAndView fieldEmpIn(HttpServletRequest req) throws SQLException {
		ModelAndView modelAndView = new ModelAndView();
		String right_check_array[] = req.getParameter("right_check_result").split(",");	//emp_id
		
		for(int i=0; i<right_check_array.length;i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(req.getParameter("right_field_code"));
			dto.setEmp_id(right_check_array[i]);
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.headFieldDel(right_check_array[i]);
			fieldService.fieldEmpIn(dto);
		}//	for End
		
		modelAndView.addObject("messageFlg", "1");
		modelAndView.setViewName("redirect:/fieldEmpForm");
		return modelAndView;	
	}
	
	//(>>)　ボタン
	@RequestMapping(value = "/fieldEmpOut", method = RequestMethod.POST)
	public ModelAndView fieldEmpOut(HttpServletRequest req) throws SQLException {
		ModelAndView modelAndView = new ModelAndView();
		String left_check_array[] = req.getParameter("left_check_result").split(",");	//emp_id
		String field_code = req.getParameter("field_radio");
		
		for(int i=0; i<left_check_array.length;i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(field_code);
			dto.setEmp_id(left_check_array[i]);
			dto.setAuth_code(fieldService.empAuthCode(left_check_array[i]));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldEmpOut(dto);
			fieldService.fieldLeaderUnset(dto);
		}//	for End
		fieldService.fieldLeaderNullSet(field_code);
		
		modelAndView.addObject("messageFlg", "1");
		modelAndView.setViewName("redirect:/fieldEmpForm");
		return modelAndView;	
	}	
}

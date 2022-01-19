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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.field.dto.FieldEmpLeftListDto;
import com.ksinfo.field.dto.FieldEmpRightListDto;
import com.ksinfo.field.dto.FieldEmpUpdateDto;
import com.ksinfo.field.dto.FieldInfoDetailDto;
import com.ksinfo.field.dto.FieldSearchDto;
import com.ksinfo.field.service.FieldService;

/**
 * 
 */

@Controller
public class FieldInfoDetailController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	FieldService fieldService;
	
	private String field_code;
	
	//　親ウィンドウ
	@RequestMapping(value = "/fieldInfoDetailForm", method = RequestMethod.POST)
	public ModelAndView fieldInfoDetail(HttpServletRequest req, Model model) throws ServletException, SQLException, IOException{
		ModelAndView modelAndView = new ModelAndView();
		List<FieldInfoDetailDto> fieldDetail = null;
		this.field_code = req.getParameter("field_code");
		
		//　現場コードをDBに送信
		fieldDetail = fieldService.fieldInfoDetail(this.field_code); //jstl用
		req.setAttribute("list", fieldDetail);
		
		modelAndView.setViewName("field/fieldInfoDetailForm");
		return modelAndView;
	}
	
	//modal ajax 子ウィンドウ
	@RequestMapping(value = "/fieldModalEmpList", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView fieldModalEmpList(HttpServletRequest req, HttpSession session) throws ServletException {
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		FieldSearchDto search = new FieldSearchDto();
		search.setField_code(this.field_code);
		search.setWord(req.getParameter("word"));
	
		List<FieldEmpLeftListDto> list_l = fieldService.fieldEmpLeftList(this.field_code);
		List<FieldEmpRightListDto> list_r = fieldService.fieldEmpRightList(search);
		req.setAttribute("list_l", list_l);
		req.setAttribute("list_r", list_r);
		
		modelAndView.setViewName("field/fieldModalEmpList");
		return modelAndView;
	}
	
	//　リーダー設定ボタン
	@RequestMapping(value = "/modalLeaderSet", method = RequestMethod.POST)
	@ResponseBody
	public void modalLeaderSet(@RequestParam(value="left_check[]") List<String> left_check, HttpServletRequest req) throws SQLException {
		for(int i=0; i<left_check.size();i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(this.field_code);
			dto.setEmp_id(left_check.get(i));
			dto.setAuth_code(fieldService.empAuthCode(left_check.get(i)));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldLeaderSet(dto);
		}//	for End
	}
		
	//　リーダー取消ボタン
	@RequestMapping(value = "/modalLeaderUnset", method = RequestMethod.POST)
	@ResponseBody
	public void modalLeaderUnset(@RequestParam(value="left_check[]") List<String> left_check, HttpServletRequest req) throws SQLException {
		for(int i=0; i<left_check.size();i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(this.field_code);
			dto.setEmp_id(left_check.get(i));
			dto.setAuth_code(fieldService.empAuthCode(left_check.get(i)));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldLeaderUnset(dto);
		}// for End
		
		fieldService.fieldLeaderNullSet(this.field_code);	//現場にリーダーがない場合Null処理。
	}
	
	//(<<)　ボタン
	@RequestMapping(value = "/modalFieldIn", method = RequestMethod.GET)
	@ResponseBody
	public void modalFieldIn(@RequestParam(value="right_check[]") List<String> right_check , HttpServletRequest req) throws SQLException {
		URLCheckUtil.urlCheck(req);
		
		for(int i=0; i<right_check.size();i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(this.field_code);
			dto.setEmp_id(right_check.get(i));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.headFieldDel(right_check.get(i));
			fieldService.fieldEmpIn(dto);
		}//	for End
	}
	
	//(>>)　ボタン
	@RequestMapping(value = "/modalFieldOut", method = RequestMethod.GET)
	@ResponseBody
	public void modalFieldOut(@RequestParam(value="left_check[]") List<String> left_check, HttpServletRequest req) throws SQLException {
		
		for(int i=0; i<left_check.size();i++) {
			FieldEmpUpdateDto dto = new FieldEmpUpdateDto();
			dto.setField_code(this.field_code);
			dto.setEmp_id(left_check.get(i));
			dto.setAuth_code(fieldService.empAuthCode(left_check.get(i)));
			dto.setRec_update_id(req.getSession().getAttribute("sid").toString());
			fieldService.fieldEmpOut(dto);
			fieldService.fieldLeaderUnset(dto);
		}//	for End
		fieldService.fieldLeaderNullSet(this.field_code);
	}
	
}

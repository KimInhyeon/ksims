package com.ksinfo.field.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.ksinfo.common.exception.AuthException;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.field.dto.FieldMainViewDto;
import com.ksinfo.field.dto.FieldMapSearchDto;
import com.ksinfo.field.dto.FieldSimpleInfoDto;
import com.ksinfo.field.service.FieldService;

/**
 * Youm 2021/03/25
 */

@Controller
public class FieldMainViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	FieldService fieldService;
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/fieldList", method = RequestMethod.GET)
	public ModelAndView fieldList(HttpServletRequest req, HttpSession session, Model model) {
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		
		if(session.getAttribute("adminFlg") != null) {				//　ログインされていることチェック
			String messages = req.getParameter("successMessage");
			modelAndView.addObject("successMessage", messages);
			modelAndView.setViewName("field/fieldList");
		}else {
			String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
        	throw new AuthException(messages);
		}
		pIA.getURLforArray(req, "現場照会", "0");
		return modelAndView;
	}
	
	//ajax load
	@RequestMapping(value = "ajaxFieldView", method = RequestMethod.POST)
	@ResponseBody
	public List<FieldMainViewDto> ajaxFieldView(HttpSession session, @RequestParam(value="field_check") Boolean field_check ,HttpServletRequest req) throws SQLException {

		List<FieldMainViewDto> field_list = null;
		String search_select = req.getParameter("search_select");
		String word = req.getParameter("word");
		String[] field_code = (String[])session.getAttribute("fieldCode");
		
		if(field_check) {
			FieldMapSearchDto dto = new FieldMapSearchDto();
			dto.setSearch_select(search_select);
			dto.setWord(word);
			if(!session.getAttribute("adminFlg").equals("01")) {
				dto.setField_code(field_code);
			}
			field_list = fieldService.fieldMapAllField(dto);
		}else {
			FieldMapSearchDto dto = new FieldMapSearchDto();
			dto.setSearch_select(search_select);
			if(!session.getAttribute("adminFlg").equals("01")) {
				dto.setField_code(field_code);
			}
			if(search_select.equals("emp_id")) {
				dto.setWord(word);
				field_list = fieldService.fieldMapAllField(dto);
			}else{
				dto.setWord(word);
				field_list = fieldService.fieldMapEmpField(dto);
			}
		}
		return field_list;
	}
	
	@RequestMapping(value = "ajaxFieldInfo", method = RequestMethod.GET)
	@ResponseBody
	public FieldSimpleInfoDto ajaxFieldInfo(HttpServletRequest req) throws SQLException {
		URLCheckUtil.urlCheck(req);
		String field_code = req.getParameter("field_code");
		List<FieldSimpleInfoDto> list = fieldService.fieldSimpleInfo(field_code);
		FieldSimpleInfoDto info = new FieldSimpleInfoDto();
		
		String emp_name = "";
		String leader_name = "";
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getEmp_name() != null) {
				emp_name += list.get(i).getEmp_name()+"、 ";
			}
			if(list.get(i).getLeader_name() != null) {
				leader_name += list.get(i).getLeader_name()+"、 ";
			}
		}
		
		info.setField_name(list.get(0).getField_name());
		info.setField_difficulty(list.get(0).getField_difficulty());
		info.setField_addr(list.get(0).getField_addr());
		
		if(list.get(0).getWork_start_time() != null) {
			info.setWork_start_time(list.get(0).getWork_start_time().substring(0,5));
		}else {
			info.setWork_start_time("");
		}
		if(list.get(0).getWork_end_time() != null) {
			info.setWork_end_time(list.get(0).getWork_end_time().substring(0,5));
		}else {
			info.setWork_end_time("");
		}
		if(emp_name != "") {
			info.setEmp_name(emp_name.substring(0,emp_name.length()-2));
		}else {
			info.setEmp_name("");
		}
		if(leader_name != "") {
			info.setLeader_name(leader_name.substring(0,leader_name.length()-2));
		}else {
			info.setLeader_name("");
		}
		
		return info;
	}
}

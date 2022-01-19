package com.ksinfo.field.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.field.dto.FieldAreaDto;
import com.ksinfo.field.dto.FieldRegistDto;
import com.ksinfo.field.exception.FieldRegistException;
import com.ksinfo.field.service.FieldService;

/**
 * Youm 2021/03/04
 */

@Controller
public class FieldRegistController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	FieldService fieldService;
	PageIndexArr pIA = new PageIndexArr();
	
	//ajax code check
	@RequestMapping(value = "/codeCheck", method = RequestMethod.GET)
	@ResponseBody
	public int codeCheck(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		URLCheckUtil.urlCheck(req);
		String code_pattern = "^[0-9]{1}[0-9]{1}[0-9]{1}[0-9]{1}$";
		Pattern pattern_chk = Pattern.compile(code_pattern);
		Matcher matcher_chk = pattern_chk.matcher(req.getParameter("field_code"));

		int code_check = -1;
		if(matcher_chk.find()) {
			code_check = fieldService.fieldCodeCheck(req.getParameter("field_code"));
		}
		return code_check;
	}
	
	@RequestMapping(value = "/fieldInfoRegistForm", method = RequestMethod.GET)
	public ModelAndView fieldInfoRegistForm(HttpServletRequest req ,Model model, HttpSession session) throws ServletException, IOException, SQLException {
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		
		List<FieldAreaDto> area_list = fieldService.fieldAreaList();
		req.setAttribute("field_area", area_list);
		req.setAttribute("code_num", String.format("%04d", fieldService.fieldCodeMaxNumber()));
				
		String jsonText = mapper.writeValueAsString( area_list );
		model.addAttribute( "json", jsonText );
		modelAndView.setViewName("/field/fieldInfoRegistForm");
		pIA.getURLforArray(req, "現場登録", "0");
		return modelAndView;
	}
	
	@RequestMapping(value = "/fieldInfoClear", method = RequestMethod.GET)
	public ModelAndView fieldInfoClear(HttpServletRequest req ,Model model, HttpSession session) throws ServletException, IOException, SQLException {
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		
		List<FieldAreaDto> area_list = fieldService.fieldAreaList();
		req.setAttribute("field_area", area_list);
		req.setAttribute("code_num", String.format("%04d", fieldService.fieldCodeMaxNumber()));
				
		String jsonText = mapper.writeValueAsString( area_list );
		model.addAttribute( "json", jsonText );				
		
		String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_004_JV");
		modelAndView.addObject("successMessage", messages);
		modelAndView.setViewName("/field/fieldInfoRegistForm");

		return modelAndView;
	}
	
	//submit
	@RequestMapping(value = "/fieldInfoRegist_chk", method = RequestMethod.POST)
	public ModelAndView fieldInfoRegist_chk(HttpServletRequest req, HttpServletResponse res, Model model) throws ServletException, SQLException, IOException, ParseException {
		ModelAndView modelAndView = new ModelAndView();
		
		SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
		
		FieldRegistDto fieldinfoDto = new FieldRegistDto();
		fieldinfoDto.setField_name(req.getParameter("field_name"));
		fieldinfoDto.setField_addr(req.getParameter("field_addr1")+req.getParameter("field_addr2"));
		fieldinfoDto.setField_difficulty(req.getParameter("field_difficulty"));
		fieldinfoDto.setField_code(req.getParameter("field_code"));
		fieldinfoDto.setWork_start_time(sdf_time.parse(req.getParameter("work_start_time")));
		fieldinfoDto.setWork_end_time(sdf_time.parse(req.getParameter("work_end_time")));
		fieldinfoDto.setWork_break_time1(req.getParameter("work_break_time1"));
		fieldinfoDto.setWork_break_time2(req.getParameter("work_break_time2"));
		fieldinfoDto.setReg_date(sdf_date.parse(req.getParameter("reg_date")));
		fieldinfoDto.setField_memo(req.getParameter("field_memo"));
		//　追加カラム
		fieldinfoDto.setField_tool(req.getParameter("field_tool"));
		fieldinfoDto.setField_env(req.getParameter("field_env"));
		fieldinfoDto.setField_area_code(req.getParameter("addr_select"));
		
		//　現場コード重複確認
		if(fieldService.fieldCodeCheck(req.getParameter("field_code")) == 0 && req.getParameter("field_code").length() <5) {
			fieldService.fieldInfoRegist(fieldinfoDto);
			String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_002_JV");
			modelAndView.addObject("successMessage", messages);
			modelAndView.setViewName("redirect:fieldList");
		}else {
			String messages = MessageUtils.getMessage("KS_IMSYS_FLDERR_001_JV");
			throw new FieldRegistException(messages);
		}
		
		return modelAndView;
	}
}

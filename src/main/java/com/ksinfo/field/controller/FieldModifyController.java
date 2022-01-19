package com.ksinfo.field.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.field.dto.FieldAreaDto;
import com.ksinfo.field.dto.FieldRegistDto;
import com.ksinfo.field.service.FieldService;

/**
 * Youm 2021/03/27
 */

@Controller
public class FieldModifyController {
	@Autowired
	FieldService fieldService;
	
	private String field_code;
	
	@RequestMapping(value = "/fieldInfoModifyView", method = RequestMethod.POST)
	public ModelAndView fieldInfoModifyView(HttpServletRequest req ,Model model,HttpSession session) throws SQLException {
		
		ModelAndView modelAndView = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		field_code = req.getParameter("field_code");
		String jsonText = null;
		
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		
		FieldRegistDto fieldinfoDto = fieldService.fieldSelect(field_code);
		SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
		req.setAttribute("list", fieldinfoDto);
		
		if(fieldinfoDto.getReg_date() != null) {
			req.setAttribute("regDate", sdf_date.format(fieldinfoDto.getReg_date()));
		}
		if(fieldinfoDto.getWork_start_time() != null) {
			req.setAttribute("start_time", sdf_time.format(fieldinfoDto.getWork_start_time()));
		}
		if(fieldinfoDto.getWork_end_time() != null) {
			req.setAttribute("end_time", sdf_time.format(fieldinfoDto.getWork_end_time()));
		}

		List<FieldAreaDto> area_list = fieldService.fieldAreaList();
		req.setAttribute("field_area", area_list);
		
		String area_idx= null;
		String area_name = null;
		
		for(int x=0; x<area_list.size();x++) {
			if(fieldinfoDto.getField_addr().contains(area_list.get(x).getField_area_name()) 
					&& Integer.parseInt(fieldinfoDto.getField_area_code().trim())>0) {
				area_idx = area_list.get(x).getField_area_idx();
				area_name = area_list.get(x).getField_area_name();
			}
		}
		if(area_idx != null) {
			req.setAttribute("area_idx", area_idx);
			if(Integer.parseInt(area_idx) > 23) {
				req.setAttribute("area_name",area_name);
				req.setAttribute("area_name2", fieldinfoDto.getField_addr().replaceAll(area_name,""));
			}else {
				req.setAttribute("area_name","東京都"+area_name);
				req.setAttribute("area_name2", fieldinfoDto.getField_addr().replaceAll("東京都"+area_name,""));
			}
		}else {
			req.setAttribute("area_idx", 27);
			req.setAttribute("area_name", fieldinfoDto.getField_addr());
		}
		

		
		try {
			jsonText = mapper.writeValueAsString( area_list );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		model.addAttribute( "json", jsonText );
		modelAndView.setViewName("field/fieldInfoModify");
		return modelAndView;
	}
	
	//submit
	@RequestMapping(value = "/fieldInfoModifyChk", method = RequestMethod.POST)
	public ModelAndView fieldInfoModifyChk(HttpServletRequest req, HttpServletResponse res, Model model) throws ServletException, SQLException, IOException, ParseException {
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
		fieldinfoDto.setField_tool(req.getParameter("field_tool"));
		fieldinfoDto.setField_env(req.getParameter("field_env"));
		fieldinfoDto.setField_area_code(req.getParameter("addr_select"));
		
		//　修正成功メッセージ page
		fieldService.FieldInfoModify(fieldinfoDto);
		String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_003_JV");
		modelAndView.addObject("successMessage", messages);
		modelAndView.setViewName("field/fieldList");
		
		return modelAndView;
	}
	
}

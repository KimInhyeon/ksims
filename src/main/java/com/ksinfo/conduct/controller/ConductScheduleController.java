package com.ksinfo.conduct.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.conduct.dto.ConductScheduleDto;
import com.ksinfo.conduct.dto.ConductScheduleEmpDto;
import com.ksinfo.conduct.dto.WorkCodeMasterDto;
import com.ksinfo.conduct.service.ConductBossService;
import com.ksinfo.conduct.service.ConductScheduleService;

@Controller
public class ConductScheduleController{
	@Autowired
	ConductScheduleService scheduleService;
	
	@Autowired
	ConductBossService bossService;
	
	Map<String, String> condition = new HashMap<String, String>();
	
	@RequestMapping(value="/conductSchedule", method=RequestMethod.GET)
	public ModelAndView getConductMainView(HttpSession session, HttpServletRequest req) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		condition.put("field_code", req.getParameter("field_code"));
		condition.put("emp_id", req.getParameter("sid"));
		//boss
		ConductScheduleEmpDto sch = null;
		try {
			sch = bossService.conductScheduleEmp(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WorkCodeMasterDto> workDropBox = scheduleService.workDropBox();
		
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
		Date d1 = f.parse(sch.getWork_end_time());
		Date d2 = f.parse(sch.getWork_start_time());
		long diff = d1.getTime() - d2.getTime();
		long hour = diff / (1000*60*60);
		long min, min2 = 0;
		if(hour%1!=0) {

			min = diff / (1000*60);
			min2 = min%60;
		}
		
		String hour0, min0;
		
		if(hour<10) {
			hour0 = hour+"";
		}else {
			hour0 = "0"+hour;
		}
		if(min2<10) {
			min0 = min2+"";
		}else {
			min0 = "0"+min2;
		}
		
		String defaultwork = hour0 + ":"+ min0; 
		System.out.println(defaultwork);
		
		modelAndView.addObject("defaultwork", defaultwork);
		modelAndView.addObject("sch", sch);
		
		ConductScheduleDto dto = new ConductScheduleDto();
		dto.setEmp_id(req.getParameter("sid"));
		dto.setField_code(req.getParameter("field_code"));
		
		String month = req.getParameter("inqueryMonth");
		String y = month.substring(0,4);
		String m = month.substring(5,7);
		
		dto.setConduct_year(y);
		dto.setConduct_month(m);
		
		List<ConductScheduleDto> cslist = scheduleService.getConductSchedule(dto);
		
		for(int i=0; i<cslist.size(); i++) {
			ConductScheduleDto sdto = cslist.get(i);
			
			sdto.setSttime(sdto.getWork_start_time());
			sdto.setEdtime(sdto.getWork_end_time());
			
			int break_time = sdto.getWork_break_time1() + sdto.getWork_break_time2(); 
			//BREAK_TIME設定
		    double addHour = Math.floor(break_time / 60);
		    int addMin   = break_time % 60;
		    String sDisp1 = new DecimalFormat("00").format(addHour);
		    sdto.setWork_break_time(sDisp1+ ":" + String.format("%02d",addMin));

		    cslist.set(i, sdto);
		   
		}
		
		modelAndView.addObject("cslist", cslist);
		modelAndView.addObject("workDropBox", workDropBox);
		modelAndView.setViewName("conduct/conductSchedule");
		
		return modelAndView;
	}
	
}
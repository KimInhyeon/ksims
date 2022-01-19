package com.ksinfo.conduct.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.conduct.dto.ConductScheduleUpdateDto;
import com.ksinfo.conduct.service.ConductBossService;
import com.ksinfo.conduct.service.ConductScheduleUpdateService;

@Controller
public class ConductScheduleUpdateController{		
	@Autowired
	ConductScheduleUpdateService scheduleUpdateService;
	
	@Autowired
	ConductBossService bossService;
	
	Map<String, String> condition = new HashMap<String, String>();

	@RequestMapping(value="/ConductScheduleUpdate", method=RequestMethod.POST)
	public ModelAndView getConductMainView(HttpSession session, HttpServletRequest req) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
	
		String[] wstime = req.getParameterValues("wsttime");
		String[] wetime = req.getParameterValues("wedtime");
		String[] wbtime = req.getParameterValues("wbtime");
		String[] wkhour = req.getParameterValues("wktime");
		String[] ovhour = req.getParameterValues("ovtime");
		String[] wktime = req.getParameterValues("worktime");
		String[] totwktime = req.getParameterValues("tot_work_time");
		String[] conductnote = req.getParameterValues("conduct_note");
		String[] cdid = req.getParameterValues("ks_conduct_id");
		String[] workcode = req.getParameterValues("workcode");
		
		ConductScheduleUpdateDto dto = new ConductScheduleUpdateDto();
		
		List<ConductScheduleUpdateDto> cslist = new ArrayList<ConductScheduleUpdateDto>();
		
		for(int i=0;i<cdid.length;i++) {
			
			dto.setWork_start_time(wstime[i]);
			dto.setWork_end_time(wetime[i]);
			dto.setWork_break_time(wbtime[i]);
			dto.setWorktime_hours(wkhour[i]);
			dto.setOvertime_hours(ovhour[i]);
			dto.setWorktime(wktime[i]);
			dto.setTot_work_time(Double.parseDouble(totwktime[i]));
			dto.setConduct_note(conductnote[i]);
			dto.setKs_conduct_id(Integer.parseInt(cdid[i]));
			dto.setWork_code(workcode[i]);
			
			
			scheduleUpdateService.getConductScheduleUpdate(dto);
			cslist.add(dto);
		}
		
		modelAndView.addObject("cslist", cslist);
		//modelAndView.setViewName("conduct/conductMain");
		//return modelAndView;
        modelAndView.setViewName("/main");
		
		return modelAndView;
	}
}

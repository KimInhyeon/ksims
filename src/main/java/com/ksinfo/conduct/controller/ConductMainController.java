package com.ksinfo.conduct.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.conduct.dto.ConductBossDto;
import com.ksinfo.conduct.dto.ConductTeamDto;
import com.ksinfo.conduct.service.ConductBossService;
import com.ksinfo.conduct.service.ConductTeamService;

@Controller
public class ConductMainController{


	@Autowired
	ConductTeamService teamService;
	@Autowired
	ConductBossService bossService;
	PageIndexArr pIA = new PageIndexArr();
	private PagingModel page;
	int count;
	
	@RequestMapping(value="/conductMain", method=RequestMethod.GET)
	public ModelAndView getConductMainView(HttpSession session, HttpServletRequest request) throws ParseException, SQLException {
		URLCheckUtil.urlCheck(request);
		ModelAndView modelAndView = new ModelAndView();
		List<ConductBossDto> conductBossList = null;// 관리자
		List<ConductTeamDto> conductTeamList = null;// 리더, 사원
		List<String> sheetList = null; //확정flg 확인
		
		//Date
		Date date=null;
		Calendar cal = Calendar.getInstance();
		
		boolean pastFlg = false;	//과거확정 확인 flg
		boolean compflg = false;	//현재확정 확인 flg
		boolean cdtFlg = false;		//근무확정 버튼용 flg
		
		String emp_id = (String)session.getAttribute("sid");//로그인한 아이디
		String flags =(String)session.getAttribute("adminFlg");//로그인한 플래그
		String inqueryMonth=request.getParameter("inqueryMonth");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		
		if(inqueryMonth==null) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			date=cal.getTime();//new Date();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			inqueryMonth=sdf.format(date).substring(0, 7);
		}else {
			date=sdf.parse(inqueryMonth+"-01");
			cal.setTime(date);
			cal.add(Calendar.MONTH, -1);
		}
		
		if(flags.equals("01")) {//관리자
			try {
				String curPage = request.getParameter("curPage");
				if(curPage==null) {
					curPage="1";
				}
				count=bossService.countThisMonth(date);
				page = new PagingModel(count, Integer.parseInt(curPage));
				modelAndView.addObject("page",page);
				conductBossList = bossService.getConductBossPage(page.getPageBegin(),page.getPageEnd(),date);
			}catch(Exception e) {
				e.printStackTrace();
			}

			//과거 compflg 현재 compflg 확인
			pastFlg = bossService.conductCompFlg(sdf.parse(sdf.format(cal.getTime()).substring(0,7)+"-01"));
			compflg = bossService.conductCompFlg(sdf.parse(inqueryMonth+"-01"));
			
//			List<ConductBossDto> cdtlist = bossService.conductBossList(date);
			sheetList  = bossService.conductSheetList(date);
			
			if(pastFlg && !compflg && sheetList.size() == 0){//과거근무 확정 && 현재 근무 확정이 아닐때
				cdtFlg = true;
			}else {
				cdtFlg = false;
			}
			
			modelAndView.addObject("conductWorkList", conductBossList);
			modelAndView.setViewName("conduct/conductRegist");
			pIA.getURLforArray(request, "勤務情報登録", "0");
		}
	
		if(flags.equals("02")||flags.equals("03")) {//리더
			ConductTeamDto conductTeamDto = new ConductTeamDto();
			conductTeamDto.setEmp_id(emp_id);
			conductTeamDto.setWork_year_month(date);
			if(inqueryMonth.equals(sdf.format(new Date()).substring(0, 7))){
				conductTeamList = teamService.getConductTeamList(conductTeamDto);
			}else {
				conductTeamList = teamService.getPastTeamList(conductTeamDto); 
			}
			
			modelAndView.addObject("ConductTeamList", conductTeamList);
			modelAndView.setViewName("conduct/conductMain");
			pIA.getURLforArray(request, "勤務情報", "0");
		}
		
//		System.out.println(cdtFlg);
		
		modelAndView.addObject("sheetList",sheetList);
		modelAndView.addObject("pastMonthFlg",inqueryMonth);
		modelAndView.addObject("compFlg",compflg);
		modelAndView.addObject("cdtFlg",cdtFlg);
		modelAndView.addObject("inqueryMonth",inqueryMonth);
		return modelAndView;
	}
	
}

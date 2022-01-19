package com.ksinfo.conduct.controller;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.exception.MemberException;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.PathCheckUtil;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.conduct.dto.ConductMonthDayDto;
import com.ksinfo.conduct.dto.ConductUpdateDto;
import com.ksinfo.conduct.dto.ConductWorkDto;
import com.ksinfo.conduct.service.ConductBossService;
import com.ksinfo.conduct.service.ConductDownLoadService;
import com.ksinfo.conduct.service.ConductUpdateSerivce;
import com.ksinfo.conduct.service.ConductWorkResultService;
import com.ksinfo.conduct.service.ConductWorkService;

@Controller
public class ConductWorkController {
	
	@Autowired
	ConductWorkService workService;
	@Autowired
	ConductUpdateSerivce updateService;
	@Autowired
	ConductDownLoadService downService;
	@Autowired
	ConductBossService bossService;
	@Autowired
	ConductWorkResultService workResultService;
	@Autowired
	Environment env;
	
	PageIndexArr pIA = new PageIndexArr();
	private PagingModel page;
	int count;
	Map<String, String> condition;	
	
	List<String> sheetList;
	
	private double pcnt = 0;
	
	@ResponseBody
	@RequestMapping(value = "/progressTest", method = RequestMethod.POST)
	public Map<String,Object> progressTest(@RequestBody Map<String,Object> map, HttpServletRequest req, HttpServletResponse resp) {
		map.put("percent", this.pcnt);
		return map;
	}
	
	@RequestMapping(value="/conductRegist", method=RequestMethod.GET)
	public ModelAndView getConductRegistView(@RequestParam("fieldCode") String field_code, HttpSession session, HttpServletRequest request) throws ParseException {

		URLCheckUtil.urlCheck(request);
		ModelAndView modelAndView = new ModelAndView();
		String session_emp_id = (String)session.getAttribute("sid");
		String authCode = (String)session.getAttribute("adminFlg");

//		List<String> sheetList = null; //확정flg 확인
		boolean compflg = false;
		Date date=null;
		String inqueryMonth=request.getParameter("inqueryMonth");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(inqueryMonth==null) {
			date=new Date();
			inqueryMonth=sdf.format(date).substring(0, 7);
		}else {
			date=sdf.parse(inqueryMonth+"-01");
		}
		List<ConductWorkDto> conductWorkResultList = null;
		
		ConductWorkDto dto = new ConductWorkDto();
		dto.setWork_year_month(date);
		dto.setField_code(field_code);
		
		try {
			sheetList = bossService.leaderSheetList(dto);
			conductWorkResultList = workResultService.getConductWorkResultList(field_code, authCode, session_emp_id, inqueryMonth);
			compflg = bossService.conductCompFlg(date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(conductWorkResultList.isEmpty()) {
			throw new MemberException("勤務表が存在しません。管理者に問い合わせください");
		}
		
		modelAndView.addObject("conductWorkList", conductWorkResultList);
		
		modelAndView.addObject("sheetList",sheetList);
		modelAndView.setViewName("conduct/conductRegist");
		modelAndView.addObject("inqueryMonth",inqueryMonth);
		modelAndView.addObject("compFlg",compflg);
		pIA.getURLforArray(request, " 勤務情報登録", "0");
		return modelAndView;
	}
	@RequestMapping(value="/conductUpdate", method=RequestMethod.POST)//근무표 입력, 파일 업로드
	public ModelAndView getConductUpdateView(HttpSession session, HttpServletRequest request,MultipartHttpServletRequest req, HttpServletResponse response) throws ParseException {
			ModelAndView modelAndView = new ModelAndView();
			List<ConductWorkDto> conductWorkResultList = null;
			String session_emp_id = (String)session.getAttribute("sid");
			String session_flag = (String)session.getAttribute("adminFlg");
			String[] workId = request.getParameterValues("workId");
			String[] field_codes = request.getParameterValues("workCode");
			String[] plan_work_days = request.getParameterValues("plan_work_days");
			String[] vacations = request.getParameterValues("vacations");
			String[] workings  = request.getParameterValues("workings");
			String[] worktimes = request.getParameterValues("worktime");
			String[] fileNames = request.getParameterValues("fileName");
			String[] filePaths = request.getParameterValues("fileUrl");
			String field_code = request.getParameter("workCode");
			Date date=null;
			String inqueryMonth=request.getParameter("inqueryMonth");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(inqueryMonth==null) {
				date=new Date();
				inqueryMonth=sdf.format(date).substring(0, 7);
			}else {
				date=sdf.parse(inqueryMonth+"-01");
			}

			ConductUpdateDto conductUpdateDto = new ConductUpdateDto();
			//파일업로드
			ArrayList<String> fileUrlList = new ArrayList<String>();
			ArrayList<String> fileNameList = new ArrayList<String>();
			String rootUploadDir = env.getProperty("conductDest");
			
			PathCheckUtil.pathChekAndMakeDir(rootUploadDir);
	        
	        MultipartFile mFile = null;
	        String orgFileName = ""; //진짜 파일명
	        String sysFileName = ""; //변환된 파일명
			for (int j = 0; j < req.getMultiFileMap().size(); j++) {
				String umeji = "btn" + j;
				mFile = req.getMultiFileMap().get(umeji).get(0);
	            orgFileName = mFile.getOriginalFilename();//첨부 안하면 여기서 널
	            if(!fileNames[j].isEmpty()&&orgFileName.isEmpty()) {
	            	fileNameList.add(j, fileNames[j]);
	            	fileUrlList.add(j, filePaths[j]);
	            } else {
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS_");
	                Calendar calendar = Calendar.getInstance();
	                sysFileName = simpleDateFormat.format(calendar.getTime()); //sysFileName: 날짜-fileLoop번호
	                try {
	                	if(!orgFileName.isEmpty()) {
	                		mFile.transferTo(new File(rootUploadDir+sysFileName+orgFileName));
	 	                    fileUrlList.add(rootUploadDir+sysFileName+orgFileName);
	 	                    fileNameList.add(orgFileName);
	                	}else {
	                		fileUrlList.add("");
	                		fileNameList.add("");
	                	}
	                 }catch(Exception e){
	                 	e.printStackTrace();
	                 }
	            }
	        }//파일 업로드 끝
			try {//근무 등록
				int idLength = workId.length;
				for(int i=0; i< idLength; i++) {
					conductUpdateDto.setEmp_id(workId[i]);
					conductUpdateDto.setField_code(field_codes[i]);
					conductUpdateDto.setWork_year_month(date);
					conductUpdateDto.setPlan_work_days(Integer.parseInt(plan_work_days[i]));
					conductUpdateDto.setWork_days(Double.valueOf(workings[i]));
					conductUpdateDto.setPaid_vacation_days(Double.valueOf(vacations[i]));
					conductUpdateDto.setWorktime(Double.parseDouble(worktimes[i]));
					conductUpdateDto.setFile_name(fileNameList.get(i));
					conductUpdateDto.setFile_url(fileUrlList.get(i));
					conductUpdateDto.setRec_update_id(session_emp_id);
					updateService.setConductUpdate(conductUpdateDto);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try {
				if(session_flag.equals("01")) {
					modelAndView.addAllObjects(modelConductBossList(req,date));
				
					//sheet select 후 출력
					sheetList = bossService.conductSheetList(date);
				}else {
					conductWorkResultList = null;
					conductWorkResultList = workResultService.getConductWorkResultList(field_code,session_flag,session_emp_id,inqueryMonth);
					modelAndView.addObject("conductWorkList", conductWorkResultList);
					
					//sheet select 후 출력
					ConductWorkDto dto = new ConductWorkDto();
					dto.setWork_year_month(date);
					dto.setField_code(field_code);
					sheetList = bossService.leaderSheetList(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			modelAndView.addObject("sheetList",sheetList);
			
			String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
			modelAndView.addObject("successMessage", messages);
			modelAndView.setViewName("conduct/conductRegist");
//			modelAndView.setViewName("redirect:conductMain");
			modelAndView.addObject("inqueryMonth",inqueryMonth);
			return modelAndView;		        
	}
	
	@RequestMapping(value="/conductGenerator", method=RequestMethod.POST)
	public ModelAndView thisMonthConductGenerator(HttpServletRequest req,ModelAndView modelAndView){
		HttpSession session=req.getSession();
		this.pcnt = 0;
		String authCode=session.getAttribute("adminFlg").toString();
		AuthCheckUtil.authCheckAdmin(authCode);
		Date date=new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		date=cal.getTime();//new Date();
		
		int cntCDT=0;
		try {
			cntCDT=bossService.countThisMonth(date);
			if(cntCDT==0) {
				List<ConductWorkDto> list= bossService.currentEmp();
				//　勤務社員＆勤務表登録　
				for(int i=0;i<list.size();i++) {
					ConductWorkDto dto=list.get(i);
					String leader_code=bossService.getLeaderCode(dto);
					dto.setLeader_code(leader_code);
					dto.setReg_emp_id(session.getAttribute("sid").toString());
					dto.setRec_create_id(session.getAttribute("sid").toString());
					dto.setPlan_work_days(0);
					dto.setWorktime(0);
					
					ConductMonthDayDto day = DaySetting(date);
//					System.out.println("%="+ ((double)(i+1)/(double)list.size())*100 + "%" );
					bossService.conductGenerator(dto,day);
					this.pcnt = ((double)(i+1) / (double)list.size())*100;
				}
				this.sheetList = bossService.conductSheetList(date);
//				bossService.conductGenerator(session.getAttribute("sid").toString());
			}else {
				throw new MemberException("勤務表が追加に失敗しました。");
			}
		}catch(Exception e) {
			throw new MemberException(e.getMessage());
		}
		//
		
		modelAndView.addAllObjects(modelConductBossList(req,date));
		modelAndView.addObject("successMessage", "勤務表レコードが追加されました。");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		modelAndView.addObject("sheetList",this.sheetList);
		modelAndView.setViewName("conduct/conductRegist");
		modelAndView.addObject("inqueryMonth",sdf.format(date).substring(0, 7));
		return modelAndView;
	}
	
	@RequestMapping(value="/condAfterGenerator", method=RequestMethod.POST)
	public ModelAndView condAfterGenerator(HttpServletRequest req,ModelAndView modelAndView) throws ParseException{
		Date date=null;
		this.pcnt = 0;
		String inqueryMonth=req.getParameter("inqueryMonth");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(inqueryMonth==null) {
			date=new Date();
			inqueryMonth=sdf.format(date).substring(0, 7);
		}else {
			date=sdf.parse(inqueryMonth+"-01");
		}
		int updateCnt=0;

		ConductMonthDayDto day = DaySetting(date);
		try {
			List<ConductWorkDto> list= bossService.currentNewEmp(date);
			updateCnt = list.size();
			
			for(int i=0;i<list.size();i++) {
				ConductWorkDto dto=list.get(i);
				String leader_code=bossService.getLeaderCode(dto);
				dto.setLeader_code(leader_code);
				dto.setReg_emp_id(req.getSession().getAttribute("sid").toString());
				dto.setRec_create_id(req.getSession().getAttribute("sid").toString());
				dto.setPlan_work_days(0);
				dto.setWorktime(0);
				
				bossService.conductGenerator(dto,day);
				this.pcnt = ((double)(i+1) / (double)list.size())*100;
			}
			
//			updateCnt=bossService.condAfterGenerator(req.getSession().getAttribute("sid").toString(),date, day);
			this.sheetList = bossService.conductSheetList(date);
		}catch(Exception e){
				e.printStackTrace();
			throw new MemberException("更新に失敗しました。");
		}
		
		modelAndView.addObject("sheetList",this.sheetList);
		modelAndView.addObject("successMessage", updateCnt+"行のレコードが追加されました。");
		modelAndView.addAllObjects(modelConductBossList(req,date));
		modelAndView.addObject("inqueryMonth",inqueryMonth);
		modelAndView.setViewName("conduct/conductRegist");
		return modelAndView;
	}
	
	@RequestMapping(value="/conductComp", method=RequestMethod.POST)
	public ModelAndView conductCompSet(HttpSession session, HttpServletRequest req,ModelAndView modelAndView) throws ParseException, SQLException{
		String inqueryMonth=req.getParameter("inqueryMonth");
		String session_emp_id = (String)session.getAttribute("sid");
		Date date=new Date();
		boolean compflg = false;
		
		condition = new HashMap<String, String>();
		condition.put("inqueryMonth", inqueryMonth);
		condition.put("sid", session_emp_id);
		bossService.conductCompUpdate(condition);
		sheetList = bossService.conductSheetList(date);
		
		String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
		modelAndView.addObject("successMessage", messages);
		
		modelAndView.addAllObjects(modelConductBossList(req,date));
		modelAndView.addObject("sheetList",sheetList);
		modelAndView.addObject("inqueryMonth",inqueryMonth);
		modelAndView.addObject("compFlg",compflg);
		
		modelAndView.setViewName("conduct/conductRegist");
//		modelAndView.setViewName("redirect:conductMain");
		return modelAndView;
	}
	
	public ModelMap modelConductBossList(HttpServletRequest req,Date work_year_month) {
		ModelMap map=new ModelMap();
		String curPage = req.getParameter("curPage");
		if(curPage==null) {
			curPage="1";
		}
		try {
			count=bossService.countThisMonth(work_year_month);
			page = new PagingModel(count, Integer.parseInt(curPage));
			map.put("conductWorkList", bossService.getConductBossPage(page.getPageBegin(),page.getPageEnd(),work_year_month));
			map.put("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public ConductMonthDayDto DaySetting(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		ConductMonthDayDto dto = new ConductMonthDayDto();
		
		int maxDay = 0;
		String[] day = sdf.format(date).split("-");
		cal.set(Integer.parseInt(day[0]),Integer.parseInt(day[1])-1,Integer.parseInt(day[2]));
		maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		dto.setYear(day[0]);
		dto.setMonth(day[1]);
		dto.setDay(day[2]);
		dto.setMaxDay(maxDay);
		return dto;
	}
	
	public PagingModel getPage() {
		return page;
	}

	public void setPage(PagingModel page) {
		this.page = page;
	}
}


package com.ksinfo.appraisal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.dto.AppraisalMgtDto;
import com.ksinfo.appraisal.service.AppraisalScoreService;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;

@Controller
public class AppraisalScoreController {
	
	@Inject
	AppraisalScoreService appraisalScoreService;
	
	private PagingModel page;  
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/AppraisalScoreController",method = RequestMethod.GET)
	public ModelAndView apporaisalMain(ModelAndView model, HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		URLCheckUtil.urlCheck(req);
		res.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String auth_code=(String)session.getAttribute("adminFlg"); //auth_code = admin_Flg ログイン権限
		String emp_id=(String)session.getAttribute("sid");//emp_id
		
		
		List<AppraisalMgtDto> dtoList=new ArrayList<AppraisalMgtDto>();
		int listCount=0;
		Map<String,Object> paraMap=new HashMap<String, Object>();
		
		String curP=req.getParameter("curPage");
		if(curP==null) {
			curP="1";
		}
		
		if(auth_code.equals("01")) {//管理者権限
			listCount=appraisalScoreService.countAllList();
			page = new PagingModel(listCount, Integer.parseInt(curP));
			paraMap.put("start", page.getPageBegin());
			paraMap.put("end", page.getPageEnd());
			dtoList=appraisalScoreService.getAllList(paraMap);
			}else if(auth_code.equals("02")||auth_code.equals("03")) {
			paraMap.put("emp_id", emp_id);
			listCount=appraisalScoreService.countMyList(paraMap);
			page = new PagingModel(listCount, Integer.parseInt(curP));
			paraMap.put("start", page.getPageBegin());
			paraMap.put("end", page.getPageEnd());
			dtoList=appraisalScoreService.getMyList(paraMap);
			}
    	model.addObject("page", page);
		model.addObject("dtoList", dtoList);
		pIA.getURLforArray(req, "評価管理一覧","0");
		model.setViewName("/appraisal/appraisalScore");
		
		return model;
	}
}

package com.ksinfo.order.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.appraisal.service.AppraisalConfirmService;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;

@Controller
public class OrderMainController {


	@Inject AppraisalConfirmService appraisalConfirmService;
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/OrderMain",method = RequestMethod.GET)
	public ModelAndView orderMainController(HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		model.addObject("appr_list", appraisalConfirmService.getApprList());
		pIA.getURLforArray(req, "受注管理","0");
		model.setViewName("order/orderMain");
		return model;
		
	}

	@RequestMapping(value = "/clientInfo",method = RequestMethod.GET)
	public ModelAndView clientInfo(HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		model.addObject("appr_list", appraisalConfirmService.getApprList());
		pIA.getURLforArray(req, "取引先情報","0");
		model.setViewName("order/clientInfo");
		return model;
		
	}

	@RequestMapping(value = "/clientManager",method = RequestMethod.GET)
	public ModelAndView clientManager(HttpServletRequest req) {
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		model.addObject("appr_list", appraisalConfirmService.getApprList());
		pIA.getURLforArray(req, "取引先担当","0");
		model.setViewName("order/clientManager");
		return model;
		
	}

	@RequestMapping(value = "/obtainOrde",method = RequestMethod.GET)
	public ModelAndView obtainOrde(HttpServletRequest req) {
		
		System.out.println(" -->> abcdefg");
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
		model.addObject("appr_list", appraisalConfirmService.getApprList());
		pIA.getURLforArray(req, "受注管理","0");
		model.setViewName("order/obtainOrde");
		return model;
		
	}
	
	
	
	

//	@RequestMapping(value = "/OrderInsert", method = RequestMethod.POST)
//	public String orderInsert() {
//		
//		return "";
//	}
	
	
	

//	@RequestMapping(value = "/OrderMainController", method = RequestMethod.GET)
//	public ModelAndView orderMain(ModelAndView model, HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
	
//		System.out.println("ABCABADF");
//		model.setViewName("order/orderMain");
//		return model;
		
//	}
}

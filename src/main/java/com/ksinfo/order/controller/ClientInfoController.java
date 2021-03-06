package com.ksinfo.order.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.order.dto.ClientInfoDto;
import com.ksinfo.order.dto.ObtainOrdeDto;
import com.ksinfo.order.service.ClientInfoService;
import com.ksinfo.order.service.OrderService;

@Controller
public class ClientInfoController {

	@Autowired
	OrderService orderService;
	@Autowired
	ClientInfoService clientInfoService;
	
	PageIndexArr pIA = new PageIndexArr();
	
	@RequestMapping(value = "/clientInfoConfirm",method = RequestMethod.POST)
	public ModelAndView orderMainController(HttpServletRequest req) {
				
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);

		ClientInfoDto clientDto = new ClientInfoDto();
		
		ClientInfoController cc = new ClientInfoController();
		Date obtDate = cc.transformDate(req.getParameter("obtorde_cont_date"));
		Date orderDate = cc.transformDate(req.getParameter("order_cont_date"));
		
		clientDto.setComCode(req.getParameter("com_code"));
		clientDto.setBusiType(req.getParameter("busi_type"));
		clientDto.setComForm(req.getParameter("com_form"));
		clientDto.setComNationality(req.getParameter("com_nationality"));
		clientDto.setComName(req.getParameter("com_name"));
		clientDto.setComKanaName(req.getParameter("com_kana_name"));
		clientDto.setComNameEng(req.getParameter("com_name_eng"));
		clientDto.setComPosCode1(req.getParameter("com_pos_code1"));
		clientDto.setComPosCode2(req.getParameter("com_pos_code2"));
		clientDto.setComAddress1(req.getParameter("com_address1"));
		clientDto.setComAddress2(req.getParameter("com_address2"));
		clientDto.setComURL(req.getParameter("com_URL"));
		clientDto.setObtordeContDate(obtDate);	//obtorde_cont_date
		clientDto.setObtordeContNum(req.getParameter("obtorde_cont_num"));
		clientDto.setOrderContDate(orderDate);	//order_cont_date
		clientDto.setOrderContNum(req.getParameter("order_cont_num"));
		clientDto.setAccInfoBank(req.getParameter("acc_info_bank"));
		clientDto.setAccInfoBranch(req.getParameter("acc_info_branch"));
		clientDto.setAccInfoClassify(req.getParameter("acc_info_classify"));
		clientDto.setAccName(req.getParameter("acc_name"));
		
		System.out.println("??????????????? -->" + clientDto.getComCode());
		System.out.println("?????? -->" + clientDto.getBusiType());
		System.out.println("?????? -->" + clientDto.getComForm());
		System.out.println("???????????? -->" + clientDto.getComNationality());
		System.out.println("?????????????????? -->" + clientDto.getComName());
		System.out.println("?????????????????? -->" + clientDto.getComKanaName());
		System.out.println("?????????????????? -->" + clientDto.getComNameEng());
		System.out.println("????????????1 -->" + clientDto.getComPosCode1());
		System.out.println("????????????2 -->" + clientDto.getComPosCode2());
		System.out.println("????????? -->" + clientDto.getComAddress1());
		System.out.println("????????? -->" + clientDto.getComAddress2());
		System.out.println("URL -->" + clientDto.getComURL());
		System.out.println("????????????????????? -->" + clientDto.getObtordeContDate());
		System.out.println("????????????????????? -->" + clientDto.getObtordeContNum());
		System.out.println("????????????????????? -->" + clientDto.getOrderContDate());
		System.out.println("????????????????????? -->" + clientDto.getOrderContNum());
		System.out.println("?????????????????? -->" + clientDto.getAccInfoBank());
		System.out.println("?????????????????? -->" + clientDto.getAccInfoBranch());
		System.out.println("?????????????????? -->" + clientDto.getAccInfoClassify());
		System.out.println("???????????? -->" + clientDto.getAccName());
	
		clientInfoService.ClientInfo(clientDto);
		pIA.getURLforArray(req, "????????????","0");
	
		model.setViewName("order/orderMain");
	
		return model;
		
	}
	
	private Date transformDate(String String) {

		 SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;
        
        try {
            // ?????? yyyymmdd?????? ?????? ???????????? java.util.Date????????? ?????????.
            tempDate = beforeFormat.parse(String);
        } catch (ParseException e) {
            e.printStackTrace();
        }

     // java.util.Date??? yyyy-mm-dd ???????????? ???????????? String??? ????????????.
        String transDate = afterFormat.format(tempDate);
        
        // ????????? String ?????? Date??? ????????????.
        Date date = Date.valueOf(transDate);
        
		return date;
	}

	@RequestMapping(value = "/obtainOrdeConfirm",method = RequestMethod.POST)
	public ModelAndView obtainOrdeController(HttpServletRequest req) {
	
		URLCheckUtil.urlCheck(req);
		ModelAndView model=new ModelAndView();
		HttpSession session=req.getSession();
		String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);

		ObtainOrdeDto obtainDto = new ObtainOrdeDto();
		
		obtainDto.setPjectNum(req.getParameter("pject_num"));
		obtainDto.setEstimateNum(req.getParameter("estimate_num"));
		String registrationDate = req.getParameter("registration_date");
		registrationDate = registrationDate.replace("/", "");
		obtainDto.setRegistrationDate(registrationDate);
		
		obtainDto.setPersNum(Integer.parseInt(req.getParameter("pers_num")));
		obtainDto.setPersName(req.getParameter("pers_name"));
		obtainDto.setUpriceClassify(req.getParameter("uprice_classify"));
		obtainDto.setUpriceAmount(req.getParameter("uprice_amount"));
		obtainDto.setStartStandardWorkTime(Integer.parseInt(req.getParameter("start_standard_work_time")));
		obtainDto.setEndStandardWorkTime(Integer.parseInt(req.getParameter("end_standard_work_time")));
		obtainDto.setExcessUprice(req.getParameter("excess_uprice"));
		obtainDto.setDeductionUprice(req.getParameter("deduction_uprice"));
		
		System.out.println(obtainDto.getPjectNum() + "    <----");
		System.out.println(obtainDto.getEstimateNum()+" <----");
		System.out.println(obtainDto.getRegistrationDate());
		System.out.println(obtainDto.getPersNum());
		System.out.println(obtainDto.getPersName());
		System.out.println(obtainDto.getUpriceClassify());
		System.out.println(obtainDto.getUpriceAmount());
		System.out.println(obtainDto.getStartStandardWorkTime());
		System.out.println(obtainDto.getEndStandardWorkTime());
		System.out.println(obtainDto.getExcessUprice());
		System.out.println(obtainDto.getDeductionUprice());
				
		orderService.ObtainOrde(obtainDto);
		pIA.getURLforArray(req, "????????????","0");
		
		
		model.setViewName("order/orderMain");
		
		return model;
		
	}


}

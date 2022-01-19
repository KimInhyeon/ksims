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
		
		System.out.println("会社コード -->" + clientDto.getComCode());
		System.out.println("業種 -->" + clientDto.getBusiType());
		System.out.println("国籍 -->" + clientDto.getComForm());
		System.out.println("会社形態 -->" + clientDto.getComNationality());
		System.out.println("名称（漢字） -->" + clientDto.getComName());
		System.out.println("名称（カナ） -->" + clientDto.getComKanaName());
		System.out.println("名称（英字） -->" + clientDto.getComNameEng());
		System.out.println("郵便番号1 -->" + clientDto.getComPosCode1());
		System.out.println("郵便番号2 -->" + clientDto.getComPosCode2());
		System.out.println("住所１ -->" + clientDto.getComAddress1());
		System.out.println("住所２ -->" + clientDto.getComAddress2());
		System.out.println("URL -->" + clientDto.getComURL());
		System.out.println("受注契約年月日 -->" + clientDto.getObtordeContDate());
		System.out.println("受注契約書番号 -->" + clientDto.getObtordeContNum());
		System.out.println("発注契約年月日 -->" + clientDto.getOrderContDate());
		System.out.println("発注契約書番号 -->" + clientDto.getOrderContNum());
		System.out.println("口座情報銀行 -->" + clientDto.getAccInfoBank());
		System.out.println("口座情報支店 -->" + clientDto.getAccInfoBranch());
		System.out.println("口座情報区分 -->" + clientDto.getAccInfoClassify());
		System.out.println("口座名義 -->" + clientDto.getAccName());
	
		clientInfoService.ClientInfo(clientDto);
		pIA.getURLforArray(req, "受注管理","0");
	
		model.setViewName("order/orderMain");
	
		return model;
		
	}
	
	private Date transformDate(String String) {

		 SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;
        
        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(String);
        } catch (ParseException e) {
            e.printStackTrace();
        }

     // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);
        
        // 반환된 String 값을 Date로 변경한다.
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
		pIA.getURLforArray(req, "受注管理","0");
		
		
		model.setViewName("order/orderMain");
		
		return model;
		
	}


}

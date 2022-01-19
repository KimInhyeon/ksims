package com.ksinfo.salary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.EnvironmentConfig;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.salary.dto.SalarymgtDto;
import com.ksinfo.salary.exception.SalaryPayrollException;
import com.ksinfo.salary.service.SalaryPayrollViewService;
import com.ksinfo.salary.service.SalaryPayrollViewServiceImpl;
import com.ksinfo.salary.service.SalaryPayrollWriteService;
import com.ksinfo.salary.service.SalaryPayrollWriteServiceImpl;


@Controller
public class SalaryPayrollWriteController {

	private static final long serialVersionUID = 1L;
	
	PageIndexArr pIA = new PageIndexArr();
//등록
	@Autowired
	Environment env;
	@Autowired
	EnvironmentConfig environmentConfig;
	
	@Autowired
	SalaryPayrollWriteService sPWDao = new SalaryPayrollWriteServiceImpl();

	@Autowired
	SalaryPayrollViewService sPVS = new SalaryPayrollViewServiceImpl();

	private String identificationNo;
	private SalarymgtDto salaryPayroll;

	private String pension_flg;
	private String health_insurance_flg;
	private String emp_insurance_flg;
	private int overtimePaySum;
	private int dependentCount;
	
	private String year_month;
	
	
	//다음사원 확인
	@ResponseBody
	@RequestMapping(value = "/ajaxNextEmp", method = RequestMethod.POST)
	public String ajaxNextEmp(HttpServletRequest req, HttpServletResponse resp) {
		String id = null;
		
		try {
    		id = sPVS.SalaryNextEmp(this.year_month,salaryPayroll.getEmpId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	//전월 로드
	@ResponseBody
	@RequestMapping(value = "/ajaxSalaryPayrollLoad", method = RequestMethod.POST)
	public SalarymgtDto ajaxSalaryPayroll_load(HttpServletRequest req, HttpServletResponse resp) {
		
		SalarymgtDto Payroll_load = null;
		try {
			Payroll_load = sPVS.salaryPayrollPastList(this.identificationNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Payroll_load;
	}
	
	//보험료 계산
	@ResponseBody
	@RequestMapping(value = "/ajaxInsuranceReference", method = RequestMethod.POST)
	public SalarymgtDto ajaxInsuranceReference(HttpServletRequest req, HttpServletResponse resp) {

		SalarymgtDto insurance = null;
		SalarymgtDto pay = new SalarymgtDto();
		Float empRate = Float.valueOf(env.getProperty("EmpRate"));
		int income = 0;
		
		//　課税対象額
		int taxableProperty = Integer.parseInt(req.getParameter("taxableProperty").replaceAll("\\,",""));
		//　所得税
		int empInsurance = (int)(taxableProperty*empRate);
		
		if(taxableProperty != 0 ) {
			try {
				insurance = sPVS.SalaryInsuranceSet(taxableProperty);
				income = sPVS.SalaryIncomeSet(taxableProperty,this.dependentCount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//emp_tbl에서 연금flg가 체크되어있는 사원만 출력
			if(insurance != null) {
				//후생연금 flg가 체크되어있는것만 값 출력
				if(this.pension_flg.equals("t")) {
					pay.setPension(insurance.getPension());
				}else {
					pay.setPensionFlg("厚生年金");
					pay.setPension(0);
				}
				if(this.health_insurance_flg.equals("t")) {
					pay.setHealthInsurance(insurance.getHealthInsurance());
				}else {
					pay.setHealthInsuranceFlg("健康保険");
					pay.setHealthInsurance(0);
				}
			}
			if(this.emp_insurance_flg.equals("t")) {
				pay.setEmployedInsurance(empInsurance);
			}else {
				pay.setEmpInsuranceFlg("雇用保険");
				pay.setEmployedInsurance(0);
			}
			pay.setIncomeTax(income);
		}else {		//給料を入力せず計算ボタンを押下した場合。
			pay.setPension(0);
			pay.setHealthInsurance(0);
			pay.setEmployedInsurance(0);
		}

		return pay;
	}
	
	//post test
	   @RequestMapping(value = "/SalaryPayrollRegist", method = RequestMethod.POST)
	   public ModelAndView Payroll_View_doPost(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) 
			   throws ServletException, IOException, SQLException  {
		   
		   ModelAndView modelAndView = new ModelAndView();
		   
			//list에서 날짜값,id를 받은후 객체에 담기
			if(req.getParameter("identificationNo") != null) {
				this.identificationNo = req.getParameter("identificationNo");
			}
			if(req.getParameter("work_year_month") != null) {
				this.year_month = req.getParameter("work_year_month");
			}
		   
		   modelAndView.setViewName("redirect:SalaryPayrollRegist");
		   return modelAndView;
	   }

	
	
	//입력화면
    @RequestMapping(value = "/SalaryPayrollRegist", method = RequestMethod.GET)
    public ModelAndView Payroll_View_doGet(HttpServletRequest req, HttpServletResponse resp,
    		Model model, HttpSession session) throws ServletException, IOException, SQLException  {
		URLCheckUtil.urlCheck(req);
    	ModelAndView modelAndView = new ModelAndView();

    	//admin입력처리
    	String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
    	
		SalarymgtDto user = (SalarymgtDto) session.getAttribute("userPay");
		
		String[] year_month_arr = null;
		this.salaryPayroll = sPVS.salaryPayrollCdt(this.identificationNo, this.year_month);
		this.pension_flg = this.salaryPayroll.getPensionFlg();
		this.health_insurance_flg = this.salaryPayroll.getHealthInsuranceFlg();
		this.emp_insurance_flg = this.salaryPayroll.getEmpInsuranceFlg();
		this.overtimePaySum = this.salaryPayroll.getOvertimePaySum();
		this.dependentCount = this.salaryPayroll.getDependentCount();
		
		//출근일수,유급휴가,부서명,이름 등등
		year_month_arr = this.year_month.split("-");
		
		if(user == null) {
			if(sPVS.salaryPayrollList(identificationNo, year_month_arr[0], year_month_arr[1]) != null) {
				this.salaryPayroll = sPVS.salaryPayrollList(identificationNo, year_month_arr[0], year_month_arr[1]);
			}
		}else{
			this.salaryPayroll = user;
			session.removeAttribute("userPay");
		}
		
		//salary 등록된 값
		req.setAttribute("pension_flg", this.pension_flg);
		req.setAttribute("health_insurance_flg", this.health_insurance_flg);
		req.setAttribute("emp_insurance_flg", this.emp_insurance_flg);
		req.setAttribute("overtimePaySum", this.overtimePaySum);
		
		req.setAttribute("year", year_month_arr[0]);
		req.setAttribute("month", year_month_arr[1]);
		req.setAttribute("salaryPayroll", this.salaryPayroll);
		

		modelAndView.setViewName("Salary/payslip");
		pIA.getURLforArray(req, "給料明細","0");
		
		return modelAndView;
    }
    
    //등록후 리스트
    @RequestMapping(value = "/SalaryPayrollWrite", method = RequestMethod.POST)
    public ModelAndView Payroll_Search(HttpServletRequest req, Model model, HttpSession session,RedirectAttributes redirectAttributes) throws ServletException, IOException{

    	ModelAndView modelAndView = new ModelAndView();
    	//admin입력처리
    	String authCode = (String) session.getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);
    	String adm = (String)session.getAttribute("sid");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
    	Date now = new Date();
    	String cal = sdf.format(now);
    	
		//등록 후 리스트에
    	SalarymgtDto dto = new SalarymgtDto();
    	
    	String planWorkDays = req.getParameter("planWorkDays");
    	dto.setPlanWorkDays(Integer.parseInt(planWorkDays));
    	
    	String workDays = req.getParameter("workDays");
    	dto.setWorkDays(Float.valueOf(workDays));
    	
    	String paidVacationDays = req.getParameter("paidVacationDays");
    	dto.setPaidVacationDays(Float.valueOf(paidVacationDays));
    	
    	String personalReasonVacationDays = req.getParameter("personalReasonVacationDays");
    	if(personalReasonVacationDays != null && personalReasonVacationDays != "") {
    		dto.setPersonalReasonVacationDays(Integer.parseInt(personalReasonVacationDays));
    	}
    	
    	String absenteeismDays = req.getParameter("absenteeismDays");
    	if(absenteeismDays != "") {
    		dto.setAbsenteeismDays(Integer.parseInt(absenteeismDays));
    	}
    	
    	String tardinessCount = req.getParameter("tardinessCount");
    	if(tardinessCount  != "") {
    		dto.setTardinessCount(Integer.parseInt(tardinessCount));
    	}
    	
    	String leaveEarlyCount = req.getParameter("leaveEarlyCount");
    	if(leaveEarlyCount  != "") {
    		dto.setLeaveEarlyCount(Integer.parseInt(leaveEarlyCount));
    	}

    	String overtime = req.getParameter("overtime");
    	dto.setOvertime(Float.valueOf(overtime));
    	
    	String salary = req.getParameter("salary").replace(",", "");
    	dto.setSalary(Integer.parseInt(salary));
    	
    	String basePay = req.getParameter("basePay").replace(",","");
    	dto.setBasePay(Integer.parseInt(basePay));
    	
    	String positionPay = req.getParameter("positionPay").replace(",", "");
    	dto.setPositionPay(Integer.parseInt(positionPay));

    	String certificationPay = req.getParameter("certificationPay").replace(",", "");
    	dto.setCertificationPay(Integer.parseInt(certificationPay));
    	
    	String housingAllowance = req.getParameter("housingAllowance").replace(",", "");
    	dto.setHousingAllowance(Integer.parseInt(housingAllowance));
    	
//    	String overtimePay = req.getParameter("overtimePay").replace(",", "");
//		dto.setOvertimePay(Integer.parseInt(overtimePay));

    	String transportationPay = req.getParameter("transportationPay").replace(",", "");
    	dto.setTransportationPay(Integer.parseInt(transportationPay));
    	
    	String endYearPay = req.getParameter("endYearPay").replace(",", "");
    	dto.setEndYearPay(Integer.parseInt(endYearPay));
    	
    	String fixedOvertimePay = req.getParameter("fixedOvertimePay").replace(",", "");
    	dto.setFixedOvertimePay(Integer.parseInt(fixedOvertimePay));
    	
    	String extraCost = req.getParameter("extraCost").replace(",", "");
    	dto.setExtraCost(Integer.parseInt(extraCost));
    	
    	String incentive = req.getParameter("incentive").replace(",", "");
    	dto.setIncentive(Integer.parseInt(incentive));
    	
    	String extraPay = req.getParameter("extraPay").replace(",", "");
    	dto.setExtraPay(Integer.parseInt(extraPay));
    	
    	String absentDeductied = req.getParameter("absentDeductied").replace(",", "");
    	dto.setAbsentDeductied(Integer.parseInt(absentDeductied));
    	
    	String totalSalary = req.getParameter("totalSalary").replace(",", "");
    	dto.setTotalSalary(Integer.parseInt(totalSalary));

    	String taxableProperty = req.getParameter("taxableProperty").replace(",", "");
    	dto.setTaxableProperty(Integer.parseInt(taxableProperty));
    	
    	String caringInsurance = req.getParameter("caringInsurance").replace(",", "");
    	dto.setCaringInsurance(Integer.parseInt(caringInsurance));
    	
    	String healthInsurance = req.getParameter("healthInsurance").replace(",", "");
    	dto.setHealthInsurance(Integer.parseInt(healthInsurance));

    	String pension = req.getParameter("pension").replace(",", "");
    	dto.setPension(Integer.parseInt(pension));
    	
    	String employedInsurance = req.getParameter("employedInsurance").replace(",", "");
    	dto.setEmployedInsurance(Integer.parseInt(employedInsurance));
    	
    	String companyInsurance = req.getParameter("companyInsurance").replace(",", "");
    	dto.setCompanyInsurance(Integer.parseInt(companyInsurance));
    	
    	String incomeTax = req.getParameter("incomeTax").replace(",", "");
    	dto.setIncomeTax(Integer.parseInt(incomeTax));
    	
    	String citizenTax = req.getParameter("citizenTax").replace(",", "");
    	dto.setCitizenTax(Integer.parseInt(citizenTax));
    	
    	String dormDeduction = req.getParameter("dormDeduction").replace(",", "");
    	dto.setDormDeduction(Integer.parseInt(dormDeduction));
    	
    	String lifeInsurance = req.getParameter("lifeInsurance").replace(",", "");
    	dto.setLifeInsurance(Integer.parseInt(lifeInsurance));
    	
    	String accumulationMoney = req.getParameter("accumulationMoney").replace(",", "");
    	dto.setAccumulationMoney(Integer.parseInt(accumulationMoney));
    	
    	String debtFinished = req.getParameter("debtFinished").replace(",", "");
    	dto.setDebtFinished(Integer.parseInt(debtFinished));

    	String totalDeduction = req.getParameter("totalDeduction").replace(",", "");
    	dto.setTotalDeduction(Integer.parseInt(totalDeduction));
    	
		req.setAttribute("salaryPayroll", this.salaryPayroll);
    	dto.setCondcutIdx(salaryPayroll.getConductIdx());
    	
    	dto.setDepartmentName(salaryPayroll.getDepartmentName());
    	
    	dto.setYear(year_month.substring(0,4));
    	dto.setMonth(year_month.substring(5,7));
    	dto.setEmpId(salaryPayroll.getEmpId());
    	
    	dto.setEmpName(salaryPayroll.getEmpName());
    	dto.setPaymentYear(cal.substring(0,4));
    	dto.setPaymentMonth(cal.substring(5,7));
    	dto.setRec_id(adm);
    	
    	//時間外手当がxx.xの場合
    	try {
    		String overtimePay = req.getParameter("overtimePay").replace(",", "");
    		dto.setOvertimePay(Integer.parseInt(overtimePay));
    		
    	}catch (NumberFormatException e) {
			// TODO: handle exception
    		session.setAttribute("userPay", dto);
    		String messages = MessageUtils.getMessage("KS_IMSYS_PAYERR_001_JV");
    		throw new SalaryPayrollException(messages);
		}
    	
    	//수정 || 등록
    	SalarymgtDto salaryList = null;
		try {
			salaryList = sPVS.salaryPayrollList(this.salaryPayroll.getEmpId(), year_month.substring(0,4), year_month.substring(5,7));
			if(salaryList == null) {
	    		sPWDao.salaryPayrollInsert(dto);
	    	}else {
	    		sPWDao.salaryPayrollUpdate(dto);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	String messages = "t";
    	modelAndView.addObject("message", messages);
    	
		//modelAndView.setViewName("Salary/payslip");
		modelAndView.setViewName("redirect:SalaryPayrollRegist");
    	
    	return modelAndView;
    }
    
    //등록후 다음페이지
    @RequestMapping(value = "/SalaryPayrollNext", method = RequestMethod.POST)
    public ModelAndView PayrollNextEmp(HttpServletRequest req, Model model, RedirectAttributes rttr) throws ServletException, IOException, SQLException  {
    	ModelAndView modelAndView = new ModelAndView();

    	HttpSession session = req.getSession();
    	String adm = (String)session.getAttribute("sid");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
    	Date now = new Date();
    	String cal = sdf.format(now);
    	
    	SalarymgtDto dto = new SalarymgtDto();
    	
    	String planWorkDays = req.getParameter("planWorkDays");
    	dto.setPlanWorkDays(Integer.parseInt(planWorkDays));
    	
    	String workDays = req.getParameter("workDays");
    	dto.setWorkDays(Float.valueOf(workDays));
    	
    	String paidVacationDays = req.getParameter("paidVacationDays");
    	dto.setPaidVacationDays(Float.valueOf(paidVacationDays));
    	
    	String personalReasonVacationDays = req.getParameter("personalReasonVacationDays");
    	if(personalReasonVacationDays != null && personalReasonVacationDays != "") {
    		dto.setPersonalReasonVacationDays(Integer.parseInt(personalReasonVacationDays));
    	}
    	
    	String absenteeismDays = req.getParameter("absenteeismDays");
    	if(absenteeismDays != "") {
    		dto.setAbsenteeismDays(Integer.parseInt(absenteeismDays));
    	}
    	
    	String tardinessCount = req.getParameter("tardinessCount");
    	if(tardinessCount != "") {
    		dto.setTardinessCount(Integer.parseInt(tardinessCount));
    	}
    	
    	String leaveEarlyCount = req.getParameter("leaveEarlyCount");
    	if(leaveEarlyCount != "") {
    		dto.setLeaveEarlyCount(Integer.parseInt(leaveEarlyCount));
    	}

    	String overtime = req.getParameter("overtime");
    	dto.setOvertime(Float.valueOf(overtime));
    	
    	String salary = req.getParameter("salary").replace(",", "");
    	dto.setSalary(Integer.parseInt(salary));
    	
    	String basePay = req.getParameter("basePay").replace(",","");
    	dto.setBasePay(Integer.parseInt(basePay));
    	
    	String positionPay = req.getParameter("positionPay").replace(",", "");
    	dto.setPositionPay(Integer.parseInt(positionPay));

    	String certificationPay = req.getParameter("certificationPay").replace(",", "");
    	dto.setCertificationPay(Integer.parseInt(certificationPay));
    	
    	String housingAllowance = req.getParameter("housingAllowance").replace(",", "");
    	dto.setHousingAllowance(Integer.parseInt(housingAllowance));
    	
    	String transportationPay = req.getParameter("transportationPay").replace(",", "");
    	dto.setTransportationPay(Integer.parseInt(transportationPay));
    	
    	String endYearPay = req.getParameter("endYearPay").replace(",", "");
    	dto.setEndYearPay(Integer.parseInt(endYearPay));
    	
    	String fixedOvertimePay = req.getParameter("fixedOvertimePay").replace(",", "");
    	dto.setFixedOvertimePay(Integer.parseInt(fixedOvertimePay));
    	
    	String extraCost = req.getParameter("extraCost").replace(",", "");
    	dto.setExtraCost(Integer.parseInt(extraCost));
    	
    	String incentive = req.getParameter("incentive").replace(",", "");
    	dto.setIncentive(Integer.parseInt(incentive));
    	
    	String extraPay = req.getParameter("extraPay").replace(",", "");
    	dto.setExtraPay(Integer.parseInt(extraPay));
    	
    	String absentDeductied = req.getParameter("absentDeductied").replace(",", "");
    	dto.setAbsentDeductied(Integer.parseInt(absentDeductied));
    	
    	String totalSalary = req.getParameter("totalSalary").replace(",", "");
    	dto.setTotalSalary(Integer.parseInt(totalSalary));

    	String taxableProperty = req.getParameter("taxableProperty").replace(",", "");
    	dto.setTaxableProperty(Integer.parseInt(taxableProperty));
    	
    	String caringInsurance = req.getParameter("caringInsurance").replace(",", "");
    	dto.setCaringInsurance(Integer.parseInt(caringInsurance));
    	
    	String healthInsurance = req.getParameter("healthInsurance").replace(",", "");
    	dto.setHealthInsurance(Integer.parseInt(healthInsurance));

    	String pension = req.getParameter("pension").replace(",", "");
    	dto.setPension(Integer.parseInt(pension));
    	
    	String employedInsurance = req.getParameter("employedInsurance").replace(",", "");
    	dto.setEmployedInsurance(Integer.parseInt(employedInsurance));
    	
    	String companyInsurance = req.getParameter("companyInsurance").replace(",", "");
    	dto.setCompanyInsurance(Integer.parseInt(companyInsurance));
    	
    	String incomeTax = req.getParameter("incomeTax").replace(",", "");
    	dto.setIncomeTax(Integer.parseInt(incomeTax));
    	
    	String citizenTax = req.getParameter("citizenTax").replace(",", "");
    	dto.setCitizenTax(Integer.parseInt(citizenTax));
    	
    	String dormDeduction = req.getParameter("dormDeduction").replace(",", "");
    	dto.setDormDeduction(Integer.parseInt(dormDeduction));
    	
    	String lifeInsurance = req.getParameter("lifeInsurance").replace(",", "");
    	dto.setLifeInsurance(Integer.parseInt(lifeInsurance));
    	
    	String accumulationMoney = req.getParameter("accumulationMoney").replace(",", "");
    	dto.setAccumulationMoney(Integer.parseInt(accumulationMoney));
    	
    	String debtFinished = req.getParameter("debtFinished").replace(",", "");
    	dto.setDebtFinished(Integer.parseInt(debtFinished));

    	String totalDeduction = req.getParameter("totalDeduction").replace(",", "");
    	dto.setTotalDeduction(Integer.parseInt(totalDeduction));
    	
    	dto.setCondcutIdx(this.salaryPayroll.getConductIdx());
    	dto.setDepartmentName(this.salaryPayroll.getDepartmentName());
    	dto.setYear(year_month.substring(0,4));
    	dto.setMonth(year_month.substring(5,7));
    	dto.setEmpId(this.salaryPayroll.getEmpId());
    	
    	dto.setEmpName(this.salaryPayroll.getEmpName());
    	dto.setPaymentYear(cal.substring(0,4));
    	dto.setPaymentMonth(cal.substring(5,7));
    	dto.setRec_id(adm);
    	
    	//時間外手当がxx.xの場合
    	try {
    		String overtimePay = req.getParameter("overtimePay").replace(",", "");
    		dto.setOvertimePay(Integer.parseInt(overtimePay));
    	}catch (NumberFormatException e) {
			// TODO: handle exception
    		session.setAttribute("userPay", dto);
    		String messages = MessageUtils.getMessage("KS_IMSYS_PAYERR_001_JV");
    		throw new SalaryPayrollException(messages);
		}
//    	String overtimePay = req.getParameter("overtimePay").replace(",", "");
//    	dto.setOvertimePay(Integer.parseInt(overtimePay));
    	
    	//수정 || 등록
    	SalarymgtDto check = sPVS.salaryPayrollList(this.salaryPayroll.getEmpId(), year_month.substring(0,4), year_month.substring(5,7));
    	
    	//2021.07.28 登録なしで次のページに移動するように修正
    	//if(check == null) {
    	//	sPWDao.salaryPayrollInsert(dto);
    	//}else {
    	//	sPWDao.salaryPayrollUpdate(dto);
    	//}
    	
    	this.identificationNo = sPVS.SalaryNextEmp(this.year_month,this.salaryPayroll.getEmpId());
    	
		modelAndView.setViewName("redirect:SalaryPayrollRegist");
		
    	return modelAndView;
    }
	
}

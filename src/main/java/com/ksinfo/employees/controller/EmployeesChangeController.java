package com.ksinfo.employees.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.ObjectUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.RootPathUtil;
import com.ksinfo.common.util.StringChangedUtil;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.conduct.service.ConductDownLoadService;
import com.ksinfo.conduct.service.ConductUpdateSerivce;
import com.ksinfo.employees.dto.DepartmentMasterDto;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.dto.EmpFileDto;
import com.ksinfo.employees.dto.PositionMasterDto;
import com.ksinfo.employees.service.EmployeesChangeService;
import com.ksinfo.employees.service.EmployeesCommonService;
import com.ksinfo.employees.service.EmployeesFileService;
import com.ksinfo.employees.util.PhotoUploadUtil;


@Controller
public class EmployeesChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	ConductUpdateSerivce updateService;
	@Autowired
	ConductDownLoadService downService;
	@Autowired
	EmployeesChangeService cngService;
	@Autowired
	EmployeesCommonService cmnService;	
	@Autowired
	EmployeesFileService fileService;
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/EmployeesChangeController", method = RequestMethod.GET)
	public ModelAndView empChangeManageForm(HttpServletRequest req , HttpServletResponse res) throws  SQLException{
		URLCheckUtil.urlCheck(req);
		
		ModelAndView modelAndView = new ModelAndView();
		PageIndexArr pIA = new PageIndexArr(); 
		
		int empIdx= Integer.parseInt(req.getParameter("empIdx"));
		String auth = (String)req.getSession().getAttribute("adminFlg");
		String displayGender = "";
		
		EmpDto empManage = null;
		List<PositionMasterDto> pstDropBox = null;
		List<DepartmentMasterDto> dpDropBox = null;
    	List<EmpFileDto> fileList = new ArrayList<EmpFileDto>();

		empManage= cngService.employeesChange(empIdx);
		
		if(ObjectUtil.isEmpty(empManage.getEmpFile())) {
			empManage.setEmpFile("default_photo.png");
		}
		
		pstDropBox = cmnService.positionDropBox();
		dpDropBox = cmnService.departDropBox();
    	fileList = fileService.getFileList(empManage.getEmpId());
    	
    	if (fileList.size() > 0) {
    		fileList.get(0).setEmpIdx(empIdx);	
    	}
    			
		if (empManage.getEmpGender().equals("M")) {
			displayGender = "男性";
		} else if (empManage.getEmpGender().equals("F")) {
			displayGender = "女性";
		} else if (empManage.getEmpGender().equals("P")) {
			displayGender = "協力会社";
		}
		
		pIA.getURLforArray(req, "社員変更","0");
		modelAndView.addObject("displayGender", displayGender);
		modelAndView.addObject("empManage", empManage);
		modelAndView.addObject("pstDropBox", pstDropBox);
		modelAndView.addObject("selectedPositionCode", empManage.getPositionCode());
		modelAndView.addObject("dpDropBox", dpDropBox);
		modelAndView.addObject("selectedDepartCode", empManage.getDepartmentCode());
		modelAndView.addObject("selectedEmpPayLevel", empManage.getEmpPayLevel());
		modelAndView.addObject("selectedDependentCount", empManage.getDependentCount());
		modelAndView.addObject("selectedEmpRelation", empManage.getEmpRelation());
		modelAndView.addObject("selectedHistoryNo", empManage.getHistoryNo());
		modelAndView.addObject("fileList", fileList);
		
		if(auth.equals("01")) {
			modelAndView.setViewName("/employees/employeesChangeManageForm");	
		} else if(auth.equals("02") || auth.equals("03")) {
			modelAndView.setViewName("/employees/employeesChangeEmployeeForm");			
		}
		
        return modelAndView;
	}
	
	@RequestMapping(value = "/EmployeesHistoryController", method = RequestMethod.GET)
	public ModelAndView empChangeManageForm(HttpServletRequest req , HttpServletResponse res, @RequestParam("historyNo") int historyNo) throws  SQLException{
		URLCheckUtil.urlCheck(req);
		
		ModelAndView modelAndView = new ModelAndView();
		PageIndexArr pIA = new PageIndexArr(); 
		
		int empIdx= Integer.parseInt(req.getParameter("empIdx"));
		String auth = (String)req.getSession().getAttribute("adminFlg");
		String displayGender = "";
		
		EmpDto empManageAfter = null;
		EmpDto empManageBefore = null;
		List<PositionMasterDto> pstDropBox = null;
		List<DepartmentMasterDto> dpDropBox = null;
    	List<EmpFileDto> fileList = new ArrayList<EmpFileDto>();
    	
    	Map<String, Integer> paramMap = new HashMap<String, Integer>();
    	paramMap.put("empIdx", empIdx);
    	paramMap.put("historyNo", historyNo);

    	// history after
		empManageAfter= cngService.employeesHistory(paramMap);
		
    	// history before
    	paramMap.put("historyNo", historyNo - 1);
    	empManageBefore= cngService.employeesHistory(paramMap);
		
		if(ObjectUtil.isEmpty(empManageAfter.getEmpFile())) {
			empManageAfter.setEmpFile("default_photo.png");
		}
		
		pstDropBox = cmnService.positionDropBox();
		dpDropBox = cmnService.departDropBox();
    	
    	if (fileList.size() > 0) {
    		fileList.get(0).setEmpIdx(empIdx);	
    	}
    			
		if (empManageAfter.getEmpGender().equals("M")) {
			displayGender = "男性";
		} else if (empManageAfter.getEmpGender().equals("F")) {
			displayGender = "女性";
		} else if (empManageAfter.getEmpGender().equals("P")) {
			displayGender = "協力会社";
		}
		
		if (!ObjectUtil.isEmpty(empManageBefore)) {
			// 社員写真
			if (StringChangedUtil.compare(empManageBefore.getEmpUrl(), empManageAfter.getEmpUrl())) {
				modelAndView.addObject("cgEmpFile", "true");
			}		
			// 郵便番号
			if (StringChangedUtil.compare(empManageBefore.getEmpPostNo(), empManageAfter.getEmpPostNo())) {
				modelAndView.addObject("cgEmpPostNo", "true");
			}
			// 住所
			if (StringChangedUtil.compare(empManageBefore.getEmpAddr(), empManageAfter.getEmpAddr())) {
				modelAndView.addObject("cgEmpAddr", "true");
			}
			// 電話番号	
			if (StringChangedUtil.compare(empManageBefore.getPhoneNumber(), empManageAfter.getPhoneNumber())) {
				modelAndView.addObject("cgPhoneNumber", "true");
			}
			// 緊急連絡先（母国）
			if (StringChangedUtil.compare(empManageBefore.getEmergencyPhoneNumber(), empManageAfter.getEmergencyPhoneNumber())) {
				modelAndView.addObject("cgEmergencyPhoneNumber", "true");
			}
			// 緊急連絡先（母国）
			if (StringChangedUtil.compare(empManageBefore.getEmpRelation(), empManageAfter.getEmpRelation())) {
				modelAndView.addObject("cgEmergencyPhoneNumber", "true");
			}
			// 職位
			if (StringChangedUtil.compare(empManageBefore.getPositionCode(), empManageAfter.getPositionCode())) {
				modelAndView.addObject("cgPositionCode", "true");
			}
			// 号俸
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getEmpPayLevel()), String.valueOf(empManageAfter.getEmpPayLevel()))) {
				modelAndView.addObject("cgEmpPayLevel", "true");
			}
			// 部署
			if (StringChangedUtil.compare(empManageBefore.getDepartmentCode(), empManageAfter.getDepartmentCode())) {
				modelAndView.addObject("cgDepartmentCode", "true");
			}
			// 課	
			if (StringChangedUtil.compare(empManageBefore.getEmpSectionName(), empManageAfter.getEmpSectionName())) {
				modelAndView.addObject("cgEmpSectionName", "true");
			}
			// 会社メール	
			if (StringChangedUtil.compare(empManageBefore.getCompanyMail(), empManageAfter.getCompanyMail())) {
				modelAndView.addObject("cgCompanyMail", "true");
			}
			// 配偶者有無
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.isEmpSpouseFlg()), String.valueOf(empManageAfter.isEmpSpouseFlg()))) {
				modelAndView.addObject("cgEmpSpouseFlg", "true");
			}
			// 扶養家族数
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getDependentCount()), String.valueOf(empManageAfter.getDependentCount()))) {
				modelAndView.addObject("cgDependentCount", "true");
			}
			// 銀行
			if (StringChangedUtil.compare(empManageBefore.getBankName(), empManageAfter.getBankName())) {
				modelAndView.addObject("cgBankName", "true");
				modelAndView.addObject("cgAccountInfo", "true");
			}
			// 支店
			if (StringChangedUtil.compare(empManageBefore.getBankBran(), empManageAfter.getBankBran())) {
				modelAndView.addObject("cgBankBran", "true");
				modelAndView.addObject("cgAccountInfo", "true");
			}
			// 区分
			if (StringChangedUtil.compare(empManageBefore.getBankNumGubun(), empManageAfter.getBankNumGubun())) {
				modelAndView.addObject("cgBankNumGubun", "true");
				modelAndView.addObject("cgAccountInfo", "true");
			}
			// 口座番号
			if (StringChangedUtil.compare(empManageBefore.getBankBookNumber(), empManageAfter.getBankBookNumber())) {
				modelAndView.addObject("cgBankBookNumber", "true");
				modelAndView.addObject("cgAccountInfo", "true");
			}
			// 在留カード番号
			if (StringChangedUtil.compare(empManageBefore.getStayExpirationNumber(), empManageAfter.getStayExpirationNumber())) {
				modelAndView.addObject("cgStayExpirationNumber", "true");
			}
			// 在留期限
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getStayExpirationDate()), String.valueOf(empManageAfter.getStayExpirationDate()))) {
				modelAndView.addObject("cgStayExpirationDate", "true");
			}
			// マイナンバー
			if (StringChangedUtil.compare(empManageBefore.getMyNumber(), empManageAfter.getMyNumber())) {
				modelAndView.addObject("cgMyNumber", "true");
			}
			// 年金番号
			if (StringChangedUtil.compare(empManageBefore.getPensionNumber(), empManageAfter.getPensionNumber())) {
				modelAndView.addObject("cgPensionNumber", "true");
			}
			// 国籍
			if (StringChangedUtil.compare(empManageBefore.getNationality(), empManageAfter.getNationality())) {
				modelAndView.addObject("cgNationality", "true");
			}
			// 住民登録番号
			if (StringChangedUtil.compare(empManageBefore.getResidentRegistrationNumber(), empManageAfter.getResidentRegistrationNumber())) {
				modelAndView.addObject("cgResidentRegistrationNumber", "true");
			}
			// 社員寮入居日
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getDormInDate()), String.valueOf(empManageAfter.getDormInDate()))) {
				modelAndView.addObject("cgDormInDate", "true");
			}
			// 社員寮退去日
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getDormOutDate()), String.valueOf(empManageAfter.getDormOutDate()))) {
				modelAndView.addObject("cgDormOutDate", "true");
			}
			// 健康保険社納
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.isHealthInsuranceFlg()), String.valueOf(empManageAfter.isHealthInsuranceFlg()))) {
				modelAndView.addObject("cgHealthInsuranceFlg", "true");
			}
			// 厚生年金社納
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.isPensionFlg()), String.valueOf(empManageAfter.isPensionFlg()))) {
				modelAndView.addObject("cgPensionFlg", "true");
			}
			// 雇用保険社納		
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.isEmpInsuranceFlg()), String.valueOf(empManageAfter.isEmpInsuranceFlg()))) {
				modelAndView.addObject("cgEmpInsuranceFlg", "true");
			}
			// 退社区分
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.isResignFlg()), String.valueOf(empManageAfter.isResignFlg()))) {
				modelAndView.addObject("cgResignFlg", "true");
			}
			// 退社日
			if (StringChangedUtil.compare(String.valueOf(empManageBefore.getResignDate()), String.valueOf(empManageAfter.getResignDate()))) {
				modelAndView.addObject("cgResignDate", "true");
			}
		}

		
		pIA.getURLforArray(req, "社員履歴","0");
		modelAndView.addObject("displayGender", displayGender);
		modelAndView.addObject("empManage", empManageAfter);
		modelAndView.addObject("pstDropBox", pstDropBox);
		modelAndView.addObject("selectedPositionCode", empManageAfter.getPositionCode());
		modelAndView.addObject("dpDropBox", dpDropBox);
		modelAndView.addObject("selectedDepartCode", empManageAfter.getDepartmentCode());
		modelAndView.addObject("selectedEmpPayLevel", empManageAfter.getEmpPayLevel());
		modelAndView.addObject("selectedDependentCount", empManageAfter.getDependentCount());
		modelAndView.addObject("selectedEmpRelation", empManageAfter.getEmpRelation());
		modelAndView.addObject("selectedHistoryNo", empManageAfter.getHistoryNo());
		
		if(auth.equals("01")) {
			modelAndView.setViewName("/employees/employeesChangeHistoryForm");	
		}
		
        return modelAndView;
	}
	
	@RequestMapping(value = "/EmployeesChangeManage", method = RequestMethod.POST)
	public ModelAndView empChangeManage(HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest request) throws  IOException, SQLException{
		
		//Data型置換
		java.util.Date stayExpirationDate = null;
		java.util.Date dormInDate = null;
		java.util.Date dormOutDate = null;
		java.util.Date resignDate = null;
		
		String strStayExpirationDate = req.getParameter("stayExpirationDate");
		String strDormInDate = req.getParameter("dormInDate");
		String strDormOutDate = req.getParameter("dormOutDate");
		String strResignDate = req.getParameter("resignDate");
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			if (!ObjectUtil.isEmpty(strStayExpirationDate)) {
				stayExpirationDate = format.parse(strStayExpirationDate);	
			}

			if (!ObjectUtil.isEmpty(strDormInDate)) {
				dormInDate = format.parse(strDormInDate);
			}

			if (!ObjectUtil.isEmpty(strDormOutDate)) {
				dormOutDate = format.parse(strDormOutDate);	
			}
			
			if (!ObjectUtil.isEmpty(strResignDate)) {
				resignDate = format.parse(strResignDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//社員情報取得
		int empIdx= Integer.parseInt(req.getParameter("empIdx"));
		HttpSession session = request.getSession();
		String empId = (String) session.getAttribute("sid");
		String empPostNo = req.getParameter("empPostNo");
		String empAddr = req.getParameter("empAddr");
		String phoneNumber = req.getParameter("phoneNumber");
		String emergencyPhoneNumber = req.getParameter("emergencyPhoneNumber");
		String empRelation = req.getParameter("empRelationDd");
		String positionCode = req.getParameter("positionCode");
		String empPayLevel = req.getParameter("empPayLevel");
		String departmentCode = req.getParameter("departmentCode");
		String empSectionName = req.getParameter("empSectionName");
		String companyMail = req.getParameter("companyMail");
		Boolean empSpouseFlg = Boolean.valueOf(req.getParameter("empSpouseFlg"));
		int dependentCount = Integer.parseInt(req.getParameter("dependentCount"));
		String bankBookNumber = req.getParameter("bankBookNumber");
		String bankName = req.getParameter("bankName");
		String bankBran = req.getParameter("bankBran");
		String bankNumGubun = req.getParameter("bankNumGubun");
		String stayExpirationNumber = req.getParameter("stayExpirationNumber");
		String myNumber = req.getParameter("myNumber");
		String pensionNumber = req.getParameter("pensionNumber");
		String residentRegistrationNumber = req.getParameter("residentRegistrationNumber");
		Boolean healthInsuranceFlg = Boolean.valueOf(req.getParameter("healthInsuranceFlg")); 
		Boolean pensionFlg = Boolean.valueOf(req.getParameter("pensionFlg")); 
		Boolean empInsuranceFlg = Boolean.valueOf(req.getParameter("empInsuranceFlg")); 
		Boolean resignFlg = Boolean.valueOf(req.getParameter("resignFlg"));
		long historyNo = Long.parseLong(req.getParameter("historyNo"));

		// 社員情報取得（追加-20210805）

		EmpDto empDto = new EmpDto();
		// Photo Upload Start
		HashMap<String, String> map = PhotoUploadUtil.uploadPhoto(request);
		
		if (!ObjectUtil.isEmpty(map.get("fileName"))) {
			// 社員情報取得（追加-20210805）
			empDto.setEmpFile(map.get("fileName"));
			empDto.setEmpUrl(map.get("fileUrl"));
		} else {
			empDto.setEmpFile(req.getParameter("fileName"));
			empDto.setEmpUrl(req.getParameter("fileUrl"));
		}
		
			empDto.setEmpIdx(empIdx);
			empDto.setEmpId(empId);
			empDto.setEmpPostNo(empPostNo);
			empDto.setEmpAddr(empAddr);
			empDto.setPhoneNumber(phoneNumber);
			empDto.setEmergencyPhoneNumber(emergencyPhoneNumber);
			empDto.setEmpRelation(empRelation);
			empDto.setPositionCode(positionCode);
			empDto.setEmpPayLevel(Integer.parseInt(empPayLevel));
			empDto.setDepartmentCode(departmentCode);
			empDto.setEmpSectionName(empSectionName);
			empDto.setCompanyMail(companyMail);
			empDto.setEmpSpouseFlg(empSpouseFlg);			
			empDto.setDependentCount(dependentCount);
			empDto.setBankBookNumber(bankBookNumber);
			empDto.setBankName(bankName);
			empDto.setBankBran(bankBran);
			empDto.setBankNumGubun(bankNumGubun);
			empDto.setStayExpirationNumber(stayExpirationNumber);
			empDto.setMyNumber(myNumber);
			empDto.setPensionNumber(pensionNumber);
			empDto.setResidentRegistrationNumber(residentRegistrationNumber);
			empDto.setStayExpirationDate(stayExpirationDate);
			empDto.setDormInDate(dormInDate);
			empDto.setDormOutDate(dormOutDate);
			empDto.setHealthInsuranceFlg(healthInsuranceFlg);
			empDto.setPensionFlg(pensionFlg);
			empDto.setEmpInsuranceFlg(empInsuranceFlg);
			empDto.setResignFlg(resignFlg);
			empDto.setResignDate(resignDate);
			empDto.setHistoryNo(historyNo);
			
		cngService.preEmployeeUpdate(empDto);			
		cngService.deleteEmp(empDto);
		cngService.empManageUpdate(empDto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/EmployeesChangeController?empIdx=" + empIdx);
        return modelAndView;
	}
	
	@RequestMapping(value = "/EmployeesChangeEmp", method = RequestMethod.POST)
	public ModelAndView empChangeEmp(HttpServletRequest req, HttpServletResponse res, MultipartHttpServletRequest request) throws  IOException, SQLException{
		//Data型置換
		java.util.Date stayExpirationDate = null;
		
		String strStayExpirationDate = req.getParameter("stayExpirationDate");
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			if (!ObjectUtil.isEmpty(strStayExpirationDate)) {
				stayExpirationDate = format.parse(strStayExpirationDate);	
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//社員情報取得
		int empIdx = Integer.parseInt(req.getParameter("empIdx"));
		String empAddr = req.getParameter("empAddr");
		String phoneNumber = req.getParameter("phoneNumber");
		HttpSession session = request.getSession();
		String empId = (String) session.getAttribute("sid");
		String nationality = req.getParameter("nationality");
		String stayExpirationNumber = req.getParameter("stayExpirationNumber");
		String empName = req.getParameter("empName");
		String empNameKana = req.getParameter("empNameKana");
		String empNameEng = req.getParameter("empNameEng");
		String companyMail = req.getParameter("companyMail");
		Boolean empSpouseFlg = Boolean.valueOf(req.getParameter("empSpouseFlg")); 
		int dependentCount = Integer.parseInt(req.getParameter("dependentCount"));
		String departmentCode = req.getParameter("departmentCode");
		String emergencyPhoneNumber = req.getParameter("emergencyPhoneNumber");
		String empPostNo = req.getParameter("empPostNo");
		String empSectionName = req.getParameter("empSectionName");
		String empPayLevel = req.getParameter("payLevel");
		String empRelation = req.getParameter("empRelationDd");
		long historyNo = Long.parseLong(req.getParameter("historyNo"));
		// 社員情報取得（追加-20210805）

		EmpDto empDto = new EmpDto();
		// Photo Upload Start
		HashMap<String, String> map = PhotoUploadUtil.uploadPhoto(request);
		
		if (!ObjectUtil.isEmpty(map.get("fileName"))) {
			// 社員情報取得（追加-20210805）
			empDto.setEmpFile(map.get("fileName"));
			empDto.setEmpUrl(map.get("fileUrl"));
		} else {
			empDto.setEmpFile(req.getParameter("fileName"));
			empDto.setEmpUrl(req.getParameter("fileUrl"));
		}

		    empDto.setEmpIdx(empIdx);
			empDto.setEmpAddr(empAddr);
			empDto.setPhoneNumber(phoneNumber);
			empDto.setEmpId(empId);
			empDto.setNationality(nationality);
			empDto.setStayExpirationDate(stayExpirationDate);
			empDto.setStayExpirationNumber(stayExpirationNumber);
			empDto.setEmpName(empName);
			empDto.setEmpNameKana(empNameKana);
			empDto.setEmpNameEng(empNameEng);
			empDto.setCompanyMail(companyMail);
			empDto.setEmpSpouseFlg(empSpouseFlg);
			empDto.setDependentCount(dependentCount);
			empDto.setDepartmentCode(departmentCode);
			empDto.setEmergencyPhoneNumber(emergencyPhoneNumber);
			empDto.setEmpPostNo(empPostNo);
			empDto.setEmpSectionName(empSectionName);
			empDto.setEmpPayLevel(Integer.parseInt( empPayLevel));
			empDto.setEmpRelation(empRelation);
			empDto.setHistoryNo(historyNo);
		
		cngService.preEmployeeUpdate(empDto);
		cngService.deleteEmp(empDto);
		cngService.empEmployeeUpdate(empDto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/EmployeesChangeController?empIdx=" + empIdx);
        return modelAndView;
	}

    @RequestMapping(value = "/empFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam("attached_file") List<MultipartFile> file, HttpServletRequest srt) {
    	String filePath = RootPathUtil.getRootPath(request);
    	//String fileName = request.getParameter("fileName");
    	String empId = srt.getParameter("uploadEmpId");
        for (int i = 0 ; i < request.getMultiFileMap().size(); i++ ) {
          String umeji = "file[" + i + "]";
          MultipartFile commonFile = (MultipartFile)request.getMultiFileMap().get(umeji).get(0);
          
          try {
        	  fileService.saveFile(commonFile, filePath, empId);
  		  } catch (Exception e) {
  			e.printStackTrace();
  		  }          
        }
    }
    
    @RequestMapping(value = "/getEmpFileInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<EmpFileDto> getEmpFileInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("emp_id") String empId, @RequestParam("emp_idx") String empIdx) throws SQLException {
    	List<EmpFileDto> result = new ArrayList<EmpFileDto>();

    	result = fileService.getFileList(empId);
    	if (result.size() > 0) {
    		result.get(0).setEmpIdx(Integer.parseInt((String)empIdx));	
    	}
    	
    	return result;
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download(@RequestParam("fileUrl") String fileUrl, @RequestParam("fileName") String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView();

        //path = URLConvertUtil.decodeURIComponent(path);
        String fullPath = RootPathUtil.getRootPath(request) + fileUrl;

        File file = new File(fullPath);

        model.setViewName("fileDownload");
        model.addObject("downloadFile", file);
        model.addObject("fileName", fileName);

        return model;
    }
	
    @RequestMapping(value="/deleteFile", method = RequestMethod.GET)
    public ModelAndView deleteFile(HttpServletRequest req, @RequestParam("empFileIdx") String empFileIdx, @RequestParam("empId") String empId, @RequestParam("empIdx") String empIdx) throws UnsupportedEncodingException {
    	ModelAndView modelAndView = new ModelAndView();
    	List<EmpFileDto> fileList = new ArrayList<EmpFileDto>();
    	
        try {
        	fileService.deleteFile(Integer.parseInt(empFileIdx));
        	fileList = fileService.getFileList(empId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        modelAndView.addObject("fileList", fileList);
        modelAndView.setViewName("redirect:/EmployeesChangeController?empIdx=" + empIdx);
        return modelAndView;
    } 
    
    
}

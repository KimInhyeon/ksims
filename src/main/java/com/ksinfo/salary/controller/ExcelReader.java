package com.ksinfo.salary.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.exception.ExcelReaderException;
import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PathCheckUtil;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.salary.service.ImportExcelDAOService;
import com.ksinfo.salary.service.ImportExcelDAOServiceImpl;

@Controller
public class ExcelReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageIndexArr pIA = new PageIndexArr();
	
	@Autowired
	Environment env;

	private String fileUploadPath;
	@Autowired
	ImportExcelDAOService iEDS = new ImportExcelDAOServiceImpl();
	
	private static int ExcelUploadProgress=0;
	
	@RequestMapping(value = "/ExcelReader", method = RequestMethod.GET)
    public ModelAndView MoveToExcelReader(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException  {
		URLCheckUtil.urlCheck(req);
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);		
		ExcelUploadProgress=0;
		ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("Salary/salaryExcelUploadForm");
        pIA.getURLforArray(req, "給料マスター情報登録","0");
        
        return modelAndView;

    }
	
	@RequestMapping(value = "/ExcelReaderUpload", method = RequestMethod.POST)
    public ModelAndView ImportExcelFile(MultipartHttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException  {
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		AuthCheckUtil.authCheckAdmin(authCode);

		ModelAndView modelAndView = new ModelAndView();
		
    	try {
    		fileUploadPath = env.getProperty("excelDest");
    		
    		PathCheckUtil.pathChekAndMakeDir(fileUploadPath);
    		
    		ExcelUploadProgress=0;
    		MultipartFile file = (MultipartFile)req.getMultiFileMap().get("upload").get(0);
    		String fileType = (String)req.getParameter("file_type");
    		
            try {
      		    this.saveFile(file, fileUploadPath);
      		  } catch (Exception e) {
      			e.printStackTrace();
      		  }     
            
    		ExcelUploadProgress+=10;
    		iEDS.GetExcelImport(fileUploadPath, file, fileType);
    		pIA.getURLforArray(req, "Excel入力ページ","1");
    		if(fileType.equals("income_tax")) {
    			model.addAttribute("successMessage", "源泉徴収税額表の更新に成功しました。");
    		}else {
    			model.addAttribute("successMessage", "保険料額表の更新に成功しました。");
    		}
    		modelAndView.setViewName("Salary/salaryExcelUploadForm");
    		
    	}catch (ExcelReaderException e) {
			// TODO: handle exception	
    		String message=MessageUtils.getMessage(e.getMessage());
    		throw new ExcelReaderException(message);
		}
    
        return modelAndView;

    }
	
	public static void addExcelUploadProgress(int progress) {
		ExcelUploadProgress+=progress;
	}
	
    public void saveFile(MultipartFile multipartFile, String filePath) throws IOException {
	    String fileUrl = filePath + multipartFile.getOriginalFilename();
      
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(fileUrl);
        fos.write(data);
        fos.close();
    }
	
	@ResponseBody
	@RequestMapping(value="/CheckProgressExcel",method=RequestMethod.POST)
	public Map<String,Object> checkProgressExcel(@RequestBody Map<String,Object> map, HttpServletRequest req, HttpServletResponse res){
		int value=ExcelUploadProgress;
		map.put("percent", value);
		return map;
	}
	
}

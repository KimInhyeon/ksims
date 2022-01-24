package com.ksinfo.noticeboard.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardReqDto;
import com.ksinfo.noticeboard.exception.CustomException;
import com.ksinfo.noticeboard.service.NoticeBoardService;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class NoticeBoardWriteController {
	
	@Autowired
	NoticeBoardService noticeBoardService;
	
	@Autowired
	XssFilter xssFilter;
	
	PageIndexArr pIA = new PageIndexArr(); 
	
	@GetMapping("/NoticeBoardWriteController")
	public ModelAndView NoticeBoardWrite (HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		
		pIA.getURLforArray(req, "遉ｾ蜩｡辣ｧ莨�","0");
		modelAndView.setViewName("/board/noticeBoardWrite");	
		
		return modelAndView;
		
	}
	
	@PostMapping("/NoticeBoardWriteController")
	public ModelAndView NoticeBoardWriteImpl (NoticeBoardReqDto nDto,HttpServletRequest req , HttpServletResponse resp,MultipartFile[] files) throws ServletException, IOException, SQLException{
		
		if(nDto.getContent().length()>3000) {
			throw new CustomException("入力出来る文字数を超過しています。 (CONTENT)");
		}
		
		if(nDto.getTitle().length()>50) {
			throw new CustomException("入力出来る文字数を超過しています。 (TITLE)");
		}
		
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		String empId = (String) req.getSession().getAttribute("sid");
		ModelAndView modelAndView = new ModelAndView();
		NoticeBoardDto noticeBoardDto = new NoticeBoardDto();
		if(authCode.equals("01")){
			noticeBoardDto.setNotice_writer("管理者");
		}else {
			noticeBoardDto.setNotice_writer("社員");
		}
		noticeBoardDto.setEmp_id(empId);
		
		String filteredTitle = xssFilter.doFilter(nDto.getTitle());
		String filteredContent = xssFilter.doFilter(nDto.getContent());
		noticeBoardDto.setNotice_title(filteredTitle);
		noticeBoardDto.setNotice_content(filteredContent);
		
		AuthCheckUtil.authCheckAdmin(authCode);
		noticeBoardService.insertNoticeBoard(noticeBoardDto,files);
		
		modelAndView.addObject("resultOk","ok");
		modelAndView.setViewName("redirect:/NoticeBoardList?curPage=1");
		return modelAndView;
		
	}
}

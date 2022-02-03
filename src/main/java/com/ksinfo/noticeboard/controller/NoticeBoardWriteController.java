package com.ksinfo.noticeboard.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.AuthCheckUtil;
import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.noticeboard.dto.NoticeBoardDto;
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
		
		pIA.getURLforArray(req, "驕会ｽｾ陷ｩ�ｽ｡霎｣�ｽｧ闔ｨ�ｿｽ","0");
		modelAndView.setViewName("/board/noticeBoardWrite");	
		
		return modelAndView;
		
	}
	
	@PostMapping("/NoticeBoardWriteController")
	public ModelAndView NoticeBoardWriteImpl (NoticeBoardReqDto nDto,HttpServletRequest req , HttpServletResponse resp,MultipartFile[] files) throws ServletException, IOException, SQLException{
		
		boolean fileFlag = true;
		
		for(MultipartFile file : files) {
			if(file.getSize()>0) {
				fileFlag = Pattern.matches("^[a-zA-Z0-9가-힇ㄱ-ㅎㅏ-ㅣぁ-ゔァ-ヴー々〆〤一-龥_()]+[.][a-zA-Z0-9]{3,4}$", file.getOriginalFilename());
				if(!fileFlag) {
					throw new CustomException("ファイルのフォーマットを確認してください。 Ex)abc.txt");
				}
			}
		}
		
		if(nDto.getContent().length()>3000) {
			throw new CustomException("蜈･蜉帛�ｺ譚･繧区枚蟄玲焚繧定ｶ�驕弱＠縺ｦ縺�縺ｾ縺吶�� (CONTENT)");
		}
		
		if(nDto.getTitle().length()>50) {
			throw new CustomException("蜈･蜉帛�ｺ譚･繧区枚蟄玲焚繧定ｶ�驕弱＠縺ｦ縺�縺ｾ縺吶�� (TITLE)");
		}
		
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		String empId = (String) req.getSession().getAttribute("sid");
		ModelAndView modelAndView = new ModelAndView();
		NoticeBoardDto noticeBoardDto = new NoticeBoardDto();
		noticeBoardDto.setNotice_writer("管理者");
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

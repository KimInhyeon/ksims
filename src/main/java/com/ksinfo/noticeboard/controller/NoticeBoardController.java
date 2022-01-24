package com.ksinfo.noticeboard.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.NotImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksinfo.common.util.PageIndexArr;
import com.ksinfo.common.util.PagingModel;
import com.ksinfo.common.util.URLCheckUtil;
import com.ksinfo.employees.dto.EmpDto;
import com.ksinfo.employees.service.EmployeesListService;
import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardModifyReqDto;
import com.ksinfo.noticeboard.exception.CustomException;
import com.ksinfo.noticeboard.service.NoticeBoardService;
import com.ksinfo.noticeboard.util.Criteia;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class NoticeBoardController extends HttpServlet {
	
	@Autowired
	NoticeBoardService noticeBoardService;
	
	@Autowired
	XssFilter xssFilter;
	
	private static final long serialVersionUID = 1L;
	private PagingModel page;	//Getter & Setter
	int count;   
	
	PageIndexArr pIA = new PageIndexArr(); 
	
	@GetMapping("/NoticeBoardList")
	public ModelAndView EmployeesList(Criteia cri,HttpServletRequest req , HttpServletResponse resp,String resultOk) throws ServletException, IOException, SQLException{
		URLCheckUtil.urlCheck(req);
		ModelAndView modelAndView = new ModelAndView();
		List<NoticeBoardDto> noticeBoardList = null;
		
		count = noticeBoardService.noticeBoardCount();
		page = new PagingModel(count, cri.getCurPage());
		
		try {
			noticeBoardList = noticeBoardService.noticeBoardList(page.getPageBegin(),page.getPageEnd());
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("nlist", noticeBoardList);
		req.setAttribute("page", page);
		req.setAttribute("resultOk", resultOk);
		pIA.getURLforArray(req, "驕会ｽｾ陷ｩ�ｽ｡霎｣�ｽｧ闔ｨ�ｿｽ","0");
		
		modelAndView.setViewName("/board/noticeBoardList");	
		
		return modelAndView;
	}
	
	@GetMapping("/noticeBoardDetail")
	public ModelAndView NoticeBoardDetail(HttpServletRequest req,HttpServletResponse resp,int notice_id,Criteia cri) throws ServletException, IOException, SQLException{
		
		ModelAndView modelAndView = new ModelAndView();
		String authCode = (String) req.getSession().getAttribute("adminFlg");
		
		Cookie oldCookie = null;
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("postView")) {
	                oldCookie = cookie;
	            }
	        }
	    }
	    Integer noticeIntegerId = notice_id;
	    if (oldCookie != null) {
	        if (!oldCookie.getValue().contains("[" + noticeIntegerId.toString() + "]")) {
	        	noticeBoardService.updateNoticeBoardReadCount(notice_id);
	            oldCookie.setValue(oldCookie.getValue() + "_[" + noticeIntegerId + "]");
	            oldCookie.setPath("/");
	            oldCookie.setMaxAge(60 * 60 * 24);
	            resp.addCookie(oldCookie);
	        }
	    } else {
	    	noticeBoardService.updateNoticeBoardReadCount(notice_id);
	        Cookie newCookie = new Cookie("postView","[" + noticeIntegerId + "]");
	        newCookie.setPath("/");
	        newCookie.setMaxAge(60 * 60 * 24);
	        resp.addCookie(newCookie);
	    }
		
		NoticeBoardDto nDto = noticeBoardService.findNoticeBoardById(notice_id);
		List<NoticeBoardFileDto> fileList = noticeBoardService.getAttachFileList(notice_id); 
		modelAndView.addObject("fileList", fileList);
		modelAndView.addObject("ndto", nDto);
		modelAndView.addObject("authCode", authCode);
		modelAndView.addObject("cri", cri);
		
		modelAndView.setViewName("/board/noticeBoardDetail");	
		
		return modelAndView;
	}
	
	
	@GetMapping("/noticeBoardDelete")
	public ModelAndView NoticeBoardDelete(int notice_id,Criteia cri) throws ServletException, IOException, SQLException{
		ModelAndView modelAndView = new ModelAndView();
		noticeBoardService.deleteNoticeBoard(notice_id);
		modelAndView.addObject("cri", cri);
		modelAndView.addObject("resultOk","deleteOk");
		modelAndView.setViewName("redirect:/NoticeBoardList?curPage="+cri.getCurPage());
		return modelAndView;
	}
	
	@GetMapping("/noticeBoardModify")
	public ModelAndView NoticeBoardModify(int notice_id,Criteia cri) throws ServletException, IOException, SQLException{
		
		ModelAndView modelAndView = new ModelAndView();
		NoticeBoardDto nDto = noticeBoardService.findNoticeBoardById(notice_id);
		List<NoticeBoardFileDto> fileList = noticeBoardService.getAttachFileList(notice_id);
		modelAndView.addObject("ndto", nDto);
		modelAndView.addObject("cri", cri);
		modelAndView.addObject("fileList",fileList);
		modelAndView.setViewName("/board/noticeBoardModify");	
		
		return modelAndView;
	}
	
	@PostMapping("/noticeBoardModify")
	public ModelAndView NoticeBoardModifyAction(NoticeBoardModifyReqDto dto,Criteia cri,MultipartFile[] files) throws ServletException, IOException, SQLException{
		
		if(dto.getNotice_content().length()>3000) {
			throw new CustomException("蜈･蜉帛�ｺ譚･繧区枚蟄玲焚繧定ｶ�驕弱＠縺ｦ縺�縺ｾ縺吶�� (CONTENT)");
		}
		
		if(dto.getNotice_title().length()>50) {
			throw new CustomException("蜈･蜉帛�ｺ譚･繧区枚蟄玲焚繧定ｶ�驕弱＠縺ｦ縺�縺ｾ縺吶�� (TITLE)");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		dto.setNotice_title(xssFilter.doFilter(dto.getNotice_title()));
		dto.setNotice_content(xssFilter.doFilter(dto.getNotice_content()));
		noticeBoardService.modifyNoticeBoard(dto.getNotice_id(), dto,files);
		modelAndView.addObject("cri", cri);
		modelAndView.addObject("resultOk","modifyOk");
		modelAndView.setViewName("redirect:/NoticeBoardList?curPage="+cri.getCurPage());	
		return modelAndView;
	}
	
	@GetMapping("/noticeBoardDownload/{notice_file_idx}")
	public void downloadNoticeFile(@PathVariable("notice_file_idx") Long notice_file_idx,HttpServletResponse response) throws SQLException {
		
		if(notice_file_idx == null) throw new RuntimeException("豁｣縺励￥縺ｪ縺�繧｢繧ｯ繧ｻ繧ｹ縺ｧ縺吶��");
		
		NoticeBoardFileDto fileInfo = noticeBoardService.getAttachDetail(notice_file_idx);
		
		if (fileInfo == null || "1".equals(fileInfo.getLogicalDelFlg())) {
			throw new RuntimeException("繝輔ぃ繧､繝ｫ縺ｮ諠�蝣ｱ縺瑚ｦ九▽縺九ｊ縺ｾ縺帙ｓ縲�");
		}
		
		String uploadDate = fileInfo.getFile_create_date().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("C:", "noticeboard", "upload", uploadDate).toString();
		
		String filename = fileInfo.getOriginal_name();
		File file = new File(uploadPath, fileInfo.getSave_name());
		
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");

			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (IOException e) {
			throw new RuntimeException("繝輔ぃ繧､繝ｫ繝�繧ｦ繝ｳ繝ｭ繝ｼ繝峨↓螟ｱ謨励＠縺ｾ縺励◆縲�");

		} catch (Exception e) {
			throw new RuntimeException("繧ｷ繧ｹ繝�繝�繧ｨ繝ｩ繝ｼ");
		}
	}
	
	public PagingModel getPage() {
		return page;
	}

	public void setPage(PagingModel page) {
		this.page = page;
	}
	
}

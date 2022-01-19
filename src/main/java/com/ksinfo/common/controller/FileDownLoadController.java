package com.ksinfo.common.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.util.URLCheckUtil;

@Controller
public class FileDownLoadController {
	
	@RequestMapping("/download")
    public ModelAndView download(@RequestParam("path") String path, @RequestParam("fileName") String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
		URLCheckUtil.urlCheck(request);
        ModelAndView modelAndView = new ModelAndView();
        
        File file = new File(path);
        
        modelAndView.setViewName("fileDownloadView");
        modelAndView.addObject("downloadFile", file); //file_url
        modelAndView.addObject("fileName", fileName); //file_name
        
        return modelAndView;
	}

}

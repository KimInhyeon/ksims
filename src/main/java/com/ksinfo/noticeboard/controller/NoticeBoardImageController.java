package com.ksinfo.noticeboard.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dto.NoticeBoardImageDto;
import com.ksinfo.noticeboard.service.NoticeBoardImageService;

@Controller
public class NoticeBoardImageController {
	
	@Autowired
	NoticeBoardImageService noticeBoardImageService;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@PostMapping("/noticeBoardImage")
	public ResponseEntity<?> imageUpload(@RequestParam("file2") MultipartFile file) {
		try {
			noticeBoardImageService.saveNoticeBoardImage(file);
			NoticeBoardImageDto ndto = null;
			ndto = noticeBoardImageService.getLastSaveFile();
			return ResponseEntity.ok().body("/ksims/image/"+ndto.getNpf_id());
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/image/{npfId}")
	public ResponseEntity<?> serveFile(@PathVariable int npfId){
		NoticeBoardImageDto uploadFile = null;
		try {
			uploadFile = noticeBoardImageService.findNoticeBoardImageById(npfId);
			Resource resource = resourceLoader.getResource("file:" + uploadFile.getNpf_file_path());
			return ResponseEntity.ok().body(resource);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
}

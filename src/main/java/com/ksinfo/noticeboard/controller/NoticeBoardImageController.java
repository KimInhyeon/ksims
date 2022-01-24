package com.ksinfo.noticeboard.controller;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/noticeImageClean")
	public @ResponseBody ResponseEntity<?> noticeImageClean() throws ServletException, IOException, SQLException{
		
		// 보드게시판의 content부분 list로 가지고오기
		List<String> noticeContentList = noticeBoardImageService.findNoticeContentList();
		
		// 이 문자가 몇번째 인덱스에 있는지 indexof 메소드로 찾을거임
		String firstWord = "<img src=\"/ksims/image/";
		String lastWord = "\" style=\"width: ";
		
		int firstIndex = 0;
		int lastIndex = 0;
		
		List<String> indexList = new ArrayList<String>();
		List<Integer> indexListInteger = new ArrayList<>();
		
		// 전체 content에서  <img src=/ksims/image/뒤에 imageId만 추출해서 indexList에 담음
		for(int i=0;i<noticeContentList.size();i++) {
			firstIndex = noticeContentList.get(i).indexOf(firstWord);
			lastIndex = noticeContentList.get(i).indexOf(lastWord);
			while(firstIndex != -1) {
				try {
					indexList.add(noticeContentList.get(i).substring(firstIndex+firstWord.length(),lastIndex));
					firstIndex = noticeContentList.get(i).indexOf(firstWord, firstIndex+firstWord.length());
					lastIndex = noticeContentList.get(i).indexOf(lastWord,lastIndex+lastWord.length());
				}catch (Exception e) {
					throw new RuntimeException("subString error");
				}
			}
		}
		
		// 추출한 imageId 타입을 String에서 int 타입으로 변경 npf_id로 쓸거임
		for(int i=0;i<indexList.size();i++) {
			indexListInteger.add(Integer.parseInt(indexList.get(i)));
		}
		
		// notice_photo_file테이블에서  npf_id 리스트를 넘겨서 select ~ in 연산으로 해당 npf_id에 대한 save_name 전부 가져옴
		List<String> noticeImageFileNameList = noticeBoardImageService.findSaveFileNameInNpfNo(indexListInteger);
		
		// 저장 경로인 "C:\\Users\\user\\workspace2\\NoticeBoardImage\\" 안에 모든 파일들의 이름을 for문 돌면서 
		// noticeImageFileNameList여기 리스트에 담긴 save_name과 비교하고 noticeImageFileNameList에 담긴 save_name이 
		// 이 모든 폴더에 모든 파일이름중에 없으면 해당 index의 파일을 삭제함
		String filePath = "C:\\Users\\user\\workspace2\\NoticeBoardImage\\";
		File dir = new File(filePath);
		File file = null;
		String[] filenames = dir.list();
		boolean flag = false;
		for(int i=0;i<filenames.length;i++) {
			for(int j=0;j<noticeImageFileNameList.size();j++) {
				if(filenames[i].equals(noticeImageFileNameList.get(j))) {
					flag = true;
				}
			}
			if(flag == false) {
				file = new File(filePath+filenames[i]);
				file.delete();
			}
			flag = false;
		}
		return new ResponseEntity<>("{\"result\" : \"ok\"}",HttpStatus.OK) ;
	}
	
	@GetMapping("/noticeImageClean2")
	public @ResponseBody ResponseEntity<?> noticeImageClean2() throws ServletException, IOException, SQLException{
		
		// 보드게시판의 content부분 list로 가지고오기
		List<String> noticeContentList = noticeBoardImageService.findNoticeContentList();
		
		String checkWord = "src=\"/ksims/image/";
		
		// 모든 imageId를 list로 가지고옴 
		List<String> imageNumList = noticeBoardImageService.findNoticeImageIdList();
		
		List<Integer> stringToInteger = new ArrayList<>();
		List<String> containedImageNumList = new ArrayList<>();
		
		// notice_board에 있는 content에 "src=\"/ksims/image/"+이미지 id라는 문자를 포함하는지 확인 하고 포함하면 imageId만 리스트에 넣음
		for(int i=0;i<noticeContentList.size();i++) {
			for(int k=0;k<imageNumList.size();k++) {
				if(noticeContentList.get(i).contains(checkWord+imageNumList.get(k)+"\"")) {
					containedImageNumList.add(imageNumList.get(k));
				}
			}
		}
		
		// 추출한 imageId 타입을 String에서 int 타입으로 변경 npf_id로 쓸거임
		for(int i=0;i<containedImageNumList.size();i++) {
			stringToInteger.add(Integer.parseInt(containedImageNumList.get(i)));
		}
		
		// notice_photo_file테이블에서  npf_id 리스트를 넘겨서 select ~ in 연산으로 해당 npf_id에 대한 save_name 전부 가져옴
		List<String> noticeImageFileNameList = noticeBoardImageService.findSaveFileNameInNpfNo(stringToInteger);
		
		// 저장 경로인 "C:\\Users\\user\\workspace2\\NoticeBoardImage\\" 안에 모든 파일들의 이름을 for문 돌면서 
		// noticeImageFileNameList여기 리스트에 담긴 save_name과 비교하고 noticeImageFileNameList에 담긴 save_name이 
		// 이 모든 폴더에 모든 파일이름중에 없으면 해당 index의 파일을 삭제함
		String filePath = "C:\\Users\\user\\workspace2\\NoticeBoardImage\\";
		File dir = new File(filePath);
		File file = null;
		String[] filenames = dir.list();
		boolean flag = false;
		for(int i=0;i<filenames.length;i++) {
			for(int j=0;j<noticeImageFileNameList.size();j++) {
				if(filenames[i].equals(noticeImageFileNameList.get(j))) {
					flag = true;
				}
			}
			if(flag == false) {
				file = new File(filePath+filenames[i]);
				file.delete();
			}
			flag = false;
		}
		return new ResponseEntity<>("{\"result\" : \"ok\"}",HttpStatus.OK) ;
	}
	
}

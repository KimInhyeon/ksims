package com.ksinfo.noticeboard.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dao.NoticeBoardImageDao;
import com.ksinfo.noticeboard.dto.NoticeBoardImageDto;

@Service
public class NoticeBoardImageServiceImpl implements NoticeBoardImageService{
	
	@Autowired
	NoticeBoardImageDao NoticeBoardImageDao;
	
	private final Path rootLocation; 
	
	public NoticeBoardImageServiceImpl(String uploadPath) {
		this.rootLocation = Paths.get(uploadPath);
		System.out.println(rootLocation.toString());
	}
	
	
	@Override
	public void saveNoticeBoardImage(MultipartFile file) throws SQLException {
		
			//		 fileName : test2.jpg
			//		 filePath : d:/images/uuid-test2.jpg
			//		 saveFileName : uuid-예2.png
			//		 contentType : image/jpeg
			//		 size : 4994942
			//		 registerDate : 2020-02-06 22:29:57.748
		try {
			if(file.isEmpty()) {
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
			}
				
			String saveFileName = fileSave(rootLocation.toString(), file);
			NoticeBoardImageDto saveFile = new NoticeBoardImageDto();
			saveFile.setNpf_file_name(file.getOriginalFilename());
			saveFile.setNpf_save_file_name(saveFileName);
			saveFile.setNpf_content_type(file.getContentType());
			saveFile.setNpf_size(file.getResource().contentLength());
			saveFile.setNpf_regdate(LocalDateTime.now());
			saveFile.setNpf_file_path(rootLocation.toString().replace(File.separatorChar, '/') +'/' + saveFileName);
			NoticeBoardImageDao.saveNoticeBoardImage(saveFile);
			
				
		} catch(Exception e) {
				e.printStackTrace();
		}
			
	}

	public String fileSave(String rootLocation, MultipartFile file) throws IOException {
		File uploadDir = new File(rootLocation);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// saveFileName 생성
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + file.getOriginalFilename();
		File saveFile = new File(rootLocation, saveFileName);
		FileCopyUtils.copy(file.getBytes(), saveFile);
		
		return saveFileName;
	}
		
	@Override
	public NoticeBoardImageDto getLastSaveFile() throws SQLException {
		return NoticeBoardImageDao.getLastSaveFile();
	}
	
	@Override
	public NoticeBoardImageDto findNoticeBoardImageById(int npfId) throws SQLException {
		return NoticeBoardImageDao.findNoticeBoardImageById(npfId);
	}
	
	@Override
	public List<String> findNoticeContentList() throws SQLException {
		return NoticeBoardImageDao.findNoticeContentList();
	}
	
	@Override
	public List<String> findSaveFileNameInNpfNo(List<Integer> list) throws SQLException {
		return NoticeBoardImageDao.findSaveFileNameInNpfNo(list);
	}
	
	@Override
	public List<String> findNoticeImageIdList() throws SQLException {
		return NoticeBoardImageDao.findNoticeImageIdList();
	}
	
}

package com.ksinfo.noticeboard.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.exception.AttachFileException;

@Component
public class FileUtils {
	
	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	private final String uploadFilePath = Paths.get("C:", "noticeboard", "upload", today).toString();
	
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public List<NoticeBoardFileDto> uploadFiles(MultipartFile[] files, int notice_id) {
		
		List<NoticeBoardFileDto> attachList = new ArrayList<>();
		
		File dir = new File(uploadFilePath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		
		for (MultipartFile file : files) {
			try {
				if(file.getSize() == 0) {
					continue;
				}
				
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				final String saveName = getRandomString() + "." + extension;
				
				File target = new File(uploadFilePath, saveName);
				file.transferTo(target);

				NoticeBoardFileDto attach = new NoticeBoardFileDto();
				attach.setNotice_id(notice_id);
				attach.setOriginal_name(file.getOriginalFilename()); 
				attach.setSave_name(saveName);
				attach.setSize(file.getSize());
				
				attachList.add(attach);
				
			} catch (IOException e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

			} catch (Exception e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}
		} // end of for

		return attachList;
	}
}

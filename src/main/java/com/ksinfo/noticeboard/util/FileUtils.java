package com.ksinfo.noticeboard.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.exception.AttachFileException;

@Component
public class FileUtils {
	
	/** 오늘 날짜 */
	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	
	/** 업로드 경로 */
	private final String uploadFilePath = Paths.get("C:", "noticeboard", "upload", today).toString();
	
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public List<NoticeBoardFileDto> uploadFiles(MultipartFile[] files, int notice_id) {
		/* 업로드 파일 정보를 담을 비어있는 리스트 */
		List<NoticeBoardFileDto> attachList = new ArrayList<>();
		
		/* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
		File dir = new File(uploadFilePath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		/* 파일 개수만큼 forEach 실행 */
		for (MultipartFile file : files) {
			try {
				if(file.getSize() == 0) {
					continue;
				}
				/* 파일 확장자 */
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
				final String saveName = getRandomString() + "." + extension;
				/* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
				File target = new File(uploadFilePath, saveName);
				file.transferTo(target);

				/* 파일 정보 저장 */
				NoticeBoardFileDto attach = new NoticeBoardFileDto();
				attach.setNotice_id(notice_id);
				attach.setOriginal_name(file.getOriginalFilename()); 
				attach.setSave_name(saveName);
				attach.setSize(file.getSize());
				/* 파일 정보 추가 */
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

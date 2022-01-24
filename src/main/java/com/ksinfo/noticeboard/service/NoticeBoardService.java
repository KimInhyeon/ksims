package com.ksinfo.noticeboard.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardModifyReqDto;

public interface NoticeBoardService {
	
	public int insertNoticeBoard(NoticeBoardDto nDto) throws SQLException;
	public int insertNoticeBoard(NoticeBoardDto nDto,MultipartFile[] files) throws SQLException;
	
	public List<NoticeBoardDto> noticeBoardList(int start,int end) throws SQLException;
	public NoticeBoardDto findNoticeBoardById(@Param("notice_id") int notice_id) throws SQLException;
	public void updateNoticeBoardReadCount(@Param("notice_id") int notice_id) throws SQLException;
	public void deleteNoticeBoard(@Param("notice_id") int notice_id) throws SQLException; 
	public void modifyNoticeBoard(int notice_id,NoticeBoardModifyReqDto dto) throws SQLException;
	public void modifyNoticeBoard(int notice_id,NoticeBoardModifyReqDto dto,MultipartFile[] files) throws SQLException;
	public int noticeBoardCount() throws SQLException;
	public NoticeBoardFileDto getAttachDetail(Long notice_file_idx) throws SQLException;
	public List<NoticeBoardFileDto> getAttachFileList(int notice_id) throws SQLException;
	
}


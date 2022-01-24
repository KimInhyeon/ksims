package com.ksinfo.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardModifyReqDto;


public interface NoticeBoardDao {
	
	public int insertNoticeBoard(NoticeBoardDto nDto) throws SQLException;
	public List<NoticeBoardDto> noticeBoardList(int start,int end) throws SQLException;
	public NoticeBoardDto findNoticeBoardById(@Param("notice_id") int notice_id) throws SQLException;
	public void updateNoticeBoardReadCount(@Param("notice_id") int notice_id) throws SQLException;
	public void deleteNoticeBoard(@Param("notice_id") int notice_id) throws SQLException;
	public void modifyNoticeBoard(int notice_id,NoticeBoardModifyReqDto dto) throws SQLException;
	public int noticeBoardCount() throws SQLException;
	
	public int insertAttach(List<NoticeBoardFileDto> attachList) throws SQLException;
	public NoticeBoardFileDto selectAttachDetail(@Param("notice_file_idx") Long notice_file_idx) throws SQLException;
	public int deleteAttach(@Param("notice_id") int notice_id) throws SQLException;
	public List<NoticeBoardFileDto> selectAttachList(@Param("notice_id") int notice_id) throws SQLException;
	public int selectAttachTotalCount(@Param("notice_id") int notice_id) throws SQLException;
	public int undeleteAttach(List<Long> notice_file_idx) throws SQLException;
}

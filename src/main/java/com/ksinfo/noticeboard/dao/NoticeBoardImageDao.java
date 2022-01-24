package com.ksinfo.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ksinfo.noticeboard.dto.NoticeBoardImageDto;


public interface NoticeBoardImageDao {
	public void saveNoticeBoardImage(NoticeBoardImageDto nDto) throws SQLException;
	public NoticeBoardImageDto getLastSaveFile() throws SQLException;
	public NoticeBoardImageDto findNoticeBoardImageById(@Param("npfId") int npfId) throws SQLException;
	public List<String> findNoticeContentList() throws SQLException; 
	public List<String> findSaveFileNameInNpfNo(List<Integer> list) throws SQLException;
	public List<String> findNoticeImageIdList() throws SQLException;
}

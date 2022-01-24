package com.ksinfo.noticeboard.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.noticeboard.dto.NoticeBoardImageDto;

@Repository
public class NoticeBoardImageDaoImpl extends SqlSessionFactoryService implements NoticeBoardImageDao {
	
	@Override
	public void saveNoticeBoardImage(NoticeBoardImageDto nDto) throws SQLException {
		getSqlSessionTemplate().insert("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.saveNoticeBoardImage",nDto);
	}
	
	@Override
	public NoticeBoardImageDto getLastSaveFile() throws SQLException {
		return getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.getLastSaveFile");
	}
	
	@Override
	public NoticeBoardImageDto findNoticeBoardImageById(int npfId) throws SQLException {
		return getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.findNoticeBoardImageById",npfId);
	}
	
	@Override
	public List<String> findNoticeContentList() throws SQLException {
		return getSqlSessionTemplate().selectList("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.findNoticeContentList");
	}
	
	@Override
	public List<String> findSaveFileNameInNpfNo(List<Integer> list) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list",list);
		return getSqlSessionTemplate().selectList("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.findSaveFileNameInNpfNo",map);
	}
	
	@Override
	public List<String> findNoticeImageIdList() throws SQLException {
		return getSqlSessionTemplate().selectList("com.ksinfo.noticeboard.dao.NoticeBoardImageDao.findNoticeImageIdList");
	}
}

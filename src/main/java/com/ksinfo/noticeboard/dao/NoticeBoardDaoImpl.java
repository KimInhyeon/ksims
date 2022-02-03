package com.ksinfo.noticeboard.dao;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ksinfo.common.util.SqlSessionFactoryService;
import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardModifyReqDto;

@Repository
public class NoticeBoardDaoImpl extends SqlSessionFactoryService implements NoticeBoardDao {
	
	@Override
	public int insertNoticeBoard(NoticeBoardDto nDto) throws SQLException {
		return getSqlSessionTemplate().insert("com.ksinfo.noticeboard.dao.NoticeBoardDao.insertNoticeBoard",nDto);
	}
	
	@Override
	public List<NoticeBoardDto> noticeBoardList(int start,int end) throws SQLException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", start);
        paramMap.put("end", end);
		return getSqlSessionTemplate().selectList("com.ksinfo.noticeboard.dao.NoticeBoardDao.noticeBoardList",paramMap);
	}
	
	@Override
	public NoticeBoardDto findNoticeBoardById(int notice_id) throws SQLException {
		return getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardDao.findNoticeBoardById", notice_id);
	}
	
	@Override
	public void updateNoticeBoardReadCount(int notice_id) throws SQLException {
		getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.updateNoticeBoardReadCount", notice_id);
	}
	
	@Override
	public int deleteNoticeBoard(int notice_id) throws SQLException {
		return getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.deleteNoticeBoard", notice_id);
	}
	
	@Override
	public void modifyNoticeBoard(int notice_id, NoticeBoardModifyReqDto dto) throws SQLException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("notice_id", notice_id);
        paramMap.put("dto", dto);
		getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.modifyNoticeBoard",paramMap);
	}
	@Override
	public int noticeBoardCount() throws SQLException {
		return getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardDao.noticeBoardCount");
	}
	
	/////////////////////////
	
	@Override
	public int deleteAttach(int notice_id) {
		return getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.deleteAttach",notice_id);
	}
	
	@Override
	public NoticeBoardFileDto selectAttachDetail(Long notice_file_idx) {
		return  getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardDao.selectAttachDetail",notice_file_idx);
	}
	
	@Override
	public int insertAttach(List<NoticeBoardFileDto> attachList) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list",attachList);
		return getSqlSessionTemplate().insert("com.ksinfo.noticeboard.dao.NoticeBoardDao.insertAttach",map);
	}
	
	@Override
	public List<NoticeBoardFileDto> selectAttachList(int notice_id) {
		return getSqlSessionTemplate().selectList("com.ksinfo.noticeboard.dao.NoticeBoardDao.selectAttachList",notice_id);
	}
	
	@Override
	public int selectAttachTotalCount(int notice_id) {
		return getSqlSessionTemplate().selectOne("com.ksinfo.noticeboard.dao.NoticeBoardDao.selectAttachTotalCount",notice_id);
	}
	
	@Override
	public int undeleteAttach(List<Long> notice_file_idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list",notice_file_idx);
		return getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.undeleteAttach",map);
	}
	
	@Override
	public int deleteAttach2(Set<Long> notice_file_idx, int notice_id) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list",notice_file_idx);
		map.put("notice_id", notice_id);
		return getSqlSessionTemplate().update("com.ksinfo.noticeboard.dao.NoticeBoardDao.deleteAttach2",map);
	}
}

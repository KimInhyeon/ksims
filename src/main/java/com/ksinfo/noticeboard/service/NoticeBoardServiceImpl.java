package com.ksinfo.noticeboard.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.noticeboard.dao.NoticeBoardDao;
import com.ksinfo.noticeboard.dto.NoticeBoardDto;
import com.ksinfo.noticeboard.dto.NoticeBoardFileDto;
import com.ksinfo.noticeboard.dto.NoticeBoardModifyReqDto;
import com.ksinfo.noticeboard.util.FileUtils;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Autowired
	NoticeBoardDao noticeBoardDao;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public int insertNoticeBoard(NoticeBoardDto nDto) throws SQLException {
		return noticeBoardDao.insertNoticeBoard(nDto);
	}
	
	@Override
	public int insertNoticeBoard(NoticeBoardDto nDto, MultipartFile[] files) throws SQLException {
		int queryResult = 1;

		if (insertNoticeBoard(nDto) == 0) {
			return 0;
		}
		List<NoticeBoardFileDto> fileList = fileUtils.uploadFiles(files, nDto.getNotice_id());
		if (CollectionUtils.isEmpty(fileList) == false) {
			queryResult = noticeBoardDao.insertAttach(fileList);
			if (queryResult < 1) {
				queryResult = 0;
			}
		}

		return queryResult;
	}
	
	@Override
	public List<NoticeBoardDto> noticeBoardList(int start,int end) throws SQLException {
		return noticeBoardDao.noticeBoardList(start,end);
	}
	
	@Override
	public NoticeBoardDto findNoticeBoardById(int notice_id) throws SQLException {
		return noticeBoardDao.findNoticeBoardById(notice_id);
	}
	
	@Override
	public void updateNoticeBoardReadCount(int notice_id) throws SQLException {
		noticeBoardDao.updateNoticeBoardReadCount(notice_id);
	}
	
	@Override
	public void deleteNoticeBoard(int notice_id) throws SQLException {
		noticeBoardDao.deleteNoticeBoard(notice_id);
	}
	
	@Override
	public void modifyNoticeBoard(int notice_id, NoticeBoardModifyReqDto dto) throws SQLException {
		noticeBoardDao.modifyNoticeBoard(notice_id, dto);
	}
	
	@Override
	public void modifyNoticeBoard(int notice_id, NoticeBoardModifyReqDto dto, MultipartFile[] files) throws SQLException {
		noticeBoardDao.modifyNoticeBoard(notice_id, dto);
		
		if ("Y".equals(dto.getChangeYn())) {
			noticeBoardDao.deleteAttach(notice_id);

			// fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 '0'으로 업데이트
			if (CollectionUtils.isEmpty(dto.getFileIdxs()) == false) {
				noticeBoardDao.undeleteAttach(dto.getFileIdxs());
			}
		}
		
		List<NoticeBoardFileDto> fileList = fileUtils.uploadFiles(files, notice_id);
		if (CollectionUtils.isEmpty(fileList) == false) {
			noticeBoardDao.insertAttach(fileList);
		}
	}
	
	@Override
	public int noticeBoardCount() throws SQLException {
		return noticeBoardDao.noticeBoardCount();
	}
	
	@Override
	public List<NoticeBoardFileDto> getAttachFileList(int notice_id) throws SQLException {
		int noticeBoardFileTotalCount = noticeBoardDao.selectAttachTotalCount(notice_id);
		if(noticeBoardFileTotalCount<1) {
			return Collections.emptyList();
		}
		return noticeBoardDao.selectAttachList(notice_id);
	}
	
	@Override
	public NoticeBoardFileDto getAttachDetail(Long notice_file_idx) throws SQLException {
		return noticeBoardDao.selectAttachDetail(notice_file_idx);
	}
	
}

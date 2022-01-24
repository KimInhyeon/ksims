package com.ksinfo.noticeboard.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeBoardModifyReqDto {
	private int notice_id;
	private String emp_id;
	private String notice_title;
	private String notice_content;
	private String notice_writer;
	private int notice_readcount;
	/** 파일 변경 여부 */
	private String changeYn;
	/** 파일 인덱스 리스트 */
	private List<Long> fileIdxs;
}

package com.ksinfo.noticeboard.dto;

import java.util.Set;

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
	private String changeYn;
	private Set<Long> deleteFileIdx;
}

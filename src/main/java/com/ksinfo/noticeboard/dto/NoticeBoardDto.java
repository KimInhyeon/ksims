package com.ksinfo.noticeboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeBoardDto {
	private int notice_id;
	private String emp_id;
	private String notice_title;
	private String notice_content;
	private String notice_writer;
	private int notice_readcount;
	private LocalDateTime notice_regdate;
	private LocalDateTime notice_update;
	
}

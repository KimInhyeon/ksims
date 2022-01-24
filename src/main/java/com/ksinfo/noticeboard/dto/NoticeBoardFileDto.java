package com.ksinfo.noticeboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeBoardFileDto {
	private Long notice_file_idx;
	private int notice_id;
	private String original_name;
	private String save_name;
	private Long size;
	private LocalDateTime file_delete_date;
	private LocalDateTime file_create_date;
	private String logicalDelFlg;
}

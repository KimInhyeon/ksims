package com.ksinfo.noticeboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardImageDto {
	private int npf_id;
	private String npf_file_name;
	private String npf_save_file_name;
	private String npf_file_path;
	private long npf_size;
	private LocalDateTime npf_regdate;
	private String npf_content_type;
}

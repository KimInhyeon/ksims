package com.ksinfo.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpDtoResp {
	private int emp_idx;
	private String emp_id;
	private boolean partner_comp_flg;
	private String position_code;
	private String field_code;
	private String emp_name;
	private String emp_age;
	private String emp_gender;
	private String department_code;
	private String emp_addr;
	private String phone_number;
	private int dependent_count;
	private java.util.Date hired_date;
	private java.util.Date stay_expiration_date;
	private java.util.Date dorm_in_date;
	private java.util.Date dorm_out_date;
	private boolean emp_career_flg;
	private int emp_career_point;
	private String auth_code;
	//霑ｽ蜉�-20210607
	private String emp_nationality;
	private String stay_expiration_number;
	private String emp_name_kana;
	private String emp_name_eng;
	private String emp_jumin;
	private String emp_comp_mail;
	private String emp_mynumber;
	private String emp_pension_number;
	private String emp_bank_book_number;
	private String emergency_phone_number;
	//霑ｽ蜉�-20210712
	private String emp_post;
	private String emp_bank_name;
	private String emp_bank_num_gubun;
	private String emp_bank_bran;
	private String emp_section_name;
	private int emp_pay_level;
	//霑ｽ蜉�-20210802
	private String emp_file;
	private String emp_url;
}

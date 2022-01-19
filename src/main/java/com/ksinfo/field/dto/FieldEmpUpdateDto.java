package com.ksinfo.field.dto;

import org.springframework.stereotype.Repository;

@Repository
public class FieldEmpUpdateDto {
	private String field_code;
	private String emp_id;
	private String rec_update_id;
	private String auth_code;
	
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getRec_update_id() {
		return rec_update_id;
	}
	public void setRec_update_id(String rec_update_id) {
		this.rec_update_id = rec_update_id;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	
	
	
}

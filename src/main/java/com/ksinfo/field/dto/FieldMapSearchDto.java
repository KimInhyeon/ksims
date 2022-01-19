package com.ksinfo.field.dto;

public class FieldMapSearchDto {
	private String search_select;
	private String word;
	private String[] field_code;
	
	public String getSearch_select() {
		return search_select;
	}
	public void setSearch_select(String search_select) {
		this.search_select = search_select;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public String[] getField_code() {
		return field_code;
	}
	public void setField_code(String[] field_code) {
		this.field_code = field_code;
	}
	
	
}

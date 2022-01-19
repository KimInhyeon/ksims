package com.ksinfo.appraisal.dto;

public class AppraisalHistoryDto {
	private String position_name;
	private String project_name;
	private String appraisal_start_date;
	private String appraisal_end_date;
	private String app_grade;
	private float app_score;
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getAppraisal_start_date() {
		return appraisal_start_date;
	}
	public void setAppraisal_start_date(String appraisal_start_date) {
		this.appraisal_start_date = appraisal_start_date;
	}
	public String getAppraisal_end_date() {
		return appraisal_end_date;
	}
	public void setAppraisal_end_date(String appraisal_end_date) {
		this.appraisal_end_date = appraisal_end_date;
	}
	public String getApp_grade() {
		return app_grade;
	}
	public void setApp_grade(String app_grade) {
		this.app_grade = app_grade;
	}
	public float getApp_score() {
		return app_score;
	}
	public void setApp_score(float app_score) {
		this.app_score = app_score;
	}
	
}

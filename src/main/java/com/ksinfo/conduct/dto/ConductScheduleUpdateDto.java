package com.ksinfo.conduct.dto;

public class ConductScheduleUpdateDto {
	String work_start_time;
	String work_break_time; 
	String work_end_time;
	String worktime_hours; 
	String overtime_hours;
	String worktime;
	String conduct_note;
	String field_code;
	double tot_work_time;
	int ks_conduct_id;
	String work_code;
	
	
	public int getKs_conduct_id() {
		return ks_conduct_id;
	}
	public void setKs_conduct_id(int ks_conduct_id) {
		this.ks_conduct_id = ks_conduct_id;
	}

	public String getWork_break_time() {
		return work_break_time;
	}
	public void setWork_break_time(String work_break_time) {
		this.work_break_time = work_break_time;
	}

	public String getWorktime_hours() {
		return worktime_hours;
	}
	public void setWorktime_hours(String worktime_hours) {
		this.worktime_hours = worktime_hours;
	}
	public String getOvertime_hours() {
		return overtime_hours;
	}
	public void setOvertime_hours(String overtime_hours) {
		this.overtime_hours = overtime_hours;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getConduct_note() {
		return conduct_note;
	}
	public void setConduct_note(String conduct_note) {
		this.conduct_note = conduct_note;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getWork_start_time() {
		return work_start_time;
	}
	public void setWork_start_time(String work_start_time) {
		this.work_start_time = work_start_time;
	}
	public String getWork_end_time() {
		return work_end_time;
	}
	public void setWork_end_time(String work_end_time) {
		this.work_end_time = work_end_time;
	}
	public double getTot_work_time() {
		return tot_work_time;
	}
	public void setTot_work_time(double tot_work_time) {
		this.tot_work_time = tot_work_time;
	}
	public String getWork_code() {
		return work_code;
	}
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
	
}

package com.ksinfo.appraisal.dto;

public class AppraisalMgtDto {
	private int appraisal_idx=0;
	private String emp_id;
	private String emp_name;
	private String conduct_start_month;
	private String conduct_end_month;
	private String appriaiser_id=" ";
	private String appriaiser_name=" ";
	private String field_code=" ";
	private String field_name=" ";
	private String field_role=" ";
	private String field_addr=" ";
	private String field_position_code=" ";
	private String field_position_name=" ";
	private String leader_code;
	private String field_env=" ";
	private String field_tool=" ";
	private int self_diligence;
	private int self_passion;
	private int self_responsibility;
	private int self_understand;
	private int self_learning;
	private int self_application;
	private int self_communication;
	private int self_contribution;
	private float self_sum;
	private String self_advantage;
	private String self_weak;
	private String self_eval;
	private int ld_diligence;
	private int ld_passion;
	private int ld_responsibility;
	private int ld_understand;
	private int ld_learning;
	private int ld_application;
	private int ld_communication;
	private int ld_contribution;
	private float ld_sum;
	private String ld_advantage;
	private String ld_weak;
	private String ld_eval;
	private int cust_ability;
	private String cust_ability_cmt;
	private int cust_keepwork;
	private String cust_keepwork_cmt;
	private float cust_sum;
	private int ld_sales;
	private float sales_sum;
	public float getCust_sum() {
		return cust_sum;
	}
	public void setCust_sum(float cust_sum) {
		this.cust_sum = cust_sum;
	}
	private String ld_sales_cmt;
	private int ld_promote;
	private String ld_promote_cmt;
	private float app_score;
	private boolean self_app_flg;
	private boolean leader_app_flg;
	private boolean customer_app_flg;
	private boolean perfomance_app_flg;
	private boolean accept_flg; 
	private String rec_create_id;
	private String rec_create_date;
	private String rec_update_id;
	private String rec_update_date;
	private String logical_del_flg;
	public float getSales_sum() {
		return sales_sum;
	}
	public void setSales_sum(float sales_sum) {
		this.sales_sum = sales_sum;
	}
	public int getAppraisal_idx() {
		return appraisal_idx;
	}
	public void setAppraisal_idx(int appraisal_idx) {
		this.appraisal_idx = appraisal_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getConduct_start_month() {
		return conduct_start_month;
	}
	public void setConduct_start_month(String conduct_start_month) {
		this.conduct_start_month = conduct_start_month;
	}
	public String getConduct_end_month() {
		return conduct_end_month;
	}
	public void setConduct_end_month(String conduct_end_month) {
		this.conduct_end_month = conduct_end_month;
	}
	public String getAppriaiser_id() {
		return appriaiser_id;
	}
	public void setAppriaiser_id(String appriaiser_id) {
		this.appriaiser_id = appriaiser_id;
	}
	public String getField_code() {
		return field_code;
	}
	public void setField_code(String field_code) {
		this.field_code = field_code;
	}
	public String getField_addr() {
		return field_addr;
	}
	public void setField_addr(String field_addr) {
		this.field_addr = field_addr;
	}
	public String getField_role() {
		return field_role;
	}
	public void setField_role(String field_role) {
		this.field_role = field_role;
	}
	public String getField_position_code() {
		return field_position_code;
	}
	public void setField_position_code(String field_position_code) {
		this.field_position_code = field_position_code;
	}
	public String getField_position_name() {
		return field_position_name;
	}
	public void setField_position_name(String field_position_name) {
		this.field_position_name = field_position_name;
	}
	public String getLeader_code() {
		return leader_code;
	}
	public void setLeader_code(String leader_code) {
		this.leader_code = leader_code;
	}
	public String getField_env() {
		return field_env;
	}
	public void setField_env(String field_env) {
		this.field_env = field_env;
	}
	public String getField_tool() {
		return field_tool;
	}
	public void setField_tool(String field_tool) {
		this.field_tool = field_tool;
	}
	public int getSelf_diligence() {
		return self_diligence;
	}
	public void setSelf_diligence(int self_diligence) {
		this.self_diligence = self_diligence;
	}
	public int getSelf_passion() {
		return self_passion;
	}
	public void setSelf_passion(int self_passion) {
		this.self_passion = self_passion;
	}
	public int getSelf_responsibility() {
		return self_responsibility;
	}
	public void setSelf_responsibility(int self_responsibility) {
		this.self_responsibility = self_responsibility;
	}
	public int getSelf_understand() {
		return self_understand;
	}
	public void setSelf_understand(int self_understand) {
		this.self_understand = self_understand;
	}
	public int getSelf_learning() {
		return self_learning;
	}
	public void setSelf_learning(int self_learning) {
		this.self_learning = self_learning;
	}
	public int getSelf_application() {
		return self_application;
	}
	public void setSelf_application(int self_application) {
		this.self_application = self_application;
	}
	public int getSelf_communication() {
		return self_communication;
	}
	public void setSelf_communication(int self_communication) {
		this.self_communication = self_communication;
	}
	public int getSelf_contribution() {
		return self_contribution;
	}
	public void setSelf_contribution(int self_contribution) {
		this.self_contribution = self_contribution;
	}
	public float getSelf_sum() {
		return self_sum;
	}
	public void setSelf_sum(float self_sum) {
		this.self_sum = self_sum;
	}
	public String getSelf_advantage() {
		return self_advantage;
	}
	public void setSelf_advantage(String self_advantage) {
		this.self_advantage = self_advantage;
	}
	public String getSelf_weak() {
		return self_weak;
	}
	public void setSelf_weak(String self_weak) {
		this.self_weak = self_weak;
	}
	public String getSelf_eval() {
		return self_eval;
	}
	public void setSelf_eval(String self_eval) {
		this.self_eval = self_eval;
	}
	public int getLd_diligence() {
		return ld_diligence;
	}
	public void setLd_diligence(int ld_diligence) {
		this.ld_diligence = ld_diligence;
	}
	public int getLd_passion() {
		return ld_passion;
	}
	public void setLd_passion(int ld_passion) {
		this.ld_passion = ld_passion;
	}
	public int getLd_responsibility() {
		return ld_responsibility;
	}
	public void setLd_responsibility(int ld_responsibility) {
		this.ld_responsibility = ld_responsibility;
	}
	public int getLd_understand() {
		return ld_understand;
	}
	public void setLd_understand(int ld_understand) {
		this.ld_understand = ld_understand;
	}
	public int getLd_learning() {
		return ld_learning;
	}
	public void setLd_learning(int ld_learning) {
		this.ld_learning = ld_learning;
	}
	public int getLd_application() {
		return ld_application;
	}
	public void setLd_application(int ld_application) {
		this.ld_application = ld_application;
	}
	public int getLd_communication() {
		return ld_communication;
	}
	public void setLd_communication(int ld_communication) {
		this.ld_communication = ld_communication;
	}
	public int getLd_contribution() {
		return ld_contribution;
	}
	public void setLd_contribution(int ld_contribution) {
		this.ld_contribution = ld_contribution;
	}
	public float getLd_sum() {
		return ld_sum;
	}
	public void setLd_sum(float ld_sum) {
		this.ld_sum = ld_sum;
	}
	public String getLd_advantage() {
		return ld_advantage;
	}
	public void setLd_advantage(String ld_advantage) {
		this.ld_advantage = ld_advantage;
	}
	public String getLd_weak() {
		return ld_weak;
	}
	public void setLd_weak(String ld_weak) {
		this.ld_weak = ld_weak;
	}
	public String getLd_eval() {
		return ld_eval;
	}
	public void setLd_eval(String ld_eval) {
		this.ld_eval = ld_eval;
	}
	public int getCust_ability() {
		return cust_ability;
	}
	public void setCust_ability(int cust_ability) {
		this.cust_ability = cust_ability;
	}
	public String getCust_ability_cmt() {
		return cust_ability_cmt;
	}
	public void setCust_ability_cmt(String cust_ability_cmt) {
		this.cust_ability_cmt = cust_ability_cmt;
	}
	public int getCust_keepwork() {
		return cust_keepwork;
	}
	public void setCust_keepwork(int cust_keepwork) {
		this.cust_keepwork = cust_keepwork;
	}
	public String getCust_keepwork_cmt() {
		return cust_keepwork_cmt;
	}
	public void setCust_keepwork_cmt(String cust_keepwork_cmt) {
		this.cust_keepwork_cmt = cust_keepwork_cmt;
	}
	public int getLd_sales() {
		return ld_sales;
	}
	public void setLd_sales(int ld_sales) {
		this.ld_sales = ld_sales;
	}
	public String getLd_sales_cmt() {
		return ld_sales_cmt;
	}
	public void setLd_sales_cmt(String ld_sales_cmt) {
		this.ld_sales_cmt = ld_sales_cmt;
	}
	public int getLd_promote() {
		return ld_promote;
	}
	public void setLd_promote(int ld_promote) {
		this.ld_promote = ld_promote;
	}
	public String getLd_promote_cmt() {
		return ld_promote_cmt;
	}
	public void setLd_promote_cmt(String ld_promote_cmt) {
		this.ld_promote_cmt = ld_promote_cmt;
	}
	public boolean isSelf_app_flg() {
		return self_app_flg;
	}
	public float getApp_score() {
		return app_score;
	}
	public void setApp_score(float app_score) {
		this.app_score = app_score;
	}
	public void setSelf_app_flg(boolean self_app_flg) {
		this.self_app_flg = self_app_flg;
	}
	public boolean isLeader_app_flg() {
		return leader_app_flg;
	}
	public void setLeader_app_flg(boolean leader_app_flg) {
		this.leader_app_flg = leader_app_flg;
	}
	public boolean isCustomer_app_flg() {
		return customer_app_flg;
	}
	public void setCustomer_app_flg(boolean customer_app_flg) {
		this.customer_app_flg = customer_app_flg;
	}
	public boolean isPerfomance_app_flg() {
		return perfomance_app_flg;
	}
	public boolean isAccept_flg() {
		return accept_flg;
	}
	public void setAccept_flg(boolean accept_flg) {
		this.accept_flg = accept_flg;
	}
	public void setPerfomance_app_flg(boolean perfomance_app_flg) {
		this.perfomance_app_flg = perfomance_app_flg;
	}
	public String getRec_create_id() {
		return rec_create_id;
	}
	public void setRec_create_id(String rec_create_id) {
		this.rec_create_id = rec_create_id;
	}
	public String getRec_create_date() {
		return rec_create_date;
	}
	public void setRec_create_date(String rec_create_date) {
		this.rec_create_date = rec_create_date;
	}
	public String getRec_update_id() {
		return rec_update_id;
	}
	public void setRec_update_id(String rec_update_id) {
		this.rec_update_id = rec_update_id;
	}
	public String getRec_update_date() {
		return rec_update_date;
	}
	public void setRec_update_date(String rec_update_date) {
		this.rec_update_date = rec_update_date;
	}
	public String getLogical_del_flg() {
		return logical_del_flg;
	}
	public void setLogical_del_flg(String logical_del_flg) {
		this.logical_del_flg = logical_del_flg;
	}
	public String getAppriaiser_name() {
		return appriaiser_name;
	}
	public void setAppriaiser_name(String appriaiser_name) {
		this.appriaiser_name = appriaiser_name;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}


	
}

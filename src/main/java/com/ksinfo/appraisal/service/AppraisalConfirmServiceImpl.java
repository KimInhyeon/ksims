package com.ksinfo.appraisal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalConfirmDao;
import com.ksinfo.appraisal.dto.AppraisalConfirmDto;

@Service("appraisalConfirmService")
public class AppraisalConfirmServiceImpl implements AppraisalConfirmService {
	@Inject
	AppraisalConfirmDao dao;
	
	@Override
	public List<AppraisalConfirmDto> getApprList() {
		List<AppraisalConfirmDto> list=dao.getApprList();
		list=scoreDto(list);
		return list;
	}

	@Override
	public int putAppraisal(AppraisalConfirmDto dto) {
		return dao.putAppraisal(dto);
	}
	
	private List<AppraisalConfirmDto> scoreDto (List<AppraisalConfirmDto> dto){
		for(int i=0;i<dto.size();i++) {
			String position_code=dto.get(i).getPosition_code();
			float ld_sum=dto.get(i).getLd_sum();
			float cust_sum=dto.get(i).getCust_sum();
			float sales_sum=dto.get(i).getSales_sum();
			
			float project_score=0;
			if(position_code.equals("24")) {
				project_score=(ld_sum*5.f)+(cust_sum*3.f)+(sales_sum*2.f);
			}else if(position_code.equals("23")) {
				project_score=(ld_sum*4.f)+(cust_sum*2.f)+(sales_sum*4.f);
			}else {
				project_score=(ld_sum*3.f)+(cust_sum*2.f)+(sales_sum*5.f);
			}
			project_score=Math.round(project_score)/10f;
			
			String grd="";
			float app_score=0;
			if(project_score>=90) {
				grd="S";
				app_score=3;
			}else if(project_score>=80) {
				grd="A";
				app_score=2;
			}else if(project_score>=70) {
				grd="B";
				app_score=1.5f;
			}else {
				grd="C";
				app_score=1;
			}
			
			dto.get(i).setApp_grade(grd);
			dto.get(i).setApp_score(app_score);
			dto.get(i).setProject_score(project_score);
		}
		return dto;
	}

}

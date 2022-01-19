package com.ksinfo.appraisal.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ksinfo.appraisal.dao.AppraisalScoreDao;
import com.ksinfo.appraisal.dto.AppraisalMgtDto;

@Service("appraisalScoreService")
public class AppraisalScoreServiceImpl implements AppraisalScoreService {

	@Inject
	AppraisalScoreDao dao;
	
	@Override
	public int countAllList() {
		// TODO Auto-generated method stub
		return dao.countAllList();
	}
	
	@Override
	public int countMyList(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return dao.countMyList(paraMap);
	}
	
	@Override
	public List<AppraisalMgtDto> getAllList(Map<String,Object> paraMap) {
		List<AppraisalMgtDto> dto=dao.getAllList(paraMap);
		dto=this.scoreDto(dto);
		return dto;
	}

	@Override
	public List<AppraisalMgtDto> getMyList(Map<String,Object> paraMap) {
		List<AppraisalMgtDto> dto= dao.getMyList(paraMap);
		dto=this.scoreDto(dto);
		return dto;
	}
	
	private List<AppraisalMgtDto> scoreDto (List<AppraisalMgtDto> dto) {
		for(int i=0;i<dto.size();i++) {
			String position_code=dto.get(i).getField_position_code();
			float ld_sum=dto.get(i).getLd_sum();
			float cust_sum=dto.get(i).getCust_sum();
			float sales_sum=dto.get(i).getSales_sum();
			
			float score=0;
			if(position_code.equals("24")) {
				ld_sum*=5.f;
				cust_sum*=3.f;
				sales_sum*=2.f;
			}else if(position_code.equals("23")) {
				ld_sum=ld_sum*4.f;
				cust_sum=cust_sum*2.f;
				sales_sum=sales_sum*4.f;
			}else {
				ld_sum*=3.f;
				cust_sum*=2.f;
				sales_sum*=5.f;
			}
			score=Math.round(ld_sum+cust_sum+sales_sum)/10.f;
			dto.get(i).setApp_score(score);
		}
		return dto;
	}

}

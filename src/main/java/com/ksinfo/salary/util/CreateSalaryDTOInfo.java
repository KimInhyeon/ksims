package com.ksinfo.salary.util;

import java.util.List;

import com.ksinfo.salary.dto.SalaryDto;

public class CreateSalaryDTOInfo {

	public Object[][] CreateArr(List<SalaryDto> sDTO, String CreateFomula){

		Object[][] sDTOArr = new Object[sDTO.size()][45];

		for(int x=0; x<sDTO.size(); x++) {
			for(int i=0; i<45; i++) {
				switch(i) {
				
					//�s��
					case 0 : sDTOArr[x][i] = 0;
					 continue;
				
					//���喼
					case 1 : sDTOArr[x][i] = "-";
							 continue;
					//�Ј��ԍ�
					case 2 : sDTOArr[x][i] = sDTO.get(x).getIdentificationNo();
							 continue;
					//�Ј���
					case 3 : sDTOArr[x][i] = sDTO.get(x).getEmployeeName();
					         continue;
					//�����x���z
					case 4 : if(!CreateFomula.equals("")) {
								sDTOArr[x][i] = 0;
							 }else if(CreateFomula.equals("")){
								sDTOArr[x][i] = sDTO.get(x).getTotalSalary()-sDTO.get(x).getTotalDeduction();
							 }
							 	
			         		 continue;
					//�J������
					case 5 : sDTOArr[x][i] = sDTO.get(x).getSetUpWorkingDays();
			         		 continue;
					//�o�Γ���
					case 6 : sDTOArr[x][i] = sDTO.get(x).getWorkingDays();
			         		 continue;
					//�L���x��
					case 7 : sDTOArr[x][i] = sDTO.get(x).getPaidVacation();
			         		 continue;
					//�c���x��
					case 8 : sDTOArr[x][i] = sDTO.get(x).getSpecialPaidVacation();
			         		 continue;
					//���Γ���
					case 9 : sDTOArr[x][i] = sDTO.get(x).getAbsentDays();
			         		 continue;
					//�x����
					case 10 : sDTOArr[x][i] = sDTO.get(x).getLateness();
			         		  continue;
					//���ԊO�J��
					case 11 : sDTOArr[x][i] = sDTO.get(x).getEarlyLeaveDays();
			         		  continue;
					//��{��
					case 12 : sDTOArr[x][i] = sDTO.get(x).getOvertime();
			         		  continue;
					//��E�蓖
					case 13 : sDTOArr[x][i] = sDTO.get(x).getBasePay();
			         		  continue;
					//���i�蓖
					case 14 : sDTOArr[x][i] = sDTO.get(x).getPositionPay();
			         		  continue;
					//���ԊO�蓖
					case 15 : sDTOArr[x][i] = sDTO.get(x).getCertificationExtraPay();
			         		  continue;
					//�ʋΎ蓖
					case 16 : sDTOArr[x][i] = sDTO.get(x).getFamilyExtraPay();
			         		  continue;
			         //�N�����Z
					case 17 : sDTOArr[x][i] = sDTO.get(x).getOvertimePay();
	         		  		  continue;
	  				//�Œ�c�Ǝ蓖
	  				case 18 : sDTOArr[x][i] = sDTO.get(x).getTransportationPay();
	  		         		  continue;  
	  		        //��ʔ�o��		  
	  				case 19 : sDTOArr[x][i] = sDTO.get(x).getEndYearPay();
	  						  continue;
	         		//���ʋ� 
					case 20 : sDTOArr[x][i] = sDTO.get(x).getFixedExtraWorkingPay();
	         		 		  continue;
					//���̑��蓖
					case 21 : sDTOArr[x][i] = sDTO.get(x).getTransportationFeeExtra();
					 		  continue;
					//�s�A�J�T��
					case 22 : sDTOArr[x][i] = sDTO.get(x).getIncentive();
					 	 	  continue;
					//���x���z
					case 23 : sDTOArr[x][i] = sDTO.get(x).getExtraPay();
					 		  continue;
					//�ېőΏۊz
					case 24 : sDTOArr[x][i] = sDTO.get(x).getAbsentDeducted();
					 		  continue;
					//���ی�
					case 25 : if(!CreateFomula.equals("")) {
								sDTOArr[x][i] = 0;
							  }else if(CreateFomula.equals("")){
								 sDTOArr[x][i] = sDTO.get(x).getTotalSalary();
							  }

					 	  	  continue;
					//���N�ی�
					case 26 : if(!CreateFomula.equals("")) {
								sDTOArr[x][i] = 0;
							  }else if(CreateFomula.equals("")){
								 sDTOArr[x][i] = sDTO.get(x).getTaxableProperty();
							  }
					
					 		  continue;
					//�����N�� 
					case 27 : sDTOArr[x][i] = sDTO.get(x).getCaringInsurance();
					 		  continue;
					//�ٗp�ی�
					case 28 : sDTOArr[x][i] = sDTO.get(x).getHealthInsurance();
					 		  continue;
					//�Љ�ی� 
					case 29 : sDTOArr[x][i] = sDTO.get(x).getPension();
					 		  continue;
					//������
					case 30 : sDTOArr[x][i] = sDTO.get(x).getEmployedInsurance();
					 		  continue;
					//�Z����
					case 31 : sDTOArr[x][i] = sDTO.get(x).getCompanyInsurance();
					 		  continue;
					//�����ی���
					case 32 : sDTOArr[x][i] = sDTO.get(x).getIncomeTax();
					 		  continue;
					//�ϗ���
					case 33 : sDTOArr[x][i] = sDTO.get(x).getCitizenTax();
					 		  continue;
					//��
					case 34 : sDTOArr[x][i] = "";
					 		  continue;
					//�� 
					case 35 : sDTOArr[x][i] = "";
					 continue;
					//�����ی���
					case 36 : sDTOArr[x][i] = sDTO.get(x).getLifeInsurance();
					 		  continue;
					//�ϗ���
					case 37 : sDTOArr[x][i] = sDTO.get(x).getAccumulationMoney();
					 		  continue;
					//�Б�T��
					case 38 : sDTOArr[x][i] = sDTO.get(x).getDormDeduction();
					 		  continue;
					//�ԍ�
					case 39 : sDTOArr[x][i] = sDTO.get(x).getDebtFinished();
					 		  continue;
					 
					 //�T���v
					case 40 : if(!CreateFomula.equals("")) {
								sDTOArr[x][i] = 0;
							  }else if(CreateFomula.equals("")){
								sDTOArr[x][i] = sDTO.get(x).getTotalDeduction();
							  }

			 	  	  		  continue;
					
					//��Б��̕ی����S���z�ݒ�
					//���N�ی�
					case 41 : sDTOArr[x][i] = sDTO.get(x).getHealthInsurance();
					 		  continue;
					//�����N��
					case 42 : sDTOArr[x][i] = sDTO.get(x).getPension();
					 		  continue;
					//�ٗp�ی�
					case 43 : sDTOArr[x][i] = sDTO.get(x).getEmployedInsurance();
					 		  continue;
					//�Љ�ی�
					case 44 : sDTOArr[x][i] = sDTO.get(x).getCompanyInsurance();
					 		  continue;		 
				}
			}
		}		
		return sDTOArr;
	}
}

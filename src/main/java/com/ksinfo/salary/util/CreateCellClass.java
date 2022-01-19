package com.ksinfo.salary.util;

import java.text.DecimalFormat;
import java.util.List;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.ksinfo.salary.dto.SalaryDto;


public class CreateCellClass {

	
		public Cell[][] CreateCell(List<SalaryDto> sDTO){

			Cell[][] cellArr = new Cell[sDTO.size()][25];
			DecimalFormat dF = new DecimalFormat("###,###,###");

			for(int x=0; x<sDTO.size(); x++) {
				for(int i=0; i<25; i++) {
					cellArr[x][i] = new Cell(1,1);
					switch(i) {
						//社員番号
						case 0 : cellArr[x][i].add(new Paragraph(sDTO.get(x).getIdentificationNo()));
								 continue;
						//社員名
						case 1 : cellArr[x][i].add(new Paragraph(sDTO.get(x).getEmployeeName()));
								 continue;
						//差引支給額
						case 2 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getSalary())));
						         continue;
						//基本給
						case 3 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getBasePay())));
				         		 continue;
						//役職手当
						case 4 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getPositionPay())));
				         		 continue;
						//資格手当
						case 5 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getCertificationExtraPay())));
				         		 continue;
						//住宅手当
						case 6 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getFamilyExtraPay())));
				         		 continue;
				       //時間外手当
						case 7 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getOvertimePay())));
								continue;
				         
						//通勤手当
						case 8 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getTransportationPay())));
								 continue;
						//年末精算
						case 9 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getEndYearPay())));
								continue;		 
						//固定残業手当
						case 10 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getFixedExtraWorkingPay())));
				         		 continue;
				        //交通費・経費
						case 11:  cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getExtraCost())));
		         				continue; 
						//成果給
						case 12 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getIncentive())));
				         		 continue;
				        //その他手当
						case 13 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getExtraPay())));
				         		  continue;
				        //>総支給費
						case 14 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getTotalSalary())));
				         		  continue;
				        //課税対象額
						case 15 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getTaxableProperty())));
				         		  continue;	
				        //健康保険
						case 16 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getHealthInsurance())));
								continue;
						//源泉徴収額（厚生年金）
						case 17 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getPension())));
				         		  continue;
				        //雇用保険
						case 18 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getEmployedInsurance())));
				         		  continue;
				        //社会保険
						case 19 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getCompanyInsurance())));
				         		  continue; 
				        //源泉徴収額（所得税）
						case 20 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getIncomeTax())));
				         		  continue;
						//源泉徴収額（住民税）
						case 21 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getCitizenTax())));
				         		  continue;
				        //社宅控除
						case 22 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getDormDeduction())));
				         		  continue;
				        //返済		  
						case 23 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getDebtFinished())));
									continue;		  
						//控除計
						case 24 : cellArr[x][i].add(new Paragraph(dF.format(sDTO.get(x).getTotalDeduction())));
				         		  continue;
				       
					}
				}
			}		
			return cellArr;
		}
		
		public Cell[][] CreateHearderCell(List<SalaryDto> sDTO){
			Cell[][] cellArr = new Cell[sDTO.size()][25];
			
//			for(int x=0; x<sDTO.size(); x++) {
				for(int i=0; i<25; i++) {
					cellArr[0][i] = new Cell(2,1);
				}
//			}
			
			return cellArr;
		}

}

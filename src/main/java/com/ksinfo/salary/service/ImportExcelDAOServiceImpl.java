package com.ksinfo.salary.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksinfo.common.exception.ExcelReaderException;
import com.ksinfo.common.util.ObjectUtil;
import com.ksinfo.salary.controller.ExcelReader;
import com.ksinfo.salary.dao.FileControlDAO;
import com.ksinfo.salary.dao.FileControlDAOImpl;
import com.ksinfo.salary.dto.IncomeTaxDto;
import com.ksinfo.salary.dto.InsuranceDto;

@Service
public class ImportExcelDAOServiceImpl implements ImportExcelDAOService {
	private static final String excelFormError = "KS_IMSYS_EXLMSG_002_JV";
	private static final String fileFormatError = "KS_IMSYS_EXLMSG_001_JV";
	private static final String notMonthlyError = "KS_IMSYS_EXLMSG_003_JV";
	
	@Autowired
	FileControlDAO fcDao = new FileControlDAOImpl();
	
	public void GetExcelImport(String fileUploadPath, MultipartFile multi, String file_type) {
			IncomeTaxDto paramClass;
			InsuranceDto paramClass2;
		
			// TODO Auto-generated method stub
			List<IncomeTaxDto> list = new ArrayList<IncomeTaxDto>();
			List<InsuranceDto> list2 = new ArrayList<InsuranceDto>();

			List<IncomeTaxDto> check = new ArrayList<IncomeTaxDto>();
			List<InsuranceDto> check2 = new ArrayList<InsuranceDto>();

			FileInputStream fis = null;
			HSSFWorkbook workbook = null;
			XSSFWorkbook workbook2 = null;

			String fileName = multi.getOriginalFilename();
			String file_ext = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
			
			if (file_type.equals("income_tax")&&file_ext.equals("xls")) {
				// (2019.11.12 耶ヤ슁�겑) (.xls)壤℡폀�겗�깢�궊�궎�꺂�겓�겇�걚�겍瓦썲뒥
				try {
					fis = new FileInputStream(fileUploadPath + "/" + fileName);
					workbook = new HSSFWorkbook(fis);
					HSSFSheet curSheet;
					HSSFRow curRow;
					HSSFCell curCell;

					/* 礪섇폀�겗訝��빁�쎅�궥�꺖�깉 */
					curSheet = workbook.getSheetAt(0);

					// ***************************************************************
					int rowIndex = 0;
					int getRowIndex = 0;
					for (getRowIndex = 0; getRowIndex < curSheet.getPhysicalNumberOfRows(); getRowIndex++) {

						int cnt_Yen = 0;

						if (getRowIndex != 0) {
							curRow = curSheet.getRow(getRowIndex);
							for (int cellIndex = 1; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);

								
									String value = "";
									// if(curCell != null && curCell.getStringCellValue()!=null) {
									if (curCell != null && curCell.getCellType().equals(CellType.STRING)) {
										value = curCell.getStringCellValue();
										if (value.equals("円")) {
											cnt_Yen++;
										}
									}

									if (cnt_Yen == 10) {
										break;
									}
									
									if(getRowIndex==2&&cellIndex==1) { //その月の社会保
										String dayFile=curCell.getStringCellValue();
										if(dayFile.indexOf("その月の社会保")==-1) {
											throw new ExcelReaderException(notMonthlyError);
										}
									}
							}
						}

						if (cnt_Yen == 10) {
							break;
						}

					}
					getRowIndex+=3;
					if(getRowIndex!=9) {
						throw new ExcelReaderException(excelFormError);
					}else {
						ExcelReader.addExcelUploadProgress(8);
					}
					// ***************************************************************

					for (rowIndex = getRowIndex; rowIndex < 303; rowIndex++) {
						if (rowIndex != 0) {
							curRow = curSheet.getRow(rowIndex);
							paramClass = new IncomeTaxDto();
							Integer value;
							
							curCell = curRow.getCell(0);
							if(curCell!=null&curCell.getCellType().equals(CellType.BLANK)) {
								if((rowIndex-2)%6==0) {
									continue;
								}else {
									throw new ExcelReaderException(excelFormError);
								}
							}

							for (int cellIndex = 1; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);

								if (curCell != null && curCell.getCellType().equals(CellType.NUMERIC)) {
									value = (int) curCell.getNumericCellValue();
									
									switch (cellIndex) {
									case 1:
										if(rowIndex>10) {
											int lastLessIdx=list.size()-1;
											if(list.get(lastLessIdx).getLess()!=value) {
												throw new ExcelReaderException(excelFormError);
											}
										}
										paramClass.setOver(value);
										break;

									case 2:
										paramClass.setLess(value);
										break;

									case 3:
										paramClass.setSupportFamilyZero(value);
										break;

									case 4:
										paramClass.setSupportFamilyOne(value);
										break;

									case 5:
										paramClass.setSupportFamilyTwo(value);
										break;

									case 6:
										paramClass.setSupportFamilyThree(value);
										break;

									case 7:
										paramClass.setSupportFamilyFour(value);
										break;

									case 8:
										paramClass.setSupportFamilyFive(value);
										break;

									case 9:
										paramClass.setSupportFamilySix(value);
										break;

									case 10:
										paramClass.setSupportFamilySeven(value);
										break;

									case 11:
										paramClass.setExtra(value);
										break;

									default:
										break;
									}//switch
								
								}else{//if cell notNull& is Numeric
									throw new ExcelReaderException(excelFormError);
								}
							}//for cell
								list.add(paramClass);					
						}//if rowIndex not 0
						if(list.size()%6==0) {
							ExcelReader.addExcelUploadProgress(1);
						}
					}

					/* DB�쇉�뙯 */
					try {
						check = fcDao.incomeTaxChkList();
						if (ObjectUtil.isEmpty(check)) {
							for (IncomeTaxDto vo : list) {
								fcDao.incomeTaxInsert(vo);
								if(rowIndex%6==0) {
									ExcelReader.addExcelUploadProgress(1);
								}
								rowIndex++;
							}
						} else {
							fcDao.incomeTaxDelete();
							for (IncomeTaxDto vo : list) {
								fcDao.incomeTaxInsert(vo);
								if(rowIndex%6==0) {
									ExcelReader.addExcelUploadProgress(1);
								}
								rowIndex++;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (workbook != null)
							workbook.close();
						if (fis != null)
							fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} // income
			else if (file_type.equals("insurance")&&file_ext.equals("xlsx")) {
				try {

					fis = new FileInputStream(fileUploadPath + "/" + fileName);
					workbook2 = new XSSFWorkbook(fis);
					XSSFSheet curSheet;
					XSSFRow curRow;
					XSSFCell curCell;

					int rowIndex;
					curSheet = workbook2.getSheet("東京"); // 건보료, 후생연금 보험료액수 표
					if(curSheet==null) {
						throw new ExcelReaderException(excelFormError);
					}
					int getRowIdx=0;
					int getCelIdx=0;
					boolean flg=false;
					for(getRowIdx=0;getRowIdx < curSheet.getPhysicalNumberOfRows(); getRowIdx++) {
						curRow = curSheet.getRow(getRowIdx);
						if (curRow == null)
							break;
						
						for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells() + 2; cellIndex++) {
							curCell = curRow.getCell(cellIndex);
							if (curCell != null && curCell.getCellType().equals(CellType.STRING)) {
								String contain=curCell.getStringCellValue();
								if(contain!=null) {	
									if(contain.equals("円以上")) {
										curCell=curRow.getCell(cellIndex+2);
										contain=curCell.getStringCellValue();
										if(contain.equals("円未満")) {
											getCelIdx=cellIndex;
											flg=true;
											break;
										}
									}
								}
							}
						}
						
						if(flg) {
							break;
						}
						
					}
					ExcelReader.addExcelUploadProgress(1);
					getRowIdx++;
					if(getRowIdx!=11||getCelIdx!=2) {
						throw new ExcelReaderException(excelFormError);
					}
					
					
					for (rowIndex = 11; rowIndex < 61; rowIndex++) {

						if (rowIndex != 0) {
							curRow = curSheet.getRow(rowIndex);
							paramClass2 = new InsuranceDto();
							Double value;

							if (curRow == null)
								break;

							for (int cellIndex = 2; cellIndex < curRow.getPhysicalNumberOfCells() + 2; cellIndex++) {
								curCell = curRow.getCell(cellIndex);

								if (curCell != null) {
									
									switch (cellIndex) {
									case 2:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											if(rowIndex==11) {
												value=0.;
											}else {
												throw new ExcelReaderException(excelFormError);
											}
										}
										if(rowIndex>11) {
											int lastLessIdx=list2.size()-1;
											if(!list2.get(lastLessIdx).getLess().equals(value)) {
												throw new ExcelReaderException(excelFormError);
											}
											
										}
											paramClass2.setOver(value);
										break;

									case 3://~
										break;

									case 4:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											if(rowIndex==60) {
												value=9999999.;
											}else {
												throw new ExcelReaderException(excelFormError);
											}
										}
										if(rowIndex!=60){
											if(paramClass2.getOver()>value)
											throw new ExcelReaderException(excelFormError);
										}
										paramClass2.setLess(value);
										break;

									case 5:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											throw new ExcelReaderException(excelFormError);
										}
										paramClass2.setHealthAll(value);
										break;

									case 6:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											throw new ExcelReaderException(excelFormError);
										}
										paramClass2.setHealthHalf(value);
										break;

									case 7:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											throw new ExcelReaderException(excelFormError);
										}
									//	paramClass2.setCareAll(value));
										paramClass2.setHealthCareAll(value);
										break;

									case 8:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											throw new ExcelReaderException(excelFormError);
										}
									//	paramClass2.setCareHalf(value));
										paramClass2.setHealthCareHalf(value);
										break;

									case 9:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											if(rowIndex>=46||rowIndex<=13) {
												value=0.;
											}else {
												throw new ExcelReaderException(excelFormError);
											}
										}
									//	paramClass2.setSumAll(value));
										paramClass2.setPensionAll(value);
										break;

									case 10:
										if(curCell.getCellType().equals(CellType.NUMERIC)) {
											value=curCell.getNumericCellValue();
										}else {
											if(rowIndex>=46||rowIndex<=13) {
												value=0.;
											}else {
												throw new ExcelReaderException(excelFormError);
											}
										}
									//	paramClass2.setSumHalf(value));
										paramClass2.setPensionHalf(value);
										break;

									default:
										break;
									}
								}
							}
						
							list2.add(paramClass2);
						}
							ExcelReader.addExcelUploadProgress(1);
					}
					/* DB�쇉�뙯 */
					try {
						check2 = fcDao.insuranceChkList();
						if (ObjectUtil.isEmpty(check2)) {
							for (InsuranceDto vo : list2) {
								fcDao.insuranceInsert(vo);
								ExcelReader.addExcelUploadProgress(1);
							}
						} else {
							fcDao.insuranceDelete();
							for (InsuranceDto vo : list2) {
								fcDao.insuranceInsert(vo);
								ExcelReader.addExcelUploadProgress(1);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (workbook2 != null)
							workbook2.close();
						if (fis != null)
							fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else { //間違えたファイル
				throw new ExcelReaderException(fileFormatError);
				
			}
		
	}
}

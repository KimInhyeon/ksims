package com.ksinfo.salary.util;

import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.ksinfo.salary.dto.SalaryDto;


public class CreatePdfFormat {

		public void CreatePDFBaseInf(List<SalaryDto> sDTO,Map<String,Object> periodMap) throws java.io.IOException {
			String dest = periodMap.get("dest").toString();
			String fileName = periodMap.get("fileName").toString();
			String[] headStr= (String[]) periodMap.get("headStr");

			int ArrSize = sDTO.size();
			int pageNum = 0;
			int linePerPage = 40;
			int pageCnt = 0;
			int pageTotalNum = 0;
		    int cellSize=headStr.length;
			Cell[][] cellArr = new Cell[ArrSize][cellSize];
			
			PdfWriter writer = new PdfWriter(dest +"/" + fileName);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document doc = new Document(pdfDoc, PageSize.A3.rotate());
			PdfFont font = PdfFontFactory.createFont("c:\\windows\\fonts\\msgothic.ttc,0", "Identity-H");
			Border border = new SolidBorder(0.5f);
			border.setColor(new DeviceRgb(90, 90, 90));
			float divideCell=(PageSize.A3.getHeight()*0.98f)/25f;
			float [] pointColumnWidths = new float[cellSize];
			for(int i=0;i<cellSize;i++) {
				pointColumnWidths[i]=divideCell;
			}
			pointColumnWidths[0]=divideCell*0.8f;
			pointColumnWidths[1]=divideCell*1.2f;

			Table table=new Table(pointColumnWidths);
			table.setMarginLeft(-PageSize.A3.getHeight()*0.02f);
			table.setWidth(PageSize.A3.getHeight()*0.98f);
	    	table.setFont(font);
	    	table.setFontSize(8);
	    	
		    CreateCellClass cc = new CreateCellClass();
		    //pageTotalNum = pdfDoc.getNumberOfPages();
		    if(sDTO.size()%linePerPage==0) {
		    	pageTotalNum = ArrSize/linePerPage;
		    }else {
		    	pageTotalNum = ArrSize/linePerPage+1;
		    }
		   
		    cellArr = cc.CreateCell(sDTO);
		    for(int i=0; i< ArrSize ; i++) {	    	
			    //Head Part
		    	if(pageNum==0) {
		    		Cell header=new Cell(1,25);
		    		header.setBorder(Border.NO_BORDER);
		    		header.add(new Paragraph(periodMap.get("year")+"年"+periodMap.get("month")+"月　～　"+periodMap.get("year2")+"年"+periodMap.get("month2")+"月"));
		    		table.addHeaderCell(header);
				    for(int y=0; y<cellSize; y++) {
				    	
				    	 Cell cell = new Cell();
				    	 cell.add(new Paragraph(headStr[y]));
				    	 cell.setBorder(border).setBackgroundColor(new DeviceRgb(233, 236, 239)).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM);
				    	 cell.setHeight(28).setBorderBottom(new SolidBorder(border.getColor(),1.2f));
				    	 table.addCell(cell);
				    	}
		    	}
		    	
		    	//	body Part
				pageNum= pageNum+1;
				
				for(int y=0; y<cellSize; y++) {
		    		if(y<2) {
		    			cellArr[i][y].setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
		    		}else {
		    			cellArr[i][y].setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE);
		    		}
		    		cellArr[i][y].setBorder(border);
		    		if(i%2==0) {
		    			table.addCell(cellArr[i][y].setBackgroundColor(new DeviceRgb(242, 242, 242)));
		    		}else {
		    			table.addCell(cellArr[i][y]);
		    		}
		    	} 

				//next page Proc
		    	if(pageNum % linePerPage ==0 && i!=0 && i< ArrSize -1 ) {
		    		
		    		pageCnt = pageCnt +1;
		    		
		            doc.showTextAligned(new Paragraph(String.format("Page %s of %s", pageCnt, pageTotalNum)),
		                    580, 40, pageCnt, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);

		    		doc.add(table);
		    		table = null;
		    		
		    		table = new Table(pointColumnWidths);
					table.setMarginLeft(-PageSize.A3.getHeight()*0.02f);
					table.setWidth(PageSize.A3.getHeight()*0.98f);
			    	table.setFont(font);
			    	table.setFontSize(8);
			    	
		    		doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
		    		
		    		pageNum=0;
		    	}
		    }
		    
		    pageCnt = pageCnt +1;
		    //最後のページのページ数を書込む
            doc.showTextAligned(new Paragraph(String.format("Page %s of %s", pageCnt, pageTotalNum)),
                    580, 40, pageCnt, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
			
            doc.add(table);
			
			doc.close();
			writer.close();
		}
}

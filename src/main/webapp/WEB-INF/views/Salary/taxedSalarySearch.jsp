<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<title>社員給料照会</title>
	<script>
		$(document).ready(function() {
			$('#salList').DataTable({
				 	scrollY:  false,
			        scrollX:        "2800px",
			        scrollCollapse: true,
			        paging:         false,
			        filter: false,
			        info: false,
			        sort:false,
			        fixedColumns:   {
			            leftColumns: 3,
			            rightColumns: 0,
			            heightMatch: "auto",
				},
			});
			
			$('.DTFC_ScrollWrapper').css({"height":"100%"});
			$('.DTFC_LeftBodyWrapper').css({"overflow":"visible"});
			$('.DTFC_LeftBodyLiner').css({"overflow":"visible","width":"100%"});
			
			$(window).resize(function() {
				$('#salList').css({"width":"200%"});
				var table = $('#salList').DataTable();
				table.draw();
				table.fixedColumns().update();
				$('.DTFC_LeftBodyWrapper').css({"overflow":"visible"});
				$('.DTFC_LeftBodyLiner').css({"overflow":"visible","width":"100%"});
				
			});
		});
	
		function dateCheck(){
			
			var rangeStart=$('#rangestart > div > input').val();
			var rangeEnd=$('#rangeend > div > input').val();

			var InquiryYear = rangeStart.slice(0,4);
			var InquiryYear2 = rangeEnd.slice(0,4);
			var Inquirymonth = rangeStart.slice(-2);
			var Inquirymonth2 = rangeEnd.slice(-2);
			
			var StartDate = new Date(InquiryYear,Inquirymonth);
			var EndDate = new Date(InquiryYear2,Inquirymonth2);
			
			if(StartDate>EndDate){
				alert('逆順の検索は不可能です。');
				return false;
			}else{
				return "InquiryYear=" +InquiryYear+"&InquiryYear2=" +InquiryYear2 +"&InquiryMonth=" +Inquirymonth+"&InquiryMonth2=" +Inquirymonth2;
			}
		}
	
		function salaryInquiry(){
			var qry=dateCheck();
			if(!qry){
				return;
			}
			location.href = "taxedSalarySearch?"+qry+"&curPage=1&param=1";
		}
		function PrintPdfExcute(){
			var qry=dateCheck();
			if(!qry){
				return;
			}
			location.href="ExportPDFFile?"+qry;
			
		}
		function excelLoad(){
			var excelApp = new ActiveXObject("WScript.Shell");
			var runProgram = "C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE";
			
			var retVal = excelApp.Run("C:\\WINDOWS\\system32\\calc.exe",1,true);			
		}
	</script>
	<style>
		h1 {  font-family: "Arial", "メイリオ";  position: relative;  color: #6495ed;  line-height: 1.4;  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));}
	
		.table td{vertical-align:middle; width:4%; text-align:right;}
		.DTFC_Cloned{overflow:visible;}
		.DTFC_Cloned td{width:33%; text-align:center;}
	</style>
</head>
<body>
	<div class="content_margin" style="min-width:600px">
		<form class="ui form">
			<h1><i class="caret square left icon"></i>給料出力</h1>
			
			<div class="ui grid">
				<div class="column">
					<div class="four fields" style="width:60%">
						<div class="field">
							<label>給料計算開始</label>
							<div class="ui calendar" id="rangestart">
								<div class="ui input">
									<input type="month" placeholder="YYYY-MM" pattern="[0-9]{4}-[0-9]{2}" value="${year}-${month}" autofocus="autofocus">
								</div>
							</div>
						</div>
						<div class="field">
							<label>給料計算終了</label>
							<div class="ui calendar" id="rangeend">
								<div class="ui input">
									<input type="month" placeholder="YYYY-MM" pattern="[0-9]{4}-[0-9]{2}" value="${year2}-${month2}">
								</div>
							</div>
						</div>
						<div class="field">
							<div class="ui blue button" onClick="salaryInquiry()" style="margin-top:23px">照合</div>
						</div>
						<div class="field">
							<div class="ui red button" onClick="PrintPdfExcute()" style="margin-top:23px">
								<i class="file pdf outline icon"></i>PDF帳票印刷
							</div>
						</div>
			<!-- 			<div class="field">
							<div class="ui blue button" onClick="excelLoad()" style="margin-top:23px">
								<i class="file pdf outline icon"></i>Excel
							</div>
						</div>   -->
					</div>
				</div>
			</div>
			
			<div class="ui grid">
				<div class="column" style="width:100%;">
					<table class="table  table-striped table-light table-bordered " id="salList">
						<thead class="table-info">
							<tr align="center">
								<th scope="col" rowspan="2" >社員番号</th>
								<th scope="col" rowspan="2" >社員名</th>
								<th scope="col" rowspan="2">差引支給額</th>
								<th  scope="col" colspan="13">支給</th>
								<th scope="col" colspan="9" style="text-align: center;">控除</th>
							</tr>
							<tr align="center">
								<!-- 支給 -->
								<th scope="col" >基本給</th>
								<th scope="col" >役職手当</th>
								<th scope="col">資格手当</th>
								<th scope="col" >住宅手当</th>
								<th scope="col" >時間外手当</th>
								<th scope="col" >通勤手当</th>
								<th scope="col" >年末精算</th>
								<th scope="col">固定残業<br>手当</th>
								<th scope="col">交通費<br>・経費</th>
								<th scope="col">成果給</th>
								<th scope="col">その他手当</th>
								<th scope="col">総支給費</th>
								<th scope="col">課税対象額 </th>
								<!-- 控除 -->
								<th scope="col">健康保険料</th>
								<th scope="col">厚生年金</th>
								<th scope="col">雇用保険</th>
								<th scope="col">社会保険</th>
								<th scope="col">所得税</th>
								<th scope="col">住民税</th>
								<th scope="col">社宅控除</th>
								<th scope="col">返済</th>
								<th scope="col">控除計</th>
							</tr>
						</thead>
						<tbody>
							<c:set value="1" var="i"/>
							<c:forEach items="${sDTO}" var="sDTO">
								<c:if test="${i%2==0}">
									<tr>
								</c:if>
								<c:if test="${i%2!=0}">
									<tr style="background:rgb(242, 242, 242);">	
								</c:if>
									<td>${sDTO.identificationNo}</td>
									<td style="text-align:left;">${sDTO.employeeName}</td>
									<td style="text-align: end;"><fmt:formatNumber value="${sDTO.salary}" pattern="#,###,###" /></td>
									<!-- 支給 -->
									<td><fmt:formatNumber value="${sDTO.basePay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.positionPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.certificationExtraPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.familyExtraPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.overtimePay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.transportationPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.endYearPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.fixedExtraWorkingPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.extraCost}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.incentive}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.extraPay}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.totalSalary}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.taxableProperty}" pattern="#,###,###" /></td>
									<!-- 控除 -->
									<td><fmt:formatNumber value="${sDTO.healthInsurance}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.pension}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.employedInsurance}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.companyInsurance}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.incomeTax}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.citizenTax}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.dormDeduction}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.debtFinished}" pattern="#,###,###" /></td>
									<td><fmt:formatNumber value="${sDTO.totalDeduction}" pattern="#,###,###" /></td>
								</tr>
								<c:set value="${i+1}" var="i"/>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<c:if test="${page.curBlock == 0}">
			<div align="center" style="margin-top:10px">
				<div class="ui pagination menu">	
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="taxedSalarySearch?InquiryYear=${year}&InquiryYear2=${year2}&InquiryMonth=${month}&InquiryMonth2=${month2}&curPage=1&searchCon=${searchCon}&param=1"><i class="angle double left icon"></i></a>
						<a class="item" href="taxedSalarySearch?InquiryYear=${year}&InquiryYear2=${year2}&InquiryMonth=${month}&InquiryMonth2=${month2}&curPage=${page.prevPage}&searchCon=${searchCon}&param=1"><i class="angle left icon"></i></a>
					</c:if>
					<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
						<c:choose>
							<c:when test="${pageNum == page.curPage}">
								<div class="active item">${pageNum}</div>
							</c:when>
							<c:otherwise>
								<a class="item" href="taxedSalarySearch?InquiryYear=${year}&InquiryYear2=${year2}&InquiryMonth=${month}&InquiryMonth2=${month2}&curPage=${pageNum}&param=1">
									${pageNum}
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="taxedSalarySearch?InquiryYear=${year}&InquiryYear2=${year2}&InquiryMonth=${month}&InquiryMonth2=${month2}&curPage=${page.nextPage}&searchCon=${searchCon}&param=1"><i class="angle right icon"></i></a>
						<a class="item" href="taxedSalarySearch?InquiryYear=${year}&InquiryYear2=${year2}&InquiryMonth=${month}&InquiryMonth2=${month2}&curPage=${page.totPage}&searchCon=${searchCon}&param=1"><i class="angle double right icon"></i></a>
					</c:if>
				</div>
			</div>
			</c:if><br/>
			<div class="ui">
				<div class="ui button left floated" onclick="javascript:history.go(-1)">
					<i class="left chevron icon"></i>戻る
				</div>
			</div>
		</form>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>過去給料照会</title>
<style>
	.table td{cursor:pointer; vertical-align:middle; width:4%; text-align:center;}
	.DTFC_Cloned{overflow:visible;}
	.DTFC_Cloned td{width:33%; text-align:center;}
</style>
</head>
<c:if test="${sessionScope.adminFlg eq 01 }">
	<script>
	$(document).ready(function () {
		$('.ui.dropdown').dropdown();
		$('.ui.dropdown').dropdown({
		    "clearable": true
		});
	});
	
	function dateCheck(){
		var	pastStartDate = $('input[name=pastStartDate]').val();
		var pastEndDate = $('input[name=pastEndDate]').val();
		var nck=dateNullChk();
		var emp_id = search.emp_id.value;
		
		if(pastStartDate == "" && pastEndDate != ""){
			alert('逆順の検索は不可能です。');
			return false;
		}else if(pastStartDate == "" && pastEndDate == "" && emp_id ==""){
			alert('照会内容を選択してください。');
			return false;
		}else if(nck){
			location.href="payslipSearch?pastStartDate="+pastStartDate+"&pastEndDate="+pastEndDate+"&emp_id="+emp_id;
		}
	}
	
	function admSendList(year,month) {
		var	pastStartDate = $('input[name=pastStartDate]').val();
		var pastEndDate = $('input[name=pastEndDate]').val();
		var emp_id = search.emp_id.value;
		
		location.href="MoveTosalaryMain?curPage=1&year="+year+"&month="+month
				+"&pastStartDate="+pastStartDate+"&pastEndDate="+pastEndDate+"&emp_id="+emp_id;
	}
	
	function admSendOne(year,month) {
		var emp_id = search.emp_id.value;
		
		$('#identificationNo').val(emp_id);
		userSend(year,month);
	}
	
	</script>
</c:if>

<c:if test="${sessionScope.adminFlg ne 01 }">
	<script>
	function dateCheck(){
		var	pastStartDate = $('input[name=pastStartDate]').val();
		var pastEndDate = $('input[name=pastEndDate]').val();
		var nck=dateNullChk();
		
		if(pastStartDate == "" && pastEndDate != ""){
			alert('逆順の検索は不可能です。');
			return false;
		}else if(pastStartDate == "" && pastEndDate == ""){
			alert('照会内容を選択してください。');
			return false;
		}else if(nck){
			location.href="payslipSearch?pastStartDate="+pastStartDate+"&pastEndDate="+pastEndDate;
		}
	}
	</script>
</c:if>

<script type="text/javascript">
	
function dateNullChk() {
	
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
		return true;
	}
}

function userSend(year,month) {
	var year_month = year+"-"+month+"-01";
	$('input[name=work_year_month]').val(year_month);
	search.action="SalaryPayroll_View_Detail"
	search.submit();
}

function reset() {
	location.href='payslipSearch';
}
</script>
<style>
h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}
</style>
<body>
	<div class="content_margin_small" >
		<form id="search" name="search" method="post" class="ui form">
			<h1><i class="caret square left icon"></i>過去給料照会</h1>
			<div class="ui grid">
				<div class="column">
					<div class="four fields" style="width:100%">
						<div class="field">
							<label>給料照会開始</label>
							<div class="ui calendar" id="rangestart">
								<div class="ui input">
									<input type="month" name="pastStartDate" placeholder="YYYY-MM" pattern="[0-9]{4}-[0-9]{2}" value="${year}-${month}" autofocus="autofocus">
								</div>
							</div>
						</div>
						<div class="field">
							<label>給料照会終了</label>
							<div class="ui calendar" id="rangeend">
								<div class="ui input">
									<input type="month" name="pastEndDate" placeholder="YYYY-MM" pattern="[0-9]{4}-[0-9]{2}" value="${year2}-${month2}">
								</div>
							</div>
						</div>
						
						<c:if test="${sessionScope.adminFlg eq 01 }">
							<div class="field">
							<label>社員名</label>
							<select class="ui fluid search dropdown" name="emp_id" id="emp_id">
					            <option value="">社員名</option>
				            <c:forEach items="${EmpDropDown}" var="EmpDropDown">
					            <option value="${EmpDropDown.empId }"
					            <c:if test="${EmpDropDown.empId eq emp_id}">selected</c:if>>${EmpDropDown.empName }(${EmpDropDown.empId })</option>
				            </c:forEach>
					        </select>
							</div>
						</c:if>
						
						<div class="field">
							<div class="ui blue button" onClick="dateCheck()" style="margin-top:27px">照会</div>
							<div class="ui yellow button" onClick="reset()" style="margin-top:27px">クリア</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ui grid">
				<div class="column" style="width:100%; ">
					<table class="table  table-striped table-hover table-bordered table-sm" id="salList">
						<thead class="table-info">
							<tr align="center">
								<th scope="col" rowspan="2" >過去給料</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${!empty year_month }">
						<input type="hidden" name="work_year_month">
						<input type="hidden" id="identificationNo" name="identificationNo" value="${sessionScope.sid }">
								
							<c:forEach items="${year_month}" var="year_month">
								<tr>
									<c:if test="${sessionScope.adminFlg eq 01 }">
										<c:if test="${empty emp_id}">
											<td onclick="javascript:admSendList('${year_month.year }','${year_month.month }')">${year_month.year }年${year_month.month }月</td>
										</c:if>
										<c:if test="${!empty emp_id}">
											<td onclick="javascript:admSendOne('${year_month.year }','${year_month.month }')">${year_month.year }年${year_month.month }月</td>
										</c:if>
									</c:if>
									<c:if test="${sessionScope.adminFlg ne 01 }">
										<td onclick="javascript:userSend('${year_month.year }','${year_month.month }')">${year_month.year }年${year_month.month }月</td>
									</c:if>
								</tr>	
							</c:forEach>
						</c:if>
						<c:if test="${empty year_month }">
							<tr>
								<td>データが存在しません。</td>
							</tr>	
						</c:if>
						
						</tbody>
					</table>
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>

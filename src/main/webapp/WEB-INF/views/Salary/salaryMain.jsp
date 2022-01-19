<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KS情報システム</title>

<style type="text/css">
td{cursor: pointer;}
</style>

<script>
function month_page() {
	var year_month = $('select[name="work_year_month"]').val().split("-");
	
	var	pastStartDate = EmpSalaryList.pastStartDate.value;
	var pastEndDate = EmpSalaryList.pastEndDate.value;
	var empId = EmpSalaryList.emp_id.value;
	
	var view = "MoveTosalaryMain?curPage=1&year="+year_month[0]+"&month="+year_month[1];
	if(pastStartDate !="" || pastEndDate!= "" || empId != ""){
		view +="&pastStartDate="+pastStartDate+"&pastEndDate="+pastEndDate+"&emp_id="+empId; 
	}
	location.href= view;
}

function salaryConfirm(){
	location.href = "salaryConfirm"
}

function submitPage(id,flg) {
	var test = $('select[name="work_year_month"]').val();
	
	$('#identificationNo').val(id);
	$('#compFlg').val(flg);
//	alert(flg);
	
	if(flg == 't'){
		EmpSalaryList.method="post";
		EmpSalaryList.action="SalaryPayroll_View_Detail";
	}else{
		EmpSalaryList.method="post";
		EmpSalaryList.action="SalaryPayrollRegist";
	}

	
	$('select[name="work_year_month"]').removeAttr("disabled");
	EmpSalaryList.submit();
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

</head>
<body>
<div class="content_margin_small">
<form class="ui form" id="EmpSalaryList" name="EmpSalaryList" method="get">
    <h1><i class="caret square left icon"></i>給料照会</h1>
	<div class="ui grid">
		<div class="column">  
		<select name="work_year_month" class="ui selection dropdown" style="width:10%; height:45px!important; margin-right: 20px;" onchange="month_page()">
				<c:if test="${empty year_month}">
					<option>なし</option>
				</c:if>
				<c:if test="${!empty year_month}">
					<c:forEach items="${year_month}" var="year_month" varStatus="status">
						<option value="${year_month.year}-${year_month.month}-01"
							<c:if test="${year_month.year eq year && year_month.month eq month}"> selected </c:if>
						>${year_month.year}年${year_month.month}月</option>
					</c:forEach>
				</c:if>
		</select>
		<div class="ui blue button"  onClick="location.href='payslipSearch'" >過去給料照会</div>
		
		<table class="table table-striped table-hover table-bordered table-sm" style="margin-top: 10px">
			<thead class="table-info" align="center">
				<tr>
					<th style="width: 50%">社員名</th>
					<th>総支給額</th>
					<th> </th>
				</tr>
			</thead>
			<tbody id="GradeTable" align="center">
<%-- 			<c:forEach items="${list}" var="list"> --%>

			<c:forEach items="${list }" var="list">
				<tr onclick="submitPage(${list.empId},'${list.compFlg}')" >
					<td style="text-align:left;">${list.empName}　(${list.empId})</td>
						<c:if test="${!empty list.salary}">
							<td style="text-align: end;">
								<fmt:formatNumber value="${list.salary}"/> 円
							</td>
						</c:if>
						<c:if test="${empty list.salary}">
							<td style="text-align: center;">
								未支給
							</td>
						</c:if>
					<td style=" width:100px;">
						<c:if test="${list.compFlg eq 't' }">
							<i class='bi bi-search'></i>
						</c:if>
						<c:if test="${list.compFlg eq 'f' || empty list.compFlg }">
							<i class="bi bi-tools"></i>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list }">
				<tr>
					<td colspan="2">データがありません。</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<input type="hidden" id="identificationNo" name="identificationNo" readonly="readonly">
		<input type="hidden" id="pastStartDate" name="pastStartDate" value="${pastStartDate }">
		<input type="hidden" id="pastEndDate" name="pastEndDate" value="${pastEndDate }">
		<input type="hidden" id="emp_id" name="emp_id" value="${emp_id }">
		</div>
	</div>
	<c:if test="${sessionScope.adminFlg eq '01'}">
			<div class="ui" style="margin-top: 10px;">
				<c:choose>
				<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
					<div class="ui blue button right floated"  onClick="salaryConfirm()" >給料確定</div>
				</c:when>
				<c:otherwise>
					<button class="ui disabled button right floated" >給料確定	</button>
				</c:otherwise>
				</c:choose>
			</div>	
	
			<div align="center">
				<div class="ui pagination menu">	
					<c:if test="${page.curBlock > 1}">
						<c:choose>
							<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
								<a class="item" href="MoveTosalaryMain?curPage=${page.prevPage}"><i class="angle left icon"></i></a>
								<a class="item" href="MoveTosalaryMain?curPage=1&year=${year}&month=${month}"><i class="angle double left icon"></i></a>
							</c:when>
							<c:otherwise>
								<a class="item" href="MoveTosalaryMain?curPage=1&year=${year}&month=${month}&pastStartDate=${pastStartDate }&pastEndDate=${pastEndDate}&emp_id=${emp_id}"><i class="angle double left icon"></i></a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.curBlock > 1}">
						<c:choose>
							<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
								<a class="item" href="MoveTosalaryMain?curPage=${page.prevPage}&year=${year}&month=${month}"><i class="angle left icon"></i></a>
							</c:when>
							<c:otherwise>
								<a class="item" href="MoveTosalaryMain?curPage=${page.prevPage}&year=${year}&month=${month}&pastStartDate=${pastStartDate }&pastEndDate=${pastEndDate}&emp_id=${emp_id}"><i class="angle left icon"></i></a>
							</c:otherwise>
						</c:choose>
					</c:if>
					
					<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
						<c:choose>
							<c:when test="${pageNum == page.curPage}">
								<div class="active item">${pageNum}</div>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
										<a class="item" href="MoveTosalaryMain?curPage=${pageNum}&year=${year}&month=${month}">
											${pageNum}
										</a>
									</c:when>
									<c:otherwise>
										<a class="item" href="MoveTosalaryMain?curPage=${pageNum}&year=${year}&month=${month}&pastStartDate=${pastStartDate }&pastEndDate=${pastEndDate}&emp_id=${emp_id}">
											${pageNum}
										</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					<c:if test="${page.curBlock < page.totBlock}">
						<c:choose>
							<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
								<a class="item" href="MoveTosalaryMain?curPage=${page.nextPage}&year=${year}&month=${month}"><i class="angle right icon"></i></a>
							</c:when>
							<c:otherwise>
								<a class="item" href="MoveTosalaryMain?curPage=${page.nextPage}&year=${year}&month=${month}&pastStartDate=${pastStartDate }&pastEndDate=${pastEndDate}&emp_id=${emp_id}"><i class="angle right icon"></i></a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.curBlock < page.totBlock}">
						<c:choose>
							<c:when test="${empty pastEndDate && empty pastStartDate && empty emp_id}">
								<a class="item" href="MoveTosalaryMain?curPage=${page.totPage}&year=${year}&month=${month}"><i class="angle double right icon"></i></a>
							</c:when>
							<c:otherwise>
								<a class="item" href="MoveTosalaryMain?curPage=${page.totPage}&year=${year}&month=${month}&pastStartDate=${pastStartDate }&pastEndDate=${pastEndDate}&emp_id=${emp_id}"><i class="angle double right icon"></i></a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
			</div>	
	</c:if>		
</form>
</div>
</html>
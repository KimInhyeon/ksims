<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>KS情報システム</title>
</head>
<c:if test="${conductCnt eq salaryCnt}">
	<script type="text/javascript">
	function onConfirm() {
		if (confirm("登録しますか？") == true) {
			salaryConfirm.submit();
		}
	}
	</script>
</c:if>

<script type="text/javascript">
function ViewDetail(empId) {
	$('#identificationNo').val(empId);
	salaryConfirm.action = "SalaryPayroll_View_Detail";
	salaryConfirm.submit();
}

function PayrollRegist(empId) {
	//SalaryPayrollRegist?identificationNo=${list.empId}&work_year_month=${year}-${targetMonth}
	$('#identificationNo').val(empId);
	salaryConfirm.action = "SalaryPayrollRegist";
	salaryConfirm.submit();
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
	<div class="content_margin_small">
		<form name="salaryConfirm" action="salaryConfirm" method="POST">
			<h1><i class="caret square left icon"></i>給料確定</h1>${year}年 ${targetMonth}月分
			<input type="hidden" name="identificationNo" id="identificationNo">
			<input type="hidden" name="work_year_month" id="work_year_month" value="${year}-${targetMonth}-01">
			<c:if test="${monthlyComplete eq 'no'}">
			<div class="ui grid">
				<div class="column">
					<div class="ui info message" style="width:40%">
						<div class="header">給料確定情報</div>
						<ul class="list">
							<li>${curMonth}月の現在給料対象社員数： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${empCnt}件<br></li>
							<c:if test="${conductCnt ne salaryCnt}">
								<li>${targetMonth}月の勤務入力データーの総計： <span style="font-weight: bold; color: red">${conductCnt}件</span></li>
							    <li>${targetMonth}月の給料入力データーの総計： <span style="font-weight: bold; color: red"> ${salaryCnt}件</span></li>
							</c:if>
							<c:if test="${conductCnt eq salaryCnt}">
								<li>${targetMonth}月の勤務入力データーの総計： ${conductCnt}件</li>
							    <li>${targetMonth}月の給料入力データーの総計： ${salaryCnt}件</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-hover table-bordered table-sm" style="width: 97%;">
						<thead class="thead-light" align="center">
							<tr>
							    <th style="width:3%">#</th>
								<th style="width:20%">社員番号</th>
								<th style="width:20%">職位</th>
								<th style="width:20%">社員名</th>
								<th style="width:20%">総支給額</th>
								<th style="width:10%">給料入力</th>
							</tr>
						</thead>
						<tbody id="GradeTable" align="center">
						    <c:forEach items="${confirmList}" var="list" varStatus="status">
						        <tr>
						            <td style="text-align:center">${status.count}</td>
						            <td style="text-align:center">${list.empId}</td>
						            <td style="text-align:left">${list.positionName}</td>
						            <td style="text-align:left;margin-left:20px">${list.empName}</td>
						            <td style="text-align:right;padding-right:20px"><fmt:formatNumber value="${list.salary}" pattern="#,###,###" /></td>
						            <td style="text-align:center;">
						            <c:if test="${list.compFlg ne 't'}">
						            	<a href="javascript:PayrollRegist('${list.empId}')"><i class="edit icon"></i></a>
						            </c:if>
						            <c:if test="${list.compFlg eq 't'}">
						            	<a href="javascript:ViewDetail('${list.empId}')"><i class="edit icon"></i></a>
						            </c:if>
						            </td>
						        </tr>
						    </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</c:if>
			<c:if test="${monthlyComplete eq 'ok'}">
			の給料は確定されました。
			</c:if>
			<div class="ui">
				<c:if test="${conductCnt ne salaryCnt}">
					<button class="ui disabled button right floated" style="margin-right: 3%">
						給料確定<i class="right chevron icon"></i>
					</button>
				</c:if>
				<br>
				<c:if test="${conductCnt eq salaryCnt}">
					<div class="ui blue button right floated" onClick="onConfirm()" style="margin-right: 3%">
						給料確定<i class="right chevron icon"></i>
					</div>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>給与明細書</title>

<style type="text/css">
input.text{
width: 100px;
}
</style>
</head>
<body>
<script>	
function submitPayslip() {
	
	if (confirm("これでよろしいでしょうか？") == true) {
		document.getElementById("form1").action="SalaryPayroll_Modify"
		document.getElementById("form1").submit()
	} else {
		return;
	}
}

function modify() {
	var identificationNo = document.getElementById("identificationNo").value;
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var flag = document.getElementById("flag").value;
	var employeeNo = document.getElementById("employeeNo").value;
	var beforeFlag = "1";
	
	if (confirm("この給料支払明細書を変更しますか？") == true) {
		location.href='SalaryPayroll_Modify?identificationNo=' + identificationNo + '&year=' + year + '&month=' + month + '&flag=' + flag + '&employeeNo=' + employeeNo + '&beforeFlag=' +beforeFlag; 
	} else {
		return;
	}
}

</script>	

<form id="form1" name="form1" method="post">
	<table style="width:70%; margin: 20px auto;">
		<thead class="thead-light">
			<tr>
				<th colspan="2">
					<input type="hidden" name="identificationNo" id="identificationNo" value="${identificationNo }">
					<select name="work_year_month" class="ui selection dropdown"  onchange="submit();" style="padding-block:0;">
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
				</th>
			</tr>
		</thead>
	</table>
	
	<table class="table table-bordered profiletable" style="width: 70%; margin: 0 auto; text-align: center;">
		<tr>
			<th class="table-info">日付</th>
			<td>${year}年${month}月</td>
			<th class="table-info">部門名</th>
			<td>${salaryPayroll.departmentName }</td>
			<th class="table-info">社員No.</th>
			<td>${salaryPayroll.empId }</td>
			<th class="table-info">氏名</th>
			<td>${salaryPayroll.empName }</td>
		</tr>
	</table>
	
	<table class="table table-bordered profiletable" style="width: 70%; margin: 30px auto; text-align: center;">
		<tbody>
		<tr class="table-info">
			<th rowspan ="4" style="width: 100px;">勤怠</th>
			<th>労働日数</th>
			<th>出勤日数</th>
			<th>有給休暇</th>
			<th>慶弔休暇</th>
			<th colspan="3">差引支給額</th>
		</tr>
	 	<tr style="text-align: end;">
			<td>${salaryPayroll.planWorkDays } 日&nbsp;</td>
			<td>${workDays } 日&nbsp;</td>
			<td>${paidVacationDays } 日&nbsp;</td>
			<td>${salaryPayroll.personalReasonVacationDays } 日&nbsp;</td>
			<td rowspan ="3" colspan="3" style="font-size: 3rem; vertical-align: middle;"><center><fmt:formatNumber value="${salaryPayroll.salary }" pattern="#,###,###" /> 円&nbsp;</center></td>
		</tr>
		<tr class="table-info">
			<th>欠勤日数</th>
			<th>遅刻回数</th>
			<th>早退回数</th>
			<th>時間外労働</th>
		</tr>
		<tr style="text-align: end;">
			<td>${salaryPayroll.absenteeismDays } 日&nbsp;</td>
			<td>${salaryPayroll.tardinessCount } 日&nbsp;</td>
			<td>${salaryPayroll.leaveEarlyCount } 日&nbsp;</td>
			<td>${overtime } 時間&nbsp;</td>
		</tr>
		<tr class="table-info">
			<th rowspan="4">支給</th>
			<th>基本給</th>
			<th>役職手当</th>
			<th>資格手当</th>
			<th>住宅手当</th>
			<th>時間外手当</th>
			<th>通勤手当</th>
			<th>年末清算</th>
		</tr>
		<tr style="text-align: end;">
			<td><fmt:formatNumber value="${salaryPayroll.basePay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.positionPay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.certificationPay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.housingAllowance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.overtimePay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.transportationPay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.endYearPay }" pattern="#,###,###" /></td>
		</tr>
		<tr class="table-info">
			<th>固定残業代</th>
			<th>交通費・経費</th>
			<th>成果級</th>
			<th>その他手当</th>
			<th>不就労控除</th>
			<th>総支給額</th>
			<th>課税対象額</th>
		</tr>
		<tr style="text-align: end;">
			<td><fmt:formatNumber value="${salaryPayroll.fixedOvertimePay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.extraCost }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.incentive }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.extraPay }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.absentDeductied }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.totalSalary }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.taxableProperty }" pattern="#,###,###" /></td>
		</tr>
		<tr class="table-info">
			<th rowspan ="4">控除</th>
			<th>介護保険</th>
			<th>健康保険</th>
			<th>厚生年金</th>
			<th>雇用保険</th>
			<th>社会保険</th>
			<th>所得税</th>
			<th>住民税</th>
		</tr>
		<tr style="text-align: end;">
			<td><fmt:formatNumber value="${salaryPayroll.caringInsurance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.healthInsurance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.pension }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.employedInsurance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.companyInsurance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.incomeTax }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.citizenTax }" pattern="#,###,###" /></td>
		</tr>
		<tr class="table-info">
			<th></th>
			<th></th>
			<th>社宅控除</th>
			<th>生命保険料</th>
			<th>積立金</th>
			<th>返済</th>
			<th>控除計</th>
		</tr>
		<tr style="text-align: end;">
			<td></td>
			<td></td>
			<td><fmt:formatNumber value="${salaryPayroll.dormDeduction }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.lifeInsurance }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.accumulationMoney }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.debtFinished }" pattern="#,###,###" /></td>
			<td><fmt:formatNumber value="${salaryPayroll.totalDeduction }" pattern="#,###,###" /></td>
		</tr>
		</tbody>
	</table>
		<center>
				<input class="ui button" value="リストへ戻る" type="button" onclick="history.back()" />
		</center>
	</form>
		
</body>
</html>
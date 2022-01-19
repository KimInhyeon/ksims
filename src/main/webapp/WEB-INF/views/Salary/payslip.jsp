<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>給与明細書</title>

<style type="text/css">

input[type=text] {
	border: 0;
	width: 100px;
	background-color: unset!important;
	text-align: right!important;
}
input[type=number] {
	border: 0;
	width: 100px;
	background-color: unset!important;
	text-align: right!important;
}
</style>

<script>
$(document).ready(function(){
	var basePay = parseInt($("#basePay").val().replace(rgx1, '') || 0);
	var positionPay = parseInt($("#positionPay").val().replace(rgx1, '') || 0);
	var transportationPay = parseInt($("#transportationPay").val().replace(rgx1, '') || 0);
	var fixedOvertimePay = parseInt($("#fixedOvertimePay").val().replace(rgx1, '') || 0);
	var taxableProperty = basePay+positionPay+fixedOvertimePay;
	
	if($('#salary').val() == null || $('#salary').val() == ""){
		$('#salary').val(comma(taxableProperty+transportationPay));
		$('#taxableProperty').val(comma(taxableProperty));
		$('#totalSalary').val(comma(taxableProperty+transportationPay));
	}
});

	function work_check() {
		if ($('#planWorkDays').val() == "" || $('#planWorkDays').val() == "0") {
			$('#planWorkDays').focus();
			alert("労働日数を入力してください。");
			return false;
		} else if ($('#paidVacationDays').val() == "") {
			$('#paidVacationDays').focus();
			alert("有給休暇を入力してください。");
			return false;
		} else if ($('#overtime').val() == "") {
			$('#overtime').focus();
			alert("時間外労働を入力してください。");
			return false;
		}
		else if($('#overtime').val() <0.5 && $('#overtime').val() > 0){
			$('#overtime').focus();
			alert("時間外労働が正しくありません。");
			return false;
		}
		else if($('#workDays').val() < 0){
			alert("出勤日数が正しくありません。");
			return false;
		}
		return true;
	}
	
	function salary_check() {
		if ($('#basePay').val() == "") {
			$('#basePay').focus();
			alert("基本給を入力してください。");
			return false;
		} else if ($('#positionPay').val() == "") {
			$('#positionPay').focus();
			alert("役職手当を入力してください。");
			return false;
		} else if ($('#certificationPay').val() == "") {
			$('#certificationPay').focus();
			alert("資格手当を入力してください。");
			return false;
		} else if ($('#housingAllowance').val() == "") {
			$('#housingAllowance').focus();
			alert("住宅手当を入力してください。");
			return false;
		} else if ($('#overtimePay').val() == "") {
			$('#overtimePay').focus();
			alert("時間外手当を入力してください。");
			return false;
		} else if ($('#transportationPay').val() == "") {
			$('#transportationPay').focus();
			alert("通勤手当を入力してください。");
			return false;
		} else if ($('#endYearPay').val() == "") {
			$('#endYearPay').focus();
			alert("年末清算を入力してください。");
			return false;
		} else if ($('#fixedOvertimePay').val() == "") {
			$('#fixedOvertimePay').focus();
			alert("固定残業代を入力してください。");
			return false;
		} else if ($('#extraCost').val() == "") {
			$('#extraCost').focus();
			alert("交通費を入力してください。");
			return false;
		} else if ($('#incentive').val() == "") {
			$('#incentive').focus();
			alert("成果級を入力してください。");
			return false;
		} else if ($('#extraPay').val() == "") {
			$('#extraPay').focus();
			alert("その他手当を入力してください。");
			return false;
		} else if ($('#absentDeductied').val() == "") {
			$('#absentDeductied').focus();
			alert("不就労控除を入力してください。");
			return false;
		} else if (parseInt($("#salary").val()) < 0) {
			$('#salary').focus();
			alert("差引支給額が正しくありません。");
			return false;
		}
		return true;
	}
	
	function deduction_check() {
		if ($('#caringInsurance').val() == "") {
			$('#caringInsurance').focus();
			alert("介護保険を入力してください。");
			return false;
		} else if ($('#healthInsurance').val() == "") {
			$('#healthInsurance').focus();
			alert("健康保険を入力してください。");
			return false;
		} else if ($('#pension').val() == "") {
			$('#pension').focus();
			alert("厚生年金を入力してください。");
			return false;
		} else if ($('#employedInsurance').val() == "") {
			$('#employedInsurance').focus();
			alert("雇用保険を入力してください。");
			return false;
		} else if ($('#companyInsurance').val() == "") {
			$('#companyInsurance').focus();
			alert("社会保険を入力してください。");
			return false;
		} else if ($('#incomeTax').val() == "") {
			$('#incomeTax').focus();
			alert("所得税を入力してください。");
			return false;
		} else if ($('#citizenTax').val() == "") {
			$('#citizenTax').focus();
			alert("住民税を入力してください。");
			return false;
		} else if ($('#dormDeduction').val() == "") {
			$('#dormDeduction').focus();
			alert("社宅控除を入力してください。");
			return false;
		} else if ($('#lifeInsurance').val() == "") {
			$('#lifeInsurance').focus();
			alert("生命保険料を入力してください。");
			return false;
		} else if ($('#accumulationMoney').val() == "") {
			$('#accumulationMoney').focus();
			alert("積立金を入力してください。");
			return false;
		} else if ($('#debtFinished').val() == "") {
			$('#debtFinished').focus();
			alert("返済を入力してください。");
			return false;
		}
		return true;
	}
</script>

<script>
function disable_unset() {
	$('#workDays').removeAttr("disabled");
	$('#salary').removeAttr("disabled");
	$('#totalSalary').removeAttr("disabled");
	$('#taxableProperty').removeAttr("disabled");
	$('#totalDeduction').removeAttr("disabled");
}

function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
    }    
  }

//등록후 리스트
function submitPayslip() {
	if (work_check() && salary_check() && deduction_check()) {
		if (confirm("これでよろしいでしょうか？") == true) {
			disable_unset();
			document.getElementById("form1").action = "SalaryPayrollWrite";
			document.getElementById("form1").submit();
		}
	}
}
	
//등록 후 다음페이지"""
function submitPayslipNext() {
		if (confirm("次の社員に移動してもよろしいでしょうか？") == true) {
			$.ajax({
				url : "ajaxNextEmp",
				type : "POST",
				success : function(data) {
					if(data == ""){
					//console.log(data);
					alert("登録する次の社員がありません。");
					}else{
						disable_unset();
						document.getElementById("form1").action = "SalaryPayrollNext";
						document.getElementById("form1").submit();
					}
				},
				error : function() {
					alert("Error");
				}
			});
		}
}

//보험료 계산
function setupPayslip() {
	var taxableProperty = $('#taxableProperty').val();
	if (salary_check()) {
		if (confirm("計算しますか？") == true) {
			$.ajax({
				url : "ajaxInsuranceReference",
				type : "POST",
				data : {
					taxableProperty : taxableProperty
				},
				success : function(data) {
					$('#healthInsurance').val(
							comma(data["healthInsurance"])); //　健康保険
					$('#pension').val(comma(data["pension"])); //　厚生年金
					$('#employedInsurance').val(
							comma(data["employedInsurance"]));//雇用保険
					$('#incomeTax').val(comma(data["incomeTax"])); //　所得税

					var alt = "";
					if(data["pensionFlg"] != null){
						alt += data["pensionFlg"]+"、";
					}
					if(data["healthInsuranceFlg"] != null){
						alt+= data["healthInsuranceFlg"]+"、";
					}
					if(data["empInsuranceFlg"] != null){
						alt += data["empInsuranceFlg"]
					}
					if(data["pensionFlg"] != null || data["healthInsuranceFlg"] != null || data["empInsuranceFlg"] != null){
						alert("該当社員は"+alt+"の申請をしませんでした。");
					}
					arr();
				},
				error : function() {
					alert("Error");
				}
			});
		}
	}
}
</script>

<SCRIPT type="text/javascript">
var rgx1 = /\D/g; // /[^0-9]/g 와 같은 표현
var rgx2 = /(\d+)(\d{3})/;
var rgx4 = /\D/g; // /[^0-9]/g 와 같은 표현
var rgx5 = /(\d+)(\d{3})/;
	
function arr() {
	var rgx1 = /\D/g; // /[^0-9]/g 와 같은 표현
	var rgx2 = /(\d+)(\d{3})/;
	//　支給
	var basePay = parseInt($("#basePay").val().replace(rgx1, '') || 0);
	var positionPay = parseInt($("#positionPay").val().replace(rgx1, '') || 0);
	var certificationPay = parseInt($("#certificationPay").val().replace(rgx1, '') || 0);
	var housingAllowance = parseInt($("#housingAllowance").val().replace(rgx1, '') || 0);
	var overtimePay = parseInt($("#overtimePay").val().replace(rgx1, '') || 0);
	var transportationPay = parseInt($("#transportationPay").val().replace(rgx1, '') || 0);
	var endYearPay = parseInt($("#endYearPay").val().replace(rgx1, '') || 0);
	var fixedOvertimePay = parseInt($("#fixedOvertimePay").val().replace(rgx1, '') || 0);
	var extraCost = parseInt($("#extraCost").val().replace(rgx1, '') || 0);
	var incentive = parseInt($("#incentive").val().replace(rgx1, '') || 0);
	var extraPay = parseInt($("#extraPay").val().replace(rgx1, '') || 0);
	var absentDeductied = parseInt($("#absentDeductied").val().replace(rgx1,'') || 0);
	//　控除
	var caringInsurance = parseInt($("#caringInsurance").val().replace(rgx1, '') || 0);
	var healthInsurance = parseInt($("#healthInsurance").val().replace(rgx1, '') || 0);
	var pension = parseInt($("#pension").val().replace(rgx1, '') || 0);
	var employedInsurance = parseInt($("#employedInsurance").val().replace(rgx1, '') || 0);
	var companyInsurance = parseInt($("#companyInsurance").val().replace(rgx1, '') || 0);
	var incomeTax = parseInt($("#incomeTax").val().replace(rgx1, '') || 0);
	var citizenTax = parseInt($("#citizenTax").val().replace(rgx1, '') || 0);
	var dormDeduction = parseInt($("#dormDeduction").val().replace(rgx1, '') || 0);
	var lifeInsurance = parseInt($("#lifeInsurance").val().replace(rgx1, '') || 0);
	var accumulationMoney = parseInt($("#accumulationMoney").val().replace(rgx1, '') || 0);
	var debtFinished = parseInt($("#debtFinished").val().replace(rgx1, '') || 0);

	//공제계
	var totalDeduction = caringInsurance + healthInsurance + pension
			+ employedInsurance + companyInsurance + incomeTax + citizenTax
			+ dormDeduction + lifeInsurance + accumulationMoney
			+ debtFinished;
	//총지급액
	var totalSalary = basePay + positionPay + certificationPay
			+ housingAllowance + overtimePay + transportationPay
			+ endYearPay + fixedOvertimePay + extraCost + incentive
			+ extraPay + absentDeductied;
	//과세대상
	var taxableProperty = totalSalary - transportationPay - extraCost
	//실지급액
	var salary = totalSalary - totalDeduction;

	$('#totalDeduction').val(comma(totalDeduction));
	$("#salary").val(comma(salary));
	$("#totalSalary").val(comma(totalSalary));
	$("#taxableProperty").val(comma(taxableProperty));
}

function getCode(obj) {
	var num01;
	var num02;
	num01 = obj.value;
	num02 = num01.replace(rgx1, "");
	num01 = setComma(num02);
	obj.value = num01;
	arr();
}
function setComma(inNum) {
	var outNum = null;
	outNum = inNum;
	while (rgx2.test(outNum)) {
		outNum = outNum.replace(rgx2, '$1' + ',' + '$2');
	}
	return outNum;
}

function getCode2(obj) {
	var code1 = /[^.\d]/g;
	var code2 = /^(\d*\.?)|(\d*)\.?/g;

	var day = obj.value;
	day = day.replace(code1, '').replace(code2, "$1$2").replace(/^\.+/,'');
	obj.value = day;
}

//점찍기
function getCode3(obj) {
	
	
	var code1 = /[^.\d]/g;
//	var code2 = /^(\d*\.?)|(\d*)\.?/g;
	var code2 = /^(\d*\.?)(\d*)?$/g;

	var day = obj.value;
	day = day.replace(code1, '').replace(code2, "$1$2").replace(/^\.+/,'');
	obj.value = day;

	var planWorkDays = parseInt($('#planWorkDays').val().replace(rgx1, '') || 0);
	var paidVacationDays = parseFloat($('#paidVacationDays').val()
			.replace(code1).replace(code2, "$1$2") || 0);
	
	$('#workDays').val(planWorkDays - paidVacationDays || 0);
	
	//시간외 수당
	var overtime = $('#overtime').val();
	var sum = $('#overtime_pay_sum').val();
	$('#overtimePay').val(comma(overtime*sum) || 0);
	arr();
}

</SCRIPT>

<script type="text/javascript">
//숫자 콤마
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function loadPayslip() {
	if (confirm("入力したデータは削除されます。よろしいでしょうか？") == true) {
		$.ajax({
			url : "ajaxSalaryPayrollLoad",
			type : "POST",
			success : function(data) {
				//console.log("data=" + data);
				if (typeof data == "undefined" || data == "") {
					alert("先月のデーターがありません。");
				} else {
					$('#planWorkDays').val(data["planWorkDays"]); //　労働日数
					$('#workDays').val(data["workDays"]); //　出勤日数
					$('#paidVacationDays').val(data["paidVacationDays"]); //　有給休暇
					$('#personalReasonVacationDays').val(data["personalReasonVacationDays"]); // 慶弔休暇
					$('#salary').val(comma(data["salary"])); //　差引支給額
					
					$('#absenteeismDays').val(comma(data["absenteeismDays"])); //　欠勤日数
					$('#tardinessCount').val(comma(data["tardinessCount"])); //　遅刻回数
					$('#leaveEarlyCount').val(comma(data["leaveEarlyCount"])); //　早退回数
					$('#overtime').val(comma(data["overtime"])); //　時間外労働

					$('#basePay').val(comma(data["basePay"])); //　基本給
					$('#positionPay').val(comma(data["positionPay"])); //　役職手当
					$('#certificationPay').val(comma(data["certificationPay"]));//　資格手当
					$('#housingAllowance').val(comma(data["housingAllowance"]));//　住宅手当
					$('#overtimePay').val(comma(data["overtimePay"])); //　時間外手当	
					$('#transportationPay').val(comma(data["transportationPay"]));//　通勤手当
					$('#endYearPay').val(comma(data["endYearPay"])); //　年末清算

					$('#fixedOvertimePay').val(comma(data["fixedOvertimePay"]));//　固定残業代
					$('#extraCost').val(comma(data["extraCost"])); //　交通費
					$('#incentive').val(comma(data["incentive"])); //　成果級
					$('#extraPay').val(comma(data["extraPay"])); //　その他手当
					$('#absentDeductied').val(comma(data["absentDeductied"])); //　不就労控除
					$('#totalSalary').val(comma(data["totalSalary"])); //　総支給額
					$('#taxableProperty').val(comma(data["taxableProperty"])); //　課税対象額

					$('#caringInsurance').val(comma(data["caringInsurance"])); //　介護保険
					$('#healthInsurance').val(comma(data["healthInsurance"])); //　健康保険
					$('#pension').val(comma(data["pension"])); //　厚生年金
					$('#employedInsurance').val(comma(data["employedInsurance"]));//雇用保険
					$('#companyInsurance').val(comma(data["companyInsurance"]));//社会保険
					$('#incomeTax').val(comma(data["incomeTax"])); //　所得税
					$('#citizenTax').val(comma(data["citizenTax"])); //　住民税
					$('#dormDeduction').val(comma(data["dormDeduction"])); //　社宅控除
					$('#lifeInsurance').val(comma(data["lifeInsurance"])); //　生命保険料
					$('#accumulationMoney').val(comma(data["accumulationMoney"]));//　積立金
					$('#debtFinished').val(comma(data["debtFinished"])); //　返済
					$('#totalDeduction').val(comma(data["totalDeduction"])); //　控除計
				}
			},
			error : function() {
				alert("Error");
			}
		});
	} else {
		return;
	}
}

function returnList() {
	var referrer = document.referrer;
	var url = referrer.slice(-13);

	if (url == "salaryConfirm"){
		location.href="salaryConfirm";
	}else{
		location.href="MoveTosalaryMain?curPage=1";
	}
}
</script>

</head>
<body>
<input type="hidden" name="overtime_pay_sum" id="overtime_pay_sum" value="${overtimePaySum}">
	<form id="form1" name="form1" method="post">
		<table class="table table-bordered profiletable"
			style="width: 70%; margin: 0 auto; text-align: center;">
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

		<table class="table table-bordered profiletable"
			style="width: 70%; margin: 30px auto; text-align: center;">
			<tbody>
				<tr class="table-info">
					<th rowspan="4" style="width: 100px;">勤怠</th>
					<th>労働日数</th>
					<th>出勤日数</th>
					<th>有給休暇</th>
					<th>慶弔休暇</th>
					<th colspan="3">差引支給額</th>
				</tr>
				<tr>
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='1' class="form-control" id="planWorkDays" name="planWorkDays" maxlength="2" value='${salaryPayroll.planWorkDays}' onchange='getCode3(this);' onkeyup='getCode3(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="planWorkDays" name="planWorkDays" maxlength="2"  value='${salaryPayroll.planWorkDays}' onchange='getCode3(this);' onkeyup='getCode3(this);' /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>	
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='0.5' class="form-control" id="workDays" name="workDays" maxlength="4" value='${salaryPayroll.workDays}' onchange='getCode2(this);' onkeyup='getCode2(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="workDays" name="workDays" value='${salaryPayroll.workDays}' onchange='getCode2(this);' onkeyup='getCode2(this);' /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>	
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='0.5' class="form-control" id="paidVacationDays" name="paidVacationDays" maxlength="4" value='${salaryPayroll.paidVacationDays}' onchange='getCode3(this);' onkeyup='getCode3(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="paidVacationDays" name="paidVacationDays" maxlength="3" onchange='getCode3(this);' value='${salaryPayroll.paidVacationDays}' onkeyup='getCode3(this);'/> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='1' class="form-control" id="personalReasonVacationDays" name="personalReasonVacationDays" maxlength="2" value='${salaryPayroll.personalReasonVacationDays}' onchange='getCode(this);' onkeyup='getCode(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="personalReasonVacationDays" name="personalReasonVacationDays" onchange='getCode(this);' onkeyup='getCode(this);' maxlength="2" value="${salaryPayroll.personalReasonVacationDays }" /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>
					</td>
						
					<td rowspan="3" colspan="3" bgcolor="#bde9ba">
						<center>
							<input type="text" class="form-control" id="salary" name="salary" value='<fmt:formatNumber value="${salaryPayroll.salary }" pattern="#,###,###" />' style="display: inline; font-size: 3rem; width: 52%;" disabled="disabled" />
							<span style="font-size: 3rem;">円</span>
						</center>
					</td>
					
				</tr>
				<tr class="table-info">
					<th>欠勤日数</th>
					<th>遅刻回数</th>
					<th>早退回数</th>
					<th>時間外労働</th>
				</tr>
				<tr>
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='1' class="form-control" id="absenteeismDays" name="absenteeismDays" maxlength="2" value='${salaryPayroll.absenteeismDays}' onchange='getCode(this);' onkeyup='getCode(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="absenteeismDays" name="absenteeismDays" value="${salaryPayroll.absenteeismDays}" onchange='getCode(this);' onkeyup='getCode(this);' maxlength="2" /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='1' class="form-control" id="tardinessCount" name="tardinessCount" maxlength="2" value='${salaryPayroll.tardinessCount}' onchange='getCode(this);' onkeyup='getCode(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="tardinessCount" name="tardinessCount" value="${salaryPayroll.tardinessCount}" onchange='getCode(this);' onkeyup='getCode(this);' maxlength="2" /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='31' step='1' class="form-control" id="leaveEarlyCount" name="leaveEarlyCount" maxlength="2" value='${salaryPayroll.leaveEarlyCount}' onchange='getCode(this);' onkeyup='getCode(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="leaveEarlyCount" name="leaveEarlyCount" value="${salaryPayroll.leaveEarlyCount}" onchange='getCode(this);' onkeyup='getCode(this);' maxlength="2" /> --%>
						<div class="ui basic label" style="font-size:0.8em">日</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type='number' min='0' max='100' step='0.5' class="form-control" id="overtime" name="overtime" maxlength="4" value='${salaryPayroll.overtime}' onchange='getCode3(this);' onkeyup='getCode3(this);' oninput="maxLengthCheck(this)">
<%-- 						<input type="text" class="form-control" id="overtime" name="overtime" value="${salaryPayroll.overtime}" onchange='getCode3(this);' onkeyup='getCode3(this);' maxlength="4" /> --%>
						<div class="ui basic label" style="font-size:0.8em">時間</div>
					</div>
					</td>
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
				
				<tr>
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="basePay" name="basePay" value='<fmt:formatNumber value="${salaryPayroll.basePay}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
					<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>

					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="positionPay" name="positionPay" value='<fmt:formatNumber value="${salaryPayroll.positionPay }" pattern="#,###,###" />'　onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>

					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="certificationPay" name="certificationPay" value='<fmt:formatNumber value="${salaryPayroll.certificationPay }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="housingAllowance" name="housingAllowance" value='<fmt:formatNumber value="${salaryPayroll.housingAllowance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>

					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="overtimePay" name="overtimePay" value='<fmt:formatNumber value="${salaryPayroll.overtimePay }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="transportationPay" name="transportationPay" value='<fmt:formatNumber value="${salaryPayroll.transportationPay }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="endYearPay" name="endYearPay" value='<fmt:formatNumber value="${salaryPayroll.endYearPay }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>	
					</td>
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
				<tr>
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="fixedOvertimePay" name="fixedOvertimePay" value='<fmt:formatNumber value="${salaryPayroll.fixedOvertimePay }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="extraCost" name="extraCost" value='<fmt:formatNumber value="${salaryPayroll.extraCost}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="incentive" name="incentive" value='<fmt:formatNumber value="${salaryPayroll.incentive}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
						<div class="ui right labeled input">
						<input type="text" class="form-control" id="extraPay" name="extraPay" value='<fmt:formatNumber value="${salaryPayroll.extraPay}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
						
					<td>
						<div class="ui right labeled input">
						<input type="text" class="form-control" id="absentDeductied" name="absentDeductied" value='<fmt:formatNumber value="${salaryPayroll.absentDeductied}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6">
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td bgcolor="#bde9ba">
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="totalSalary" name="totalSalary" value='<fmt:formatNumber value="${salaryPayroll.totalSalary}" pattern="#,###,###" />' tabindex="-1" readonly="readonly">
						<div class="ui basic label" style="font-size:0.8em; background-color: unset;">円</div>
					</div>
					</td>
					
					<td bgcolor="#bde9ba">
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="taxableProperty" name="taxableProperty" value='<fmt:formatNumber value="${salaryPayroll.taxableProperty}" pattern="#,###,###" />' tabindex="-1" readonly="readonly">
						<div class="ui basic label" style="font-size:0.8em; background-color: unset;">円</div>
					</div>
					</td>
				</tr>

				<tr class="table-info">
					<th rowspan="4">控除</th>
					<th>介護保険</th>
					<th>健康保険</th>
					<th>厚生年金</th>
					<th>雇用保険</th>
					<th>社会保険</th>
					<th>所得税</th>
					<th>住民税</th>
				</tr>
				<tr>
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="caringInsurance" name="caringInsurance" value='<fmt:formatNumber value="${salaryPayroll.caringInsurance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
						
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="healthInsurance" name="healthInsurance" value='<fmt:formatNumber value="${salaryPayroll.healthInsurance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="pension" name="pension" value='<fmt:formatNumber value="${salaryPayroll.pension}" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="employedInsurance" name="employedInsurance" value='<fmt:formatNumber value="${salaryPayroll.employedInsurance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="companyInsurance" name="companyInsurance" value='<fmt:formatNumber value="${salaryPayroll.companyInsurance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="incomeTax" name="incomeTax" value='<fmt:formatNumber value="${salaryPayroll.incomeTax }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="citizenTax" name="citizenTax" value='<fmt:formatNumber value="${salaryPayroll.citizenTax }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
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
				<tr>
					<td></td>
					<td></td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="dormDeduction" name="dormDeduction" value='<fmt:formatNumber value="${salaryPayroll.dormDeduction }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="lifeInsurance" name="lifeInsurance" value='<fmt:formatNumber value="${salaryPayroll.lifeInsurance }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="accumulationMoney" name="accumulationMoney" value='<fmt:formatNumber value="${salaryPayroll.accumulationMoney }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td>
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="debtFinished" name="debtFinished" value='<fmt:formatNumber value="${salaryPayroll.debtFinished }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="7" />
						<div class="ui basic label" style="font-size:0.8em">円</div>
					</div>
					</td>
					
					<td bgcolor="#bde9ba">
					<div class="ui right labeled input">
						<input type="text" class="form-control" id="totalDeduction" name="totalDeduction" value='<fmt:formatNumber value="${salaryPayroll.totalDeduction }" pattern="#,###,###" />' onchange='getCode(this);' onkeyup='getCode(this);' maxlength="6" readonly="readonly" />
						<div class="ui basic label" style="font-size:0.8em; background-color: unset;">円</div>
					</div>
					</td>
				</tr>
			</tbody>
		</table>

		<center>
			<input class="ui button" value="リストへ戻る" type="button"
				onclick="returnList()" />
			<input class="ui primary button" type="button" value="先月ロード"
				onclick="loadPayslip()" />
			<input class="ui primary button" type="button" value="計算"
				onclick="setupPayslip()" id="setcal" />
			<input class="ui primary button" type="button" value="登録"
				onclick="submitPayslip()" id="setsave" />
			<input class="ui primary button" type="button" value="次に"
				onclick="submitPayslipNext()" id="setsaveToNext" />
		</center>
	</form>
</body>
</html>
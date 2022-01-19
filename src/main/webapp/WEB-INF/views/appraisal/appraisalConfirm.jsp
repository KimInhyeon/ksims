<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="charset=UTF-8">
	<title>評価承認</title>
	<script>
		function appraisalhistoryback(){
				location.href='${pageContext.request.contextPath}/AppraisalMainController';
		}
		
		function toggleChecker(){
			const chks=document.getElementsByName("confirmCheck");
			if(mst_chk.checked){
				for (let i=0; i<chks.length; i++){
			        chks[i].checked=true;
			      }
			}else{
				for (let i=0; i<chks.length; i++){
			        chks[i].checked=false;
			      }
			}
			
		}
		
		function onConfirm(){
			if(confirm('評価を承認します。')){
				const chks=document.getElementsByName("confirmCheck");
				var cnt=0;
					for (let i=0; i<chks.length; i++){
				        if(chks[i].checked){
				        	$('.disis'+chks[i].value).removeAttr('disabled');        	
				        	cnt++;
				        	}
				        }
				if(cnt==0){
					alert('承認したい社員を一人以上チェックしてください。');
					return;
				}
				if(doubleSubmitCheck()){
					return;
				}
				appraisal.submit();
				$('#confirm_btn').css('pointer-events','none');
			}
		}
		
		//dblClick 방지
		var doubleSubmitFlag = false;
		function doubleSubmitCheck(){
		    if(doubleSubmitFlag){
		        return doubleSubmitFlag;
		    }else{
		        doubleSubmitFlag = true;
		        return false;
		    }
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
	.table thead th {vertical-align:middle;}
	input {text-align: center;}
	</style>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" id="appraisal" name="appraisal" action="${pageContext.request.contextPath}/AppraisalConfirmController" method="POST">
			<h1><i class="caret square left icon"></i>評価承認</h1>
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-hover table-bordered table-sm">
						<thead class="table-info" align="center">
							<tr>
								<th rowspan="2">社番</th>
								<th rowspan="2">社員名</th>
								<th rowspan="2">職位</th>
								<th rowspan="2">プロジェクト名</th>
								<th colspan="2">評価期間</th>
								<th rowspan="2">評価グレード </th>
								<th rowspan="2">評価合計 </th>
								<th style="vertical-align:middle" rowspan="2" >
									<input type="checkbox" onclick="toggleChecker()" id="mst_chk" name="mst_chk">
								</th>
							</tr>
							<tr>
								<th>開始日年月日</th>
								<th>終了年月日 	</th>
							</tr>
						</thead>
						<tbody align="center">
							<c:if test="${not empty appr_list}">
								<c:set var="i" value="0"/>
								<c:forEach items="${appr_list}" var="list">
									<tr>
										<td>
											<input name="emp_id_${i=i+1}" type="text" value="${list.emp_id}" class="form-control-plaintext disis${i}" disabled>
										</td>
										<td>
											<input name="emp_name_${i}" type="text" value="${list.emp_name}" class="form-control-plaintext disis${i}" style="text-align:left" disabled>
										</td>
										<td>
											<input name="position_name_${i}" type="text" value="${list.position_name}" class="form-control-plaintext disis${i}"  style="width:100px;text-align:left" disabled>
										</td>
										<td>
											<input name="project_name_${i}" type="text" value="${list.project_name}" class="form-control-plaintext disis${i}"  style="width:240px;text-align:left" disabled>
										</td>
										<td>
											<input name="appraisal_start_date_${i}" type="text" value="${list.appraisal_start_date}" class="form-control-plaintext disis${i}" disabled>
										</td>
										<td>
											<input name="appraisal_end_date_${i}" type="text" value="${list.appraisal_end_date}" class="form-control-plaintext disis${i}" disabled>
										</td>
										<td>
											<input name="app_grade_${i}" type="text" value="${list.app_grade}" class="form-control-plaintext disis${i}" disabled>
										</td>
										<td>
											<input name="app_score_${i}" type="text" value="${list.app_score}" class="form-control-plaintext disis${i}" disabled>
										</td>
										<td style="vertical-align:middle">
											<input type="checkbox" name="confirmCheck" value="${i}" id="confirmChk_${i}">
										</td>
									</tr>
									
									<input name="appraisal_idx_${i}" type="hidden" value="${list.appraisal_idx}">
									<input name="position_code_${i}" type="hidden" value="${list.position_code}">
									<input name="project_score_${i}" type="hidden" value="${list.project_score}">
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="ui">
				<div class="ui button left floated" onclick="appraisalhistoryback()">
					<i class="left chevron icon"></i>戻る
				</div>
				<div class="ui blue button right floated" onClick="onConfirm()" id="confirm_btn">
					承認<i class="right chevron icon"></i>
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>
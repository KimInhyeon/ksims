<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/modal/appraisal/appraisalMainModal.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>
	<script>
		//遷移
		function appraisal_score(){
			location.href='${pageContext.request.contextPath}/AppraisalScoreController';
		}
		
		function appraisal_history(){
			var messagesFlg ="0";
			location.href='${pageContext.request.contextPath}/AppraisalHistoryController?messagesFlg='+ messagesFlg;
		}
	
		function appraisal_confirm(){
			location.href='${pageContext.request.contextPath}/AppraisalConfirmController';
		}

		function order_confirm(){
			location.href='${pageContext.request.contextPath}/OrderMain';
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
		<form class="ui form" id="appraisal" name="appraisal" action="${pageContext.request.contextPath}/AppraisalModalController" method="POST"name="form">
			<h1><i class="caret square left icon"></i>評価管理</h1>
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-hover table-bordered table-sm">
						<thead class="table-info" align="center">
							<tr>
								<th>#</th>
								<th>プロジェクト名</th>
								<th>現場住所</th>
								<c:if test="${sessionScope.adminFlg == '01'}">
									<th>登録日</th>
								</c:if>
								<c:if test="${sessionScope.adminFlg != '01'}">
									<th>勤務期間</th>
								</c:if>
							</tr>
						</thead>
						<tbody id="GradeTable" align="center">
							<c:if test="${not empty field_List}">
								<c:set value="0" var="field_index"/>
								<c:forEach items="${field_List}" var="field" varStatus="i">
									<tr onclick="fieldCheck('${field.field_code}','${field.field_name}')" style="cursor: pointer;"> 
										<td><c:out value="${field_index=field_index+1}"/></td>
										<td style="text-align: left;"> ${field.field_name}</td>
										<td style="text-align: left;"> ${field.field_addr}</td>	
										<c:if test="${sessionScope.adminFlg == '01'}">					
											<td style="text-align: left;"><fmt:formatDate value="${field.reg_date}" pattern="yyyy-MM-dd" /></td>	
										</c:if>														
										<c:if test="${sessionScope.adminFlg != '01'}">
											<td> 
													${field.work_start}&nbsp;~&nbsp;${field.work_end}	
											</td>
										</c:if>	
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		<br>
		<div class="ui">
			<div class="ui button left floated" onclick="javascript:location.href='${pageContext.request.contextPath}/main'">
				<i class="left chevron icon"></i>戻る
			</div>
		
			<div class="ui button right floated" style="background-color: #FFFFFF; margin-top:-10px;margin-right:-25px">
				<div class="ui blue button" onclick="appraisal_score()">評価照会</div>
				<div class="ui blue button" onclick="appraisal_history()">評価承認履歴</div>
				<c:if test="${sessionScope.adminFlg == '01'}">
					<div class="ui blue button" onclick="appraisal_confirm()" >評価承認</div>
				</c:if>
			</div>
		</div>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="charset=UTF-8">
	<title>評価承認履歴</title>
	<script>
		function appraisalhistoryback(){
			var auth_code=${sessionScope.adminFlg};
			if(auth_code=='01'){
				location.href='${pageContext.request.contextPath}/AppraisalHistoryController?messagesFlg=0';
			}else{
				location.href='${pageContext.request.contextPath}/AppraisalMainController';
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
	</style>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" name="readAppr">
			<h1><i class="caret square left icon"></i>評価承認履歴</h1>
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-hover table-bordered table-sm" style="width:30%; height:30%; float:right;">
						<thead class="table-info" align="center">
							<tr>
								<th>社番</th>
								<th>社員名</th>
								<th>勤務年数</th>
								<th>累積合計</th>
							</tr>
						</thead>
						<tbody>
							<tr align="center">
								<td>${emp_info.emp_id}</td>
								<td >${emp_info.emp_name}</td>
								<td>${emp_info.work_year}年</td>
								<td>${emp_info.sum_app}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="ui gird">
				<div class="column">
					<table class="table table-hover table-bordered table-sm">
						<thead class="table-info" align="center">
							<tr>
								<th rowspan="2">プロジェクト名</th>
								<th rowspan="2">職位</th>
								<th colspan="2">評価期間</th>
								<th rowspan="2">評価グレード </th>
								<th rowspan="2">評価合計 </th>
							</tr>
							<tr>
								<th>開始日年月日</th>
								<th>終了年月日 	</th>
							</tr>
						</thead>
						<tbody align="center">
						<c:if test="${not empty history_list}">
							<c:forEach items="${history_list}" var="list">
								<tr>
									<td style="text-align:left;">${list.project_name}</td>
									<td style="text-align:left;">${list.position_name}</td>
									<td>${list.appraisal_start_date}</td>
									<td>${list.appraisal_end_date}</td>
									<td>${list.app_grade}</td>
									<td>${list.app_score}</td>
								</tr>
							</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
			<div align="center">
				<div class="ui pagination menu">	
						<c:if test="${page.curBlock < 1}">
							<a class="item" href="${pageUrl}curPage=1&param=1"><i class="angle double left icon"></i></a>
						</c:if>
						<c:if test="${page.curBlock > 1}">
							<a class="item" href="${pageUrl}curPage=${page.prevPage}&param=1"><i class="angle left icon"></i></a>
						</c:if>
						<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
							<c:choose>
								<c:when test="${pageNum == page.curPage}">
									<div class="active item">${pageNum}</div>
								</c:when>
								<c:otherwise>
									<a class="item" href="${pageUrl}curPage=${pageNum}&param=1">
										${pageNum}
									</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${page.curBlock < page.totBlock}">
							<a class="item" href="${pageUrl}curPage=${page.nextPage}&param=1"><i class="angle right icon"></i></a>
						</c:if>
						<c:if test="${page.curBlock < page.totBlock}">
							<a class="item" href="${pageUrl}curPage=${page.totPage}&param=1"><i class="angle double right icon"></i></a>
						</c:if>
				</div>
			</div>
			
			<div class="ui">
				<div class="ui button left floated"  onClick="appraisalhistoryback()" >
					<i class="left chevron icon"></i>戻る
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>
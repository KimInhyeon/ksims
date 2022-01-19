<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<title>評価承認履歴</title>
</head>
<style>
h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}

tr{cursor: pointer;}
</style>
<body>
	<div class="content_margin">
		<form class="ui form" name="readAppr">
			<h1><i class="caret square left icon"></i>評価承認履歴</h1>
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-hover table-bordered table-sm">
						<thead class="table-info" align="center">
							<tr>
								<th>社番</th>
								<th>社員名</th>
								<th>勤務年数</th>
								<th>累積合計</th>
							</tr>
						</thead>
						<tbody align="center" >
							<c:if test="${not empty employee_list}">
								<c:forEach items="${employee_list}" var="emp">
									<tr onclick="location.href='${pageContext.request.contextPath}/AppraisalHistoryAdmin?emp_id=${emp.emp_id}'">
										<td>${emp.emp_id}</td>
										<td style="text-align:left;" >${emp.emp_name}</td>
										<td>${emp.work_year}</td>
										<td>${emp.sum_app}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
			<div align="center">
				<div class="ui pagination menu">	
					<c:if test="${page.curBlock > 1}">
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
								</a>&nbsp;
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
				<div class="ui button left floated"  onClick="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'" >
					<i class="left chevron icon"></i>戻る
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>
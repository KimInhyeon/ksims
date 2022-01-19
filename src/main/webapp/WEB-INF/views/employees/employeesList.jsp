<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>社員照会</title>
<script type="text/javascript">
	function empListClick(empIdx){
		location.href='${pageContext.request.contextPath}/EmployeesChangeController?empIdx='+ empIdx;
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
	<h1 ><i class="caret square left icon"></i>社員管理</h1>
		<form class="ui form" id="employees" name="employees" action="EmployeesWriteController" method="POST">
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-light table-bordered table-sm"> 
				 		<thead class="table-info" align="center"> 
							<tr>
							<!-- 
								<th style="width:10%">#</th>
								<th style="width:15%">社員番号</th>
								<th style="width:15%">職位</th>
								<th>社員名</th>
								<th style="width:15%">入社日</th>
								 -->
								<th >#</th>
								<th >社員番号</th>
								<th >職位</th>
								<th>社員名</th>
								<th >入社日</th>
							</tr>
						</thead>	
						<tbody id="GradeTable" align="center">
							<c:if test="${not empty list}">
								<c:forEach items="${list}" var = "list" varStatus="num">
									<tr onclick="empListClick('${list.empIdx}')" style="cursor: pointer">
										<td>${list.empSeq}</td>
										<td>${list.empId}</td>
										<td style="text-align:left;">${list.positionName}</td>
										<td style="text-align:left;margin-left:50px">${list.empName}</td>
										<td><fmt:formatDate value="${list.hiredDate}" pattern="yyyy-MM-dd" /></td>
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
						<a class="item" href="EmployeesListController?curPage=1"><i class="angle double left icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="EmployeesListController?curPage=${page.prevPage}"><i class="angle left icon"></i></a>
					</c:if>
					<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
						<c:choose>
							<c:when test="${pageNum == page.curPage}">
								<div class="active item">${pageNum}</div>
							</c:when>
							<c:otherwise>
								<a class="item" href="EmployeesListController?curPage=${pageNum}">
									${pageNum}
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="EmployeesListController?curPage=${page.nextPage}"><i class="angle right icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="EmployeesListController?curPage=${page.totPage}"><i class="angle double right icon"></i></a>
					</c:if>
				</div>
			</div>			
		</form>
	</div>
</body>
</html>

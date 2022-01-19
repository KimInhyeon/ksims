<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>
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
		<form class="ui form" id="employees" name="employees" action="EmployeesWriteController" method="POST">
			<h1><i class="caret square left icon"></i>社員管理</h1>
			<div class="ui grid">
				<div class="column">
					<table class="table table-striped table-light table-bordered">
						<thead class="table-info" align="center">
							<tr>
								<th style="width:15%">社員番号</th>
								<th style="width:15%">職位</th>
								<th>社員名</th>
								<th style="width:15%">入社日</th>
							</tr>
						</thead>	
						<tbody id="GradeTable" align="center">
							<c:if test="${not empty list}">
								<c:forEach items="${list}" var = "list" varStatus="num">
									<tr onclick="empListClick('${list.empIdx}')" style="cursor: pointer;">
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
		</form>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録</title>
</head>

<body>
	<c:if test="${okMsg eq 01}">
		<script>
			alert("給料明細書が変更されました。");
			location.href="EmployeesListController?curPage=1&param=1";
		</script>
	</c:if>
	<c:if test="${okMsg eq 02}">
		<script>
			alert("社員情報が変更されました。");
			location.href="EmployeesListController?curPage=1&param=0";
		</script>
	</c:if>
	<c:if test="${okMsg eq 03}">
		<script>
			alert("社員情報が登録されました。");
			location.href="EmployeesListController?curPage=1&param=0";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 04}">
		<script>
			alert("給料情報ファイルが登録されました。");
			location.href="MoveTosalaryMain";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 05}">
		<script>
			alert("現場情報が変更されました。");
			location.href="FieldListController.do?curPage=1";
		</script>
	</c:if>
	<c:if test="${okMsg eq 06}">
		<script>
			alert("現場情報が登録されました。");
			location.href="FieldListController.do?curPage=1";
		</script>
	</c:if>		
	<c:if test="${okMsg eq 07}">
		<script>
			alert("パスワードが変更されました。");
			location.href="Main.do";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 08}">
		<script>
			alert("給料情報が登録されました。");
			location.href="EmployeesListController?curPage=1&param=1";
		</script>
	</c:if>		
	<c:if test="${okMsg eq 09}">
		<script>
			alert("勤務履歴が登録されました。");
			location.href="ConductModifyListController.do";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 10}">
		<script>
			alert("給料情報が登録されました。");
			location.href="SalaryMgtList";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 11}">
		<script>
			alert("給料情報が変更されました。");
			location.href="SalaryMgtList";
		</script>
	</c:if>			
	<c:if test="${okMsg eq 12}">
		<script>
			alert("新しいお知らせが登録されました。");
			location.href="NotifyListController.do?curPage=1";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 13}">
		<script>
			alert("お知らせが変更されました。");
			location.href="NotifyListController.do?curPage=1";
		</script>
	</c:if>	
	<c:if test="${okMsg eq 14}">
		<script>
			alert("お知らせが削除されました。");
			location.href="NotifyListController.do?curPage=1";
		</script>
	</c:if>		
	<c:if test="${okMsg eq 15}">
		<script>
			alert("ファイルが登録されました。");
			window.opener.parent.location.href='EmployeesListController?curPage=1&param=0';
			window.close();
		</script>
	</c:if>	
	<c:if test="${okMsg eq 16}">
		<script>
			alert("評価が登録されました。");
			location.href="EmployeesAppraisalController.do?curPage=1&param=0";
		</script>
	</c:if>
	<!-- 2021/03/05 現場情報登録 -->
	<c:if test="${okMsg eq 30}">
		<script>
			alert("現場情報が登録されました。");
			location.href="fieldList";
		</script>
	</c:if>	
	<!-- 2021/03/24 現場情報変更 -->
	<c:if test="${okMsg eq 31}">
		<script>
			alert("現場情報が変更されました。");
			location.href="fieldList";
		</script>
	</c:if>	
	
</body>
</html>
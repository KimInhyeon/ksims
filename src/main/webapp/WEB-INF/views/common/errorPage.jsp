<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>KS情報システム</title>
<%
response.setStatus(HttpServletResponse.SC_OK);
%>
</head>
<body>
	<c:if test="${errorMsg eq 01}">
		<script>
			alert("社員番号が重複されました。");
			history.back();
		</script>
	</c:if>
	<c:if test="${errorMsg eq 02}">
		<script>
			alert("正しい形式の写真ファイルじゃありません。");
			history.back();
		</script>
	</c:if>
	<c:if test="${errorMsg eq 03}">
		<script>
			alert("////");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 04}">
		<script>
			alert("IDまたはパスワードが間違いました。");
			history.back();
		</script>		
	</c:if>		
	<c:if test="${errorMsg eq 05}">
		<script>
			alert("この社員は給料を支給した履歴がないです。");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 06}">
		<script>
			alert("職級コードが重複されました。");
			history.back();
		</script>
	</c:if>		
	<c:if test="${errorMsg eq 07}">
		<script>
			alert("この社員は勤務履歴がありません。");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 08}">
		<script>
			alert("既存のパスワードが一致しません。");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 09}">
		<script>
			alert("既に確定された勤務履歴です。");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 10}">
		<script>
			alert("先月の履歴ありません。");
			history.back();
		</script>
	</c:if>	
	<c:if test="${errorMsg eq 11}">
		<script>
			alert("この社員は評価履歴がありません。");
			history.back();
		</script>
	</c:if>
	<!-- 2021/03/05 現場情報登録 -->
	<c:if test="${errorMsg eq 30}">
		<script>
		alert("管理者でログインせてください。");
		history.back();
		</script>
	</c:if>
	
</body>
</html>
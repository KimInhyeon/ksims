<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap cdn 설정 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script>
	function fieldDetail_move() {
		alert("詳細画面");
		fieldMainView.action = "fieldInfoDetailForm";
		fieldMainView.submit();
	}
	
	function modify() {
		alert("情報変更");
		fieldMainView.action = "fieldInfoModifyView";
		fieldMainView.submit();
	}
	function fieldEmpForm() {
		alert("社員配置画面");
		location.href="fieldEmpForm?redirectFlg='0'";
	}
</script>
</head>
<body>

<form method="post" name="fieldMainView">
<h1>現場情報概要</h1>
	<label style="padding :8px;">
		<input type="checkbox" name="chk_place_not_working">派遣社員がない現場を表示する。
	</label>
<select name="search_criteria">
	<option>検索条件</option>
	<option>現場名</option>
	<option>リーダー名</option>
</select>
<input type="text" name="place_search"><br>
<!-- 
<div style="position: relative; top:20px; left:290px; width:230px; height:180px; background-color: red;">o</div>
 -->
 <div style="position:relative; top: 350px;">
	<input type="button" value="新規登録"  onClick="location.href='fieldInfoRegistForm'">
	<input type="button" value="現場情報変更" onclick="modify()">
	<input type="button" value="社員配置" onclick="fieldEmpForm()">
</div>

<div style="position: absolute; left: 300px;">
	<img src="${pageContext.request.contextPath}/images/tokyoMap.jpg" />
</div>
<div style="position:relative; left:870px; background-color: gray; height:450px; width: 180px;"></div>


<div style="position:relative; left:300px; top: 80px; width: 500px;">
	<input type="text" name="field_code" style="width: 400px; height: 35px;">			<!--後で修正するとき、field_codeを field_nameで変更すること！-->
	<input type="button" value="詳細情報" onclick="fieldDetail_move()">
</div>


</form>
</body>
</html>
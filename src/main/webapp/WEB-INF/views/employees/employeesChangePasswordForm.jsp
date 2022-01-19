<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="./css/table.css"></link>
<link rel="stylesheet" type="text/css" href="./css/button.css"></link>
<script type="text/javascript">

function pageSubmit() {
	var pwcc = document.getElementById("passwordChangeConfirm").value;
	var pw = document.getElementById("passwordChange").value;
	var pwn = document.getElementById("passwordNow").value;
	
	if (confirm("保存しますか？") == true) {
		if ($("#passwordNow").val() == "") {
			alert("既存のパスワードを入力してください。");
			return false;
		} else if ($("#passwordChange").val().length < 4) {
			alert("新しいパスワードは4文字以上入力してください。");
			return false;
		} else if ($("#passwordChangeConfirm").val() == "") {
			alert("新しいパスワードを再入力してください。");
			return false;
		} else if (pwcc != pw) {
			alert("新しいパスワードとその確認入力が一致しません。");
			return false;
		} else if (pwcc == pwn) {
			alert("新しいパスワードを入力してください。");
			return false;
		}
		
		document.getElementById("password").action="EmployeesChangePasswordController"
		document.getElementById("password").submit()
	} else {
		return;
	}
}	
function checkPW(){
	var pw=passwordChange.value;
	if(pw.length<4){
		$("#pwcMessage").text("4文字以上入力してください。");
	}else{
		$("#pwcMessage").text("");
	}
}
function checkPW2(){
	var pw=passwordChange.value;
	var pw2=passwordChangeConfirm.value;
	if(pw!=pw2){
		$("#pwc2Message").text("パスワードが一致しません");
	}else{
		$("#pwc2Message").text("");
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
	th{background:#bee5eb;}
	p{color:red; font-weight: normal; font-size: 7px; width:150px; display:block;}
</style>
</head>

<body>

<div class="content_margin_small">
<form method="post" name="password" id="password">
	<h1><i class="caret square left icon"></i>パスワード変更</h1>
	<div class="ui grid">
		<div class="column">
			<table class="table table-sm table-bordered">
				<tr>
					<th scope="row">既存パスワード</th>
					<td>
					<div class="textbox">
					<input class="form-control" name="passwordNow" id="passwordNow" type="password" value="" maxlength="20" autofocus/>
					</div>
					</td>
				</tr>
				<tr>
					<th scope="row">変更パスワード<br><p id="pwcMessage">&nbsp;</p></th>
					<td>
					<div class="textbox">
					<input class="form-control" name="passwordChange" id="passwordChange" type="password" value="" maxlength="20" onkeyup="checkPW()" onchange="checkPW2()" placeholder="4~20文字"/>
					</div>
					</td>
				</tr>
				<tr>
					<th scope="row">パスワード確認 <br><p id="pwc2Message">&nbsp;</p></th>
					<td>
					<div class="textbox">
					<input class="form-control" name="passwordChangeConfirm" id="passwordChangeConfirm" type="password" value="" maxlength="20" onkeyup="checkPW2()"/>
					</div>
					</td>
				</tr>
			</table>
		<!-- button box -->
		<div class="ui">
			<div class="ui button left floated" onclick="javascript:location.href='${pageContext.request.contextPath}/main'"><i class="left chevron icon"></i>戻る</div>
			<div class="ui blue button right floated" onClick="pageSubmit();">更新</div>
		</div>			
	</div>
</div>
</form>
</div>
</body>
</html>
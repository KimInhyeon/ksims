<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>社員配置・リーダー設定</title>
<style>
button{cursor: pointer;}
body {
	margin: 0;
	padding: 0;
}
span {
	font-size: 15px;
	text-overflow: ellipsis;
}

text {
	font-size: 15px;
}
.field_box_cls{
 border: 1px;
 border-radius: 0.5em;
 padding: 5px;
 width: 100%;
 background-color: white;
 overflow: hidden; 
 text-overflow: ellipsis; 
 white-space: nowrap;
}

.left-box {
	background-color: rgba(134, 238, 255, 0.2);
/* 	float: left; */
	width: 340px;
	padding: 10px;
	margin: 10px;
	overflow: auto;
	height: 400px;
	position: absolute;
	/* left: 150px; */
	top: 25.5px;
}

.right-box {
	background-color: rgba(134, 238, 255, 0.2);
	width: 500px;
	padding: 10px;
	margin: 10px;
	overflow: auto;
	height: 400px;
	position: absolute;
	left: 590px;
	top: 25.5px;
}
/* .footer {
	position: absolute;
	top: 530px;
} */

.margin_div {
  margin-left: -100px!important;
}
</style>
</head>

<script>
$(document).ready(function(){
		$("input:checkbox[name=left_check]").hide();
	});

var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}
	
//現場 div, 社員 labelクリックすると出力
function field_check(str) {
	//alert(str);
	var checkbox = ".checkbox_"+str;
	var field_box = "#field_box_"+str;
	var radio_box = "#radio_"+str;
	
	//　初期化
	$("input:checkbox[name=left_check]").hide();
	$(".field_box_cls").css("background-color","white");
	//選択したとき変更する。
	$(radio_box).prop("checked", true);
	$(checkbox).show();
	$(field_box).css("background-color","#eaeaea");
}

//　リーダーボタンクリックevent
$(document).ready(function() { 
$('#left_list [id="leader_set"]').click(function() {
	//var check = ".check_"+num;
	var field_code = $("input:radio[name=field_radio]:checked").val();
	var checkbox = ".checkbox_"+field_code;
	//alert($("input:radio[name=field_radio]:checked").val());	//　現場コード
	
	if($("input:radio[name=field_radio]:checked").length <1){
		alert("現場を選択してください。");
		return false;
	}else{	//radio ボタン押下する時
		if($(checkbox+':checked').length == 0){
			alert("社員をチェックしてください。");
		}else{
//			alert($(checkbox+':checked').length);
			var flug = true;
			var left_check = new Array();
			//리더 체크
			$(checkbox+':checked').each(function(i) {	//　リーダーチェックfor文
//				alert($(this).val());
				var leader = $(this).val();
				left_check.push($(this).val());
				if($('#'+field_code+'_'+leader).val() == 'Y'){
					flug = false
				}
			});
			if(flug){
				$('#left_check_result').val(left_check);
				left_list.action = "fieldEmpLeaderSet";
				
				if(doubleSubmitCheck()){
					return;
				}
				left_list.submit();
			}else{
				alert("リーダーの社員がいます。");
			}		//　リーダー確認 IF文 END
		}		//　社員チェック確認IF文 END
	}		//　現場チェック if文 END
});//　リーダーボタンクリックfunction END
});

$(document).ready(function() { 
$('#left_list [id="leader_unset"]').click(function() {
	var field_code = $("input:radio[name=field_radio]:checked").val();
	var checkbox = ".checkbox_"+field_code;
	
	if($("input:radio[name=field_radio]:checked").length <1){
		alert("現場を選択してください。");
		return false;
	}else{	//radio ボタン押下する時
		if($(checkbox+':checked').length == 0){
			alert("社員をチェックしてください。");
		}else{
			var flug = true;
			var left_check = new Array();
			//리더 체크
			$(checkbox+':checked').each(function(i) {	//　リーダーチェックfor文
//				alert($(this).val());
				var leader = $(this).val();
				
				left_check.push($(this).val());
				
				if($('#'+field_code+'_'+leader).val() == 'N'){
					flug = false
				}
			});	//　リーダーチェックfor文 End
			if(flug){
				$('#left_check_result').val(left_check);
				left_list.action = "fieldEmpLeaderUnset";
				if(doubleSubmitCheck()){
					return;
				}
				left_list.submit();
			}else{
				alert("リーダーではない社員を選択してください。");
			}		//　リーダー確認 IF文 END
		}		//　社員チェック確認IF文 END
	}		//　現場チェック if文 END
});//　リーダーボタンクリックfunction END
});
// (<<)ボタン
function emp_in_submit() {
	var field_code = $("input:radio[name=field_radio]:checked").val();
	var checkbox = ".checkbox_"+field_code;

	//現場チェック
	if($("input:radio[name=field_radio]:checked").length <1){
		alert("現場を選択してください。");
		return false;
	}else{
		var field_emp_size = new Array();	//　選択した現場の社員
		var right_check = new Array();		//　右側のcheckした社員
		
		if($("input[name=right_check]:checked").val() == null){
			alert("社員をチェックしてください。");
			return false;
		}else{
			var flug = true;
			//　現場の社員チェック
			$(checkbox).each(function(i) {	//　リーダーチェックfor文
				field_emp_size.push($(this).val());
			});
			//　社員チェック
		    $("input[name=right_check]:checked").each(function(i) {
		    	right_check.push($(this).val());
		    	//alert($(this).val());
		    });
			for(var i=0; i<field_emp_size.length; i++){
				for(var j=0; j<right_check.length; j++){
					if(field_emp_size[i] == right_check[j]){
						flug= false;
					}
				}
			}
			if(flug){
				$('#right_field_code').val(field_code);
				$('#right_check_result').val(right_check);
				right_list.action = "fieldEmpIn";
				if(doubleSubmitCheck()){
					return;
				}
				right_list.submit();
			}else{
				alert("同じ現場に派遣されている社員が存在します。");
			}
		}		//社員チェック　IF文　End
	}		//現場チェック IF文　End
}

//(>>)　ボタン
function emp_out_submit() {
	var field_code = $("input:radio[name=field_radio]:checked").val();

	if($("input:radio[name=field_radio]:checked").length <1){
		alert("現場を選択してください。");
		return false;
	}else{
		var left_check = new Array();
		
		var checkClass = '.checkbox_'+field_code;
		
	    $(checkClass+":checked").each(function(i) {
	    	left_check.push($(this).val());
	    });
	    
		if($(checkClass+":checked").val() == null){
			alert("社員をチェックしてください。");
			return false;
		}
		$('#left_check_result').val(left_check);
		left_list.action = "fieldEmpOut";
		if(doubleSubmitCheck()){
			return;
		}
		left_list.submit();
	}
}
</script>

<script type="text/javascript">
function back() {
	location.href="fieldList";
}

//Enter keyで検索
function searchEmp() {
    //if (window.event.keyCode == 13) {
		search_from.action = "fieldEmpForm";
		search_from.submit();
    //}
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

<body>
	<div class="content_margin" style="margin: 30px; height: 550px;">
		<div style="position: absolute;left: 50%;transform:translate(-50%);width: 1000px;">
		<!-- 検索Form -->
			<form name="search_from" id="search_from" method="get">
				<input type="text" class="form-control" name="field_search" value="${field_search}" size="26"  style="position: absolute; top: 2px; left: 10px; width:260px;margin-left:-100px" >
				<button type="button"  class="ui icon button" style="position: absolute;left: 172px; width: 80px; height: 35px; " onClick="searchEmp()">検索</button>
				<input type="text" class="form-control" name="emp_search" value="${emp_search}" size="26"  style="position: absolute; top: 2px; left: 600px; width:400px;margin-left:-100px" ">
				<button type="button"  class="ui icon button" style="position: absolute;left: 904px; width: 80px; height: 35px; " onClick="searchEmp()">検索</button>
				<input type="hidden" id="messageFlg" name="messageFlg" value="0">
			</form>
		
	<div  style="margin-top:-50px!important;margin-left:-75px!important;"><h1><i class="caret square left icon"></i>社員配置・リーダー設定</h1></div>
		<!--　左リスト -->
		<form id="left_list" name="left_list" method="post">
		<div class="margin_div">
			<input type="hidden" name="left_check_result" id="left_check_result">
			<div class='left-box'>
				<div id="leftListAjax">
					<!-- field List -->
					<c:forEach items="${fieldList}" var="list">		<!-- 01 02 03 04 55 -->
						<input type="radio" name="field_radio" value="${list.field_code }" id="radio_${list.field_code }" style="display: none;">	<!-- id= radio_01 radio_02 ... -->
						<label for="radio_${list.field_code }" onclick="field_check('${list.field_code }')" style="width: 300px;">		<!--labelクリックすると  01 || 02 || 03 を送信  -->
							<div id="field_box_${list.field_code }" class="field_box_cls">		<!-- back-ground-color div  -->
								<p style="margin: 5px;"><strong>${list.field_name }</strong></p>
	
								<!-- emp list -->
								<c:forEach items="${field_emp_list}" var="list2">
									<c:if test="${list.field_code eq list2.field_code }">
										<label style="margin: 2px 5px; /* line-height: 150%; */">&nbsp;&nbsp;
										<input type="checkbox" name="left_check" class="checkbox_${list.field_code }" value="${list2.emp_id }">		<!-- check_01 || check_03 || check_55 ... -->
											<span style="margin-right: 10px;">${list2.emp_name }</span>[${list2.emp_id}]
											<c:if test="${list2.leader_check eq 'Y'}"><b><img src="images/leader.png" style="width: 25px; margin-bottom: -5px;"></b></c:if>
											<input type="hidden" id="${list2.field_code }_${list2.emp_id}" value="${list2.leader_check}"><br>
										</label>
										<br>
									</c:if>
								</c:forEach>
							</div>
						</label><br>
					</c:forEach>
				</div>
			</div>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				<button type="button" id="leader_set" name="leader_set" class="ui button" style="margin-left: 10px; width: 130px; height: 40px; background-color: rgb(52, 152, 219); color: white;" >リーダー設定</button>
				<button type="button" id="leader_unset" class="ui button" style="width: 130px; height: 40px; margin-left: 10px; background-color:tomato; color:white;" onclick="leader_unset()">リーダー取消</button>
				<button type="button" class="ui button" data-dismiss="modal" style="width: 90px; height: 40px; margin-left: 700px;" onclick="back()">戻る</button>
		</div>	
		</form>
	
		<!-- 中央ボタン (<<) (>>) -->
		<div class="margin_div">
		    <button id="r_button" class="ui button" style="width: 100px; height: 45px; border: 0; border-radius: 100px/60px; outline: 0; position: absolute; left: 430px; top: 130px;margin-left:-100px" onclick="emp_in_submit()">＜＜</button>
		    <button id="l_button" class="ui button"
			    style="width: 100px; height: 45px; border: 0; border-radius: 100px/60px; outline: 0; position: absolute; left: 430px; top: 240px;margin-left:-100px" onclick="emp_out_submit()">＞＞</button>
	    </div>
		<!-- 右リスト -->
			<form id="right_list" name="right_list" method="post">
			<div class='right-box' style="margin-left:-90px!important">
			<input type="hidden" name="right_check_result" id="right_check_result">
			<input type="hidden" name="right_field_code" id="right_field_code">
				<div id="RightListAjax">
					<c:forEach items="${emp_all_list}" var="list">
						<label style="line-height: 250%; border-radius: 0.5em; width: 100%; background-color: white; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; ">
							<input type="checkbox" name="right_check" value=${list.emp_id } style="margin-inline-start:10px;">&nbsp;&nbsp;
							<span>${list.position_name}</span>&nbsp;&nbsp;<span style="margin-right: 10px;">${list.emp_name}</span>[${list.emp_id}]
							<span title="${list.field_name}">
								<c:if test="${list.field_code ne '0000'}">(${list.field_name})</c:if> <!-- 本社がない場合、現場名出力  -->
							</span>
						</label>
					</c:forEach>
				</div>
			</div>
	    </form>
		</div>
	</div>
</body>
</html>
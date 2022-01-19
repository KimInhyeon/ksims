<%@page import="org.apache.velocity.tools.view.WebappUberspector.SetAttributeExecutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<script>
$(document).ready(function () {
	$('#reg_date').datepicker({
	closeText: '閉じる',
	prevText: '先月',
	nextText: '来月',
	currentText: '今日',
	monthNames: ['1月(JAN)','2月(FEB)','3月(MAR)','4月(APR)','5月(MAY)','6月(JUN)',
	'7月(JUL)','8月(AUG)','9月(SEP)','10月(OCT)','11月(NOV)','12月(DEC)'],
	monthNamesShort: ['1月','2月','3月','4月','5月','6月',
	'7月','8月','9月','10月','11月','12月'],
	dayNames: ['日','月','火','水','木','金','土'],
	dayNamesShort: ['日','月','火','水','木','金','土'],
	dayNamesMin: ['日','月','火','水','木','金','土'],		
	dateFormat : 'yy-mm-dd',
	changeYear: true,
	});
	//$('#work_start_time').timepicker({ timeFormat: 'H:i'});
	//$('#work_end_time').timepicker({ timeFormat: 'H:i'});
	//$('#work_break_time').timepicker({ timeFormat: 'H:i'});
	
	//ajax field_code 
	$("input[name='field_code']").focusout(function(){
		var chk = $('#field_code').val();
	if(chk != ""){
		$.ajax({
			url : "codeCheck",
			type : "GET",
			data : { field_code : chk},
			success : function(data) {
				if(data == 0){
					$('#code_result').val(data);
					$('#checkMsg').html('<span style="color:blue">登録可能</span>');
				}else{
					$('#code_result').val(data);
					$('#checkMsg').html('<span style="color:red">登録不可</span>'); 
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	}//　重複チェック　End
	});		//field code end
	
	//field_code 初期化
	$('#field_code').focusin(function(){
//		$('#field_code').val('');
		$('#code_result').val('');
		$('#checkMsg').html('');
	});
}); //ready End

//dblClick 방지
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

function moveFocus(textbox){
	 if(textbox.value.length == 4){
		 fieldRegist.reg_date.focus();
	 }
}

function field_submit() {
	var time =  /^([01][0-9]|2[0-3]):([0-5][0-9])$/;
	var date_pattern = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
//	var code_pattern = /^\d{2}$/;
	
	if (confirm("登録しますか？") == true) {
		if($("#field_name").val() == "") {
			alert("現場名を入力してください。");
			$("#field_name").focus();
			return false;
		}else if($("#field_addr1").val() == ""){
			alert("現場住所を入力してください。");
			$("#addr_select").focus();
			return false;
		}else if($("#field_difficulty").val() == -1){
			alert("難易度を選んでください。");
			$("#field_difficulty").focus();
			return false;
		}else if(fieldRegist.code_result.value == ""){
			alert("現場コードを入力してください。");
			return false;
		}else if(fieldRegist.code_result.value == 1){
			alert("現場コードが重複されました。");
			return false;
		}else if(fieldRegist.code_result.value == -1){
			alert("現場コードが正しくありません。");
			return false;
		}else if(!time.test(fieldRegist.work_start_time.value)){
			alert("勤務開始時間が正しくありません");
			fieldRegist.work_start_time.focus();
			return false;
		}else if(!time.test(fieldRegist.work_end_time.value)){
			alert("勤務終了時間が正しくありません");
			fieldRegist.work_end_time.focus();
			return false;
		}else if($("#work_break_time1").val() == ""){
			alert("休み時間(昼休み)を入力してください");
			$("#work_break_time1").focus();
			return false;
		}else if($("#work_break_time2").val() == ""){
			alert("休み時間(その他)を入力してください");
			$("#work_break_time2").focus();
			return false;
		}else if(!date_pattern.test(fieldRegist.reg_date.value)){
			alert("登録日が正しくありません。");
			fieldRegist.reg_date.focus();
			return false;
		}
		if(doubleSubmitCheck()){
			return;
		}
		fieldRegist.submit();
	}else{
		return;
	}
}
function locationURL() {
	if (confirm("入力内容をクリアしますか？") == true) {
		$('#checkMsg').html(''); 
		location.href = "fieldInfoClear"
	}else{
		return;
	}
}

function SelectChange() {
	const idx=fieldRegist.addr_select.selectedIndex;
	if(fieldRegist.addr_select.value == ""){			//　地域選択
		fieldRegist.field_addr1.value="";
		fieldRegist.field_addr1.readOnly = true;
	}else if(fieldRegist.addr_select[idx].text == "その他"){	//　その他
		fieldRegist.field_addr1.value="";
		fieldRegist.field_addr1.readOnly = false;
		fieldRegist.field_addr1.focus();
	}
	else{
		var area_list = ${json};
		$(area_list).each(function(index , value){
			// 23区の場合
			if(fieldRegist.addr_select.value == value.field_area_code && fieldRegist.addr_select.value <= 23){
		  		//console.log(value.field_area_name);
				fieldRegist.field_addr1.value = "東京都" + value.field_area_name;
				fieldRegist.field_addr1.readOnly = true;
			// 23区以外の地域の場合
			}else if(fieldRegist.addr_select.value == value.field_area_code && fieldRegist.addr_select.value > 23){
				fieldRegist.field_addr1.value = value.field_area_name;
				fieldRegist.field_addr1.readOnly = true;
			}
		});
	}
}
</script>
<style>
th{vertical-align:middle; text-align: right; background:#e9ecef;}
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
<div class="content_margin" style="height:500px;">
	<div style="position: absolute; left: 50%; transform:translate(-50%); width: 800px;">
		<h1><i class="caret square left icon"></i>現場情報登録</h1>
		<form method="post" name="fieldRegist" action="fieldInfoRegist_chk">
			<table class="table-bordered" style="border-bottom:none; border-left:none;border-right:none">
				<tr>
					<th class="table-info" ><label for="field_name"><font color="red">＊</font>現場名</label></th>
					<td ><input type="text" class="form-control" name="field_name" id="field_name" maxlength="25" autofocus="autofocus"></td>
					
					<th class="table-info"><label for="work_start_time"><font color="red">＊</font>勤務開始時間</label></th>
                    <td >
                    	<input type="time"  class="form-control"  name="work_start_time" id="work_start_time"  style="width: 150px;" value="">
					</td>
				</tr>
				
				<tr>
					<th class="table-info"><font color="red">＊</font>現場住所</th>
					<td >
						<select name="addr_select" id="addr_select" onchange="SelectChange()" class="ui search selection dropdown" style="height: 40px !important; padding: 3px !important;" >
						<option value="" selected>地域選択</option>
						<c:forEach items="${field_area}" var="list">
							<option value="${list.field_area_code}">${list.field_area_name }</option>
						</c:forEach>
					</select> 
					</td>
					<th class="table-info"><label for="work_end_time"><font color="red">＊</font>勤務終了時間</label></th>
					<td >
                    	<input type="time"  class="form-control" value=""  id="work_end_time" name="work_end_time"  class="form-control"  style="width: 150px;" >
                    </td>	
				</tr>
				<tr>
					<th class="table-info"><label for="field_addr1">住所1</label></th>
					<td><input type="text" class="form-control" name="field_addr1" id="field_addr1" maxlength="20" placeholder="地域を選択してください" readonly="readonly"></td>
					
					<th class="table-info"><label for="work_break_time1"><font color="red">＊</font>休み時間</label></th>
					<td>
						<table>
								<td style="heght:12px;border:none;">昼休み(分)<input type="number"  class="form-control" name="work_break_time1" id="work_break_time1" min="0" max="180" style="width:80px"></td>
								<td style="heght:12px;border:none;">夜間(分)<input type="number"  class="form-control" name="work_break_time2" id="work_break_time2" min="0" max="180" style="width:80px"></td>
						</table>		
					</td>		
				</tr>
				<tr>
					<th class="table-info"><label for="field_addr2">住所2</label></th>
					<td><input type="text" class="form-control" name="field_addr2" id="field_addr2" maxlength="30" ></td>
					
					<th class="table-info"><font color="red">＊</font>難易度</th>
					<td >
						<select name="field_difficulty" id="field_difficulty" class="ui search selection dropdown"  style="width: 70px; height: 30px; padding: 3px !important;" >
							<option value="-1">選択</option>
							<option value="上">上</option>
							<option value="中">中</option>
							<option value="下">下</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="table-info"><label for="field_code"><font color="red">＊</font>現場コード</label></th>
					<td >
						<div style="float:left;">
							<input type="text" class="form-control" size="2" name="field_code" id="field_code" maxlength="4" value="${code_num }" 
							onKeyUp="moveFocus(this)" style="width: 60px;" >
						</div>
						<div class="checkMsg" id="checkMsg" style="display:inline-block; line-height:30px; margin: 0 20px;"></div>
						<div><input type="hidden" id="code_result" name="code_result" value="0"></div>
					</td>
					<th class="table-info"><label for="reg_date"><font color="red">＊</font>登録日</label></th>
					<td ><input type="text" class="form-control" name="reg_date" id="reg_date" maxlength="10" autocomplete="off"></td>
				</tr>
				<tr>
					<th class="table-info"><label for="field_tool">ツール</label></th>
					<td><input type="text" class="form-control" name="field_tool" id="field_tool" maxlength="100"></td>
					<th class="table-info"><label for="field_env">作業環境</label></th>
					<td><input type="text" class="form-control" name="field_env" id="field_env" maxlength="100"></td>
				</tr>
				<tr>
					<th class="table-info" style="text-align: right; vertical-align: top;"><label for="field_memo">メモ</label></th>
					<td colspan="4"><textarea maxLength="100" class="form-control" name="field_memo" id="field_memo" rows="5" cols="77" style="resize: none;" ></textarea></td>
				</tr>
				<tr style="border:none;">
				<td colspan="4" style="border:none" align="right">
					<input type="button" class="btn btn-primary" value="現場登録" name="btn_submit" onclick="field_submit()" style="background-color: rgb(52, 152, 219); color: white;" >&nbsp;&nbsp;
					<input type="button" class="btn btn-warning" value="クリア" onclick="locationURL()" >&nbsp;&nbsp;
					<input type="button" class="btn btn-secondary" value="戻る " onclick="location.href='fieldList'">
					
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>現場情報変更</title>
<script>
$(document).ready(function () {
	$('#field_addr1').one("click",function(){
		var addr_code = $("select[name=addr_select] option:selected").val();
		if(addr_code == 0){
			console.log(addr_code);
			$('#field_addr1').prop('readonly', false);
		}
	});
	
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
});
</script>

<script type="text/javascript">
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

function field_submit() {
	var time =  /^([01][0-9]|2[0-3]):([0-5][0-9])$/;
	var date_pattern = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
	
	if (confirm("登録しますか？") == true) {
		if($("#field_name").val() == "") {
			alert("現場名を入力してください。");
			return false;
		}else if($("#field_addr1").val() == ""){
			alert("現場住所を選択してください。");
			return false;
		}else if($("#field_difficulty").val() == -1){
			alert("難易度を選んでください。");
			return false;
		}else if(!time.test(fieldModify.work_start_time.value)){
			alert("勤務開始時間が正しくありません");
			return false;
		}else if(!time.test(fieldModify.work_end_time.value)){
			alert("勤務終了時間が正しくありません");
			return false;
		}else if($("#work_break_time1").val() == ""){
			alert("昼総休み時間が正しくありません");
			return false;
		}else if($("#work_break_time2").val() == ""){
			alert("その他休み時間が正しくありません");
			return false;
		}else if(!date_pattern.test(fieldModify.reg_date.value)){
			alert("登録日が正しくありません。");
			return false;
		}
		$('#field_code').removeAttr("disabled");
		$('#reg_date').removeAttr("disabled");
		
		if(doubleSubmitCheck()){
			return;
		}
		
		
		fieldModify.submit();
	}else{
		return false;
	}
}

function SelectChange() {
	const idx=fieldModify.addr_select.selectedIndex;
//	alert(idx);
	if(fieldModify.addr_select.value == "-1"){			//　地域選択
		fieldModify.field_addr1.value="";
		fieldModify.field_addr1.readOnly = true;
	}else if(fieldModify.addr_select[idx].value == "99"){	//　その他
		fieldModify.field_addr1.value="";
		fieldModify.field_addr1.readOnly = false;
		fieldModify.field_addr1.focus();
	}else{
		var area_list = ${json};
		$(area_list).each(function(index , value){
			// 23区の場合
			if(fieldModify.addr_select.value == value.field_area_code && fieldModify.addr_select.value <= 23){
		  		//console.log(value.field_area_name);
				fieldModify.field_addr1.value = "東京都" + value.field_area_name;
				fieldModify.field_addr1.readOnly = true;
			// 23区以外の地域の場合
			}else if(fieldModify.addr_select.value == value.field_area_code && fieldModify.addr_select.value > 23){
				fieldModify.field_addr1.value = value.field_area_name;
				fieldModify.field_addr1.readOnly = true;
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
<div class="content_margin" style="margin: 30px; height:500px;">
	<div style="position: absolute; left:50%; transform:translate(-50%); width: 800px; margin-top: -30px;">
		<h1><i class="caret square left icon"></i>現場情報変更</h1>
		<form method="post" name="fieldModify" action="fieldInfoModifyChk">
		<table class="table-bordered" style="border-bottom:none; border-left:none;border-right:none;">
			<tr>
				<th class="table-info"><label for="field_name"><font color="red">＊</font>現場名</label></th>
				<td><input type="text" class="form-control" name="field_name" id="field_name" maxlength="25" value="${list.field_name}"></td>
				
				<th class="table-info"><label for="work_start_time"><font color="red">＊</font>勤務開始時間</label></th>
				<td><input type="time" class="form-control" name="work_start_time" id="work_start_time" value="${start_time}" ></td>
		<!-- <input type="text" class="time" id="timepicker">	 -->
			</tr>
			<tr>
				<th class="table-info"><font color="red">＊</font>現場住所</th>
				<td>
				<select name="addr_select" id="addr_select" onchange="SelectChange()"  class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;">
					<option value="-1">地域選択</option>
					<c:forEach items="${field_area}" var="list" varStatus="stu">
						<option value="${list.field_area_code }"
						<c:if test="${area_idx eq stu.count}">selected</c:if>
						>${list.field_area_name }</option>
					</c:forEach>
				</select>
				</td>
				
				<th class="table-info"><label for="work_end_time"><font color="red">＊</font>勤務終了時間</label></th>	
				<td><input type="time" class="form-control" name="work_end_time" id="work_end_time" value="${end_time }" ></td>
			</tr>
			<tr>
				<th class="table-info"><label for="field_addr1">住所1</label></th>
				<td><input type="text" class="form-control" name="field_addr1" id="field_addr1" maxlength="25" 
				placeholder="地域を選択してください" value="${area_name }" <c:if test="${area_idx ne 27 }">readonly</c:if>>
				</td>
				
				<th class="table-info"><label for="work_break_time"><font color="red">＊</font>休み時間</label></th>
				<td>
				<table>
					<td style="border:none">昼休み(分)<input type="number" class="form-control" name="work_break_time1" id="work_break_time1" value="${list.work_break_time1}" maxlength="5" style="width:80px"></td>
					<td style="border:none">夜間(分)<input type="number" class="form-control" name="work_break_time2" id="work_break_time2" value="${list.work_break_time2}" maxlength="5" style="width:80px"></td>
				</table>	
			</tr>
			<tr>
				<th class="table-info"><label for="field_addr2">住所2</label></th>
				<td><input type="text" class="form-control" name="field_addr2" id="field_addr2" value="${area_name2 }" maxlength="30"></td>
			
				<th class="table-info"><font color="red">＊</font>難易度</th>
				<td>
					<select name="field_difficulty" id="field_difficulty" class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;">
						<option value="-1">選択</option>
						<option value="上" <c:if test="${list.field_difficulty eq '上'}">selected</c:if>>上</option>
						<option value="中" <c:if test="${list.field_difficulty eq '中'}">selected</c:if>>中</option>
						<option value="下" <c:if test="${list.field_difficulty eq '下'}">selected</c:if>>下</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th class="table-info"><label for="field_code"><font color="red">＊</font>現場コード</label></th>
				<td>
					<input type="text" size="2" class="form-control" name="field_code" id="field_code" value="${list.field_code }" disabled="disabled">
		<!--  			
					<div class="checkMsg" id="checkMsg" style="float:right; width: 60%;"></div>
					<div><input type="hidden" id="code_result" name="code_result"></div>
		-->			
				</td>
				<th class="table-info"><label for="reg_date"><font color="red">＊</font>登録日</label></th>
				<td><input type="text" class="form-control" name="reg_date" id="reg_date" value="${regDate}" disabled="disabled"></td>
			</tr>
			
			<tr>
				<th class="table-info"><label for="field_tool">ツール</label></th>
				<td><input type="text" class="form-control" name="field_tool" id="field_tool" value="${list.field_tool}" maxlength="100"></td>
				
				<th class="table-info"><label for="field_env">作業環境</label></th>
				<td><input type="text" class="form-control" name="field_env" id="field_env" value="${list.field_env}" maxlength="100"></td>
			</tr>
			
			<tr>
				<th class="table-info" style="text-align: right; vertical-align: top;"><label for="field_memo">メモ</label></th>
				<td colspan="4"><textarea maxLength="100" class="form-control" name="field_memo" id="field_memo" rows="5" cols="77" style="resize: none;" >${list.field_memo }</textarea></td>
			</tr>
			
			<tr style="border:none;">
				<td colspan="4" style="border:none;" align="right">
					<input type="button" class="ui button" value="現場変更" name="btn_submit" onclick="field_submit()" style="background-color: rgb(52, 152, 219); color: white;">
					<input type="button" class="ui button" value="戻る" style="margin-right:-10px" onclick="location.href='fieldList'">&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>
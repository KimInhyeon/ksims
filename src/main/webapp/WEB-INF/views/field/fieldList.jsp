<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style>
button{border: 0; background-color: unset;}
label{cursor: pointer;}
.jb-wrap {
	width: 700px;
 	margin: 10px auto;
/* 	border: 1px solid #000000;  */
	position: relative;
}
.jb-wrap img {
	width: 100%;
	vertical-align: middle;
}
.jb{
	display:table;
	position: absolute;
	transform: translate( -50%, -50% );
	
}

.jb.text1 {/* 練馬区 */
	/* padding: 10% 11%; */
	width: 28%;
	height: 11%;
 	/* background-color: #FFFF00; */ 
	top: 16%;
	left: 14%;
}
.jb.text2 {/* 板橋区 */
	/* padding: 10% 10%; */
	width: 20%;
	height: 11%;
	/* background-color: #FFFF33; */
	top: 7%;
	left: 29.5%;
}
.jb.text3 {/* 北区 */
	/* padding: 11% 17%; */
	width: 10%;
	height: 17%;
	/* background-color: #FFFF66; */
	top: 11%;
	left: 45%;
}
.jb.text4 {/* 豊島区 */
/* 	padding: 12% 11%; */
	width: 16%;
	height: 6%;
 	/* background-color: #FFFF99; */
	top: 22.5%;
	left: 37.5%;
}
.jb.text5 {/* 足立区 */
/* 	padding: 12% 15%; */
	width: 31%;
	height: 16%;
	/* background-color: #FFFFCC; */
	top: 8%; 
	left: 68%;
}
.jb.text6 {/* 荒川区 */
/* 	padding: 12% 19%; */
	width: 13%;
	height: 9%;
	/* background-color: #FF00FF; */
	top: 21.5%;
	left: 59%;
}
.jb.text7 {/* 杉並区 */
/* 	padding: 10% 17%; */
	width: 15%;
	height: 10%;
	/* background-color: #FF33FF; */
	top: 34%;
	left: 9%;
}
.jb.text8 {/* 中野区 */
/* 	padding: 10% 17%; */
	width: 8%;
	height: 14%;
	/* background-color: #FF66FF; */
	top: 32%;
	left: 21%;
}
.jb.text9 {/* 新宿区 */
/* 	padding: 10% 17%; */
	width: 12%;
	height: 13%;
	/* background-color: #FF99FF; */
	top: 32.5%;
	left: 31%;
}
.jb.text10 {/* 文京区 */
/* 	padding: 10% 17%; */
	width: 12.5%;
	height: 11%;
	/* background-color: #FFCCFF; */
	top: 31.5%;
	left: 43.5%;
}
.jb.text11 {/* 台東区 */
/* 	padding: 10% 17%; */
	width: 11%;
	height: 11%;
	/* background-color: #00FFFF; */
	top: 32%;
	left: 56%;
}
.jb.text12 {/* 墨田区 */
/* 	padding: 10% 17%; */
	width: 10%;
	height: 13%;
	/* background-color: #33FFFF; */
	top: 31%;
	left: 68%;
}
.jb.text13 {/* 葛飾区 */
/* 	padding: 10% 17%; */
	width: 13%;
	height: 17%;
	/* background-color: #66FFFF; */
	top: 25%;
	left: 80%;
}
.jb.text14 {/* 世田谷区 */
/* 	padding: 10% 17%; */
	width: 22%;
	height: 18%;
	/* background-color: #99FFFF; */
	top: 50%;
	left: 11.5%;
}
.jb.text15 {/* 渋谷区 */
/* 	padding: 10% 17%; */
	width: 12%;
	height: 10%;
	/* background-color: #CCFFFF; */
	top: 45%;
	left: 31%;
}
.jb.text16 {/* 千代田区 */
/* 	padding: 10% 17%; */
	width: 11.5%;
	height: 9.5%;
	/* background-color: #FF0000; */
	top: 43%;
	left: 43.5%;
}
.jb.text17 {/* 中央区 */
/* 	padding: 10% 17%; */
	width: 8%;
	height: 16%;
	/* background-color: #FF0033; */
	top: 46%;
	left: 54%;
}
.jb.text18 {/* 江東区 */
/* 	padding: 10% 17%; */
	width: 20.5%;
	height: 22%;
	/* background-color: #FF0066; */
	top: 49%;
	left: 69.2%;
}
.jb.text19 {/* 江戸川区 */
/* 	padding: 10% 17%; */
	width: 17.5%;
	height: 20%;
	/* background-color: #FF0099; */
	top: 44%;
	left: 89%;
}
.jb.text20 {/* 目黒区 */
/* 	padding: 10% 17%; */
	width: 11%;
	height: 10%;
	/* background-color: #FF00CC; */
	top: 58%;
	left: 28%;
}
.jb.text21 {/* 港区 */
/* 	padding: 10% 17%; */
	width: 10%;
	height: 16.5%;
	/* background-color: #FF3300; */
	top: 56.5%;
	left: 39.5%;
}
.jb.text22 {/* 品川区 */
/* 	padding: 10% 17%; */
	width: 17%;
	height: 12%;
	/* background-color: #FF6600; */
	top: 71.5%;
	left: 36%;
}
.jb.text23 {/* 大田区 */
/* 	padding: 10% 17%; */
	width: 30%;
	height: 14%;
	/* background-color: #FF6600; */
	top: 84.5%;
	left: 31%;
}

.jb.text99 {/* 東京外 */
/* 	padding: 10% 17%; */
	width: 41%;
	height: 20%;
	top: 89.5%;
	left: 79%;
}

.jb spen{
/* 	display:table-cell; */
/* text-align: center; */
/* 	vertical-align:middle; */
	
	position: absolute;
	top: 50%;
	left: 46.8%;
	transform: translate(-50%, -50%);
	text-align: center;
}
.cool{
/* width: 16%!important; */
	width: 18px!important;
	display: inline-block;
}
.marker_sort{
/*  display:flex;
flex-wrap: wrap;
/* margin-top: -1rem;
margin-bottom: -1rem; */
/* margin-right: -2rem; */
/* overflow: hidden; */
height: 100%;
width: 100%;

position: relative;
}
/* 버튼 툴팁 */
.popupLayer {
	position: absolute;
	display: none;
	background-color: azure;
	border: solid 2px #d0d0d0;
	width: 320px;
	height: 220px;
	padding: 10px;
	z-index: 1;
	border-radius:15px;
}
.popupLayer div { position: absolute; top: 5px; right: 5px; }

.imgSelect { cursor: pointer; }

#field_list{
	background: #eeeeee;
	width: 230px;
	height: 570px;
	overflow: auto;
	padding-top: 5px;
	float: left;
	display: block;
	white-space: nowrap;
	margin-left: 15px;
}

</style>
<script>
//field_code
var field_code_js = null;

$(document).ready(function(){
	ajax_load();

	//외부 클릭시 툴팁 닫힘
	$(document).mouseup(function (e){
		var container = $(".popupLayer");
		if( container.has(e.target).length === 0){
			$('.popupLayer').hide();
			$('.img_picker').attr('src','images/pin.png');
			$('.field_n').css('font-weight', 'normal');
			//$('input[name=field_code]').val('');
			field_code_js = null;
		}
	});
});	//ready End

function ajax_load() {
	var field_check = $('#emp_field_check').is(":checked");
	var search_select = $('#search_select').val();
	var word = $('#word').val();
	 $.ajax({
		url: "ajaxFieldView",
		type: "POST",
		data : {
			field_check : field_check,
			search_select : search_select,
			word : word
		},
		success : function(data) {
//			console.log(data);
			$('.popupLayer').hide();
			resultHtml(data);
		},
		error: function(){
			alert("Error");
		}
	});
}

function resultHtml(data) {
	$.each(data, function(key, value){
//		console.log(data);
		var field_code = '"'+value.field_code+'"';
		var btn_img = "<button type='button' value="+ value.field_name +" onclick='change("+ field_code +",event)' style='outline:0;'>";
			btn_img+= "<img class='img_picker i_"+value.field_code+"' src='images/pin.png' style='width:15px;'></button>"
		$('#field_list').append("<p class='field_n f" + value.field_code + "'><label>"+ btn_img +value.field_name+'</label></p>');
		$("#fieldMap"+value.addr_code).append("<div class='cool'>"+btn_img+"</div>");
	});
}

function change(field_code,event) {
	//초기화
	$('.img_picker').attr('src','images/pin.png');
	$('.field_n').css('font-weight', 'normal');
	var divLeft = event.pageX + 15;
	var divTop = event.pageY + 10;
	clkTooltip(divLeft,divTop);
	field_code_js = field_code;
	
	$.ajax({
		url: "ajaxFieldInfo",
		type: "GET",
		data : {field_code : field_code},
		success : function(data) {
			$('#field_simple_view').empty();
			$('#field_simple_view').append(
					'<spen style="font-weight:bold; font-size:larger; margin-block-end: 5px;">'+data['field_name']+'</spen>'+
					'<p>難易度： '+data['field_difficulty']+'<br>'+
					'開始時間： '+data['work_start_time']+'<br>'+
					'終了時間： '+data['work_end_time']+'<br>'+
					'現場住所： '+data['field_addr']+'<br>'+
					'リーダー： '+data['leader_name']+'<br>'+
					'派遣社員： '+data['emp_name']+'</p>');
		},
		error: function(){
			alert("Error");
		}
	});
	$('.img_picker.'+'i_'+field_code).attr('src','images/pin_mauseover.png');
	$('.field_n.f'+field_code).css('font-weight', 'bolder');
}

function clkTooltip(divLeft,divTop) {
	var sWidth = window.innerWidth;
	var sHeight = window.innerHeight;

	var oWidth = $('.popupLayer').width();
	var oHeight = $('.popupLayer').height();

	if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
	if( divTop + oHeight > sHeight ) divTop -= oHeight;

	if( divLeft < 0 ) divLeft = 0;
	if( divTop < 0 ) divTop = 0;

	$('.popupLayer').css({
		"top": divTop,
		"left": divLeft,
		"position": "absolute"
	}).fadeIn("fast");
}

function clean() {
	$('#fieldMap1').empty();
	$('#fieldMap2').empty();
	$('#fieldMap3').empty();
	$('#fieldMap4').empty();
	$('#fieldMap5').empty();
	$('#fieldMap6').empty();
	$('#fieldMap7').empty();
	$('#fieldMap8').empty();
	$('#fieldMap9').empty();
	$('#fieldMap10').empty();
	$('#fieldMap11').empty();
	$('#fieldMap12').empty();
	$('#fieldMap13').empty();
	$('#fieldMap14').empty();
	$('#fieldMap15').empty();
	$('#fieldMap16').empty();
	$('#fieldMap17').empty();
	$('#fieldMap18').empty();
	$('#fieldMap19').empty();
	$('#fieldMap20').empty();
	$('#fieldMap21').empty();
	$('#fieldMap22').empty();
	$('#fieldMap23').empty();
	$('#fieldMap99').empty();
	$('#field_list').empty();
//	$('#field_simple_view').hide();
//	$('#btn_modify').hide();
	$('#field_simple_view').empty();
}

function check_field_view() {
	$('#search_select').val('');
	$('#word').val('');
	clean();
	ajax_load();
}
function word_search_ent() {
	if($('#search_select').val() !=''){
		if(event.keyCode == 13){
			clean();
			ajax_load();
		}
	}
}
function word_search() {
	if($('#search_select').val() ==''){
		alert("検索条件を選択してください。");
		return false;
	}
	clean();
	ajax_load();
}

function detailForm_submit() {
	if(field_code_js == null){
		alert("現場を選択してください。");
		return false;
	}
	$('input[name="field_code"]').val(field_code_js);
	field_code_form.action="fieldInfoDetailForm";
	field_code_form.submit();
	//location.href='fieldInfoDetailForm?field_code='+field_code_js;
}
function modifyForm_submit() {
	$('input[name="field_code"]').val(field_code_js);
	field_code_form.action="fieldInfoModifyView";
	field_code_form.submit();
//	location.href='fieldInfoModifyView?field_code='+field_code_js;
}

function closeLayer( obj ) {
	$(obj).parent().parent().hide();
	$('.popupLayer').hide();
	$('.img_picker').attr('src','images/pin.png');
	$('.field_n').css('font-weight', 'normal');
	field_code_js = null;
}
</script>
<body>
<%-- <jsp:include page="../top.jsp" /> --%>

<div class="content_margin">
	<form name="field_code_form" method="post">
		<input type="hidden" name="field_code">
	</form>
	
	<div class="ui grid" style="margin-block-start: auto;">
		<div class="ten wide column" style="padding:unset;">
		<div style="float: right;">
			<div class="ui checkbox" style="margin-right: 60px;">
				<input id="emp_field_check" type="checkbox" checked="checked" onchange="check_field_view()">
				<label for="emp_field_check" style="cursor: pointer;">派遣人員ない現場表示する。</label>
			</div>
			
			<select name="search_select" id="search_select" class="ui dropdown">
				<option value="">検索条件</option>
				<option value="field_name">現場名</option>
				<option value="emp_id">リーダー名</option>
			</select>
			<div class="ui right action input">
				<input type="text" id="word" placeholder="Search..." onkeyPress="word_search_ent()">
				<button type="button" class="ui icon button" onclick="word_search()"> 検索</button>
			</div>
		</div>	
		</div>
		
<!-- 		<div class="six wide column" style="top: 1.5em;"></div> 
-->
<!-- 		<div class="eight wide column"> -->
			
			
			
			
		<!-- </div> -->
<!-- 		<div class="four wide column"></div> -->
	</div>
	
	<div class="ui grid">
	<!-- map -->	  
		<div class="ten wide column">
			<div class="jb-wrap img" id="field_map" style="float: right;">
				<img class="ui fluid image" src="images/tokyoMap_areaDivision.png">
				<!-- forEach -->
				<div class="jb text1">
				<spen><div class='marker_sort' id="fieldMap1"></div></spen>
				</div>
				<div class="jb text2">
				<spen><div class='marker_sort' id="fieldMap2"></div></spen>
				</div>
				<div class="jb text3">
				<spen><div class='marker_sort' id="fieldMap3"></div></spen>
				</div>
				<div class="jb text4">
				<spen><div class='marker_sort' id="fieldMap4"></div></spen>
				</div>
				<div class="jb text5">
				<spen><div class='marker_sort' id="fieldMap5"></div></spen>
				</div>
				<div class="jb text6">
				<spen><div class='marker_sort' id="fieldMap6"></div></spen>
				</div>
				<div class="jb text7">
				<spen><div class='marker_sort' id="fieldMap7"></div></spen>
				</div>
				<div class="jb text8">
				<spen><div class='marker_sort' id="fieldMap8"></div></spen>
				</div>
				<div class="jb text9">
				<spen><div class='marker_sort' id="fieldMap9"></div></spen>
				</div>
				<div class="jb text10">
				<spen><div class='marker_sort' id="fieldMap10"></div></spen>
				</div>
				<div class="jb text11">
				<spen><div class='marker_sort' id="fieldMap11"></div></spen>
				</div>
				<div class="jb text12">
				<spen><div class='marker_sort' id="fieldMap12"></div></spen>
				</div>
				<div class="jb text13">
				<spen><div class='marker_sort' id="fieldMap13"></div></spen>
				</div>
				<div class="jb text14">
				<spen><div class='marker_sort' id="fieldMap14"></div></spen>
				</div>
				<div class="jb text15">
				<spen><div class='marker_sort' id="fieldMap15"></div></spen>
				</div>
				<div class="jb text16">
				<spen><div class='marker_sort' id="fieldMap16"></div></spen>
				</div>
				<div class="jb text17">
				<spen><div class='marker_sort' id="fieldMap17"></div></spen>
				</div>
				<div class="jb text18">
				<spen><div class='marker_sort' id="fieldMap18"></div></spen>
				</div>
				<div class="jb text19">
				<spen><div class='marker_sort' id="fieldMap19"></div></spen>
				</div>
				<div class="jb text20">
				<spen><div class='marker_sort' id="fieldMap20"></div></spen>
				</div>
				<div class="jb text21">
				<spen><div class='marker_sort' id="fieldMap21"></div></spen>
				</div>
				<div class="jb text22">
				<spen><div class='marker_sort' id="fieldMap22"></div></spen>
				</div>
				<div class="jb text23">
				<spen><div class='marker_sort' id="fieldMap23"></div></spen>
				</div>
				
				<div class="jb text99">
				<spen><div class='marker_sort' id="fieldMap99"></div></spen>
				</div>
			</div>
		</div>
	 
		<div class="six wide column" style="top: 1.5em;">
			<div class="four column centered row" id="field_list" style="width: 240px;"></div>

			<c:if test="${sessionScope.adminFlg eq 01 }">
				<div class="four column centered row" style="position: absolute; top: 600px; left: 50px;">
					<button class="ui primary button" onClick="location.href='fieldInfoRegistForm'">新規登録</button>&nbsp;&nbsp;
					<button class="ui primary button" onclick="location.href='fieldEmpForm?messageFlg=0'">社員配置</button>
				</div>
			</c:if>
		</div>
	</div>
</div>

<div class="popupLayer">
	<div id="field_simple_view" style="left: 15px; height: 160px; overflow: auto;"></div>
	<button class="ui primary button" type="button" id="btn_fieldDetail" style="position: absolute; top: 175px; left: 210px;" onclick="detailForm_submit()">詳細情報</button>
	<c:if test="${sessionScope.adminFlg eq 01 }">
		<button class="ui primary button" type="button" id="btn_modify" style="position: absolute; top: 175px; left: 10px;" onclick="modifyForm_submit()">現場情報変更</button>
	</c:if>
	<div>
		<span onClick="closeLayer(this)" style="cursor:pointer;font-size:1.5em; margin: 10px;" title="閉じる ">x</span>
	</div>
</div>

</body>
</html>

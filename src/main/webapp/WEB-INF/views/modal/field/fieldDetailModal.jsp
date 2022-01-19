<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
	.modal-body .table tr td{height:40px; padding:0; vertical-align: middle;}
	.modal-body .table tr td .btn{padding:1px 0; margin:0; font-size: 15px; width:80%; margin:0 5%;}
	.modal-body .table tr td .badge{line-height: 1.5;}
</style>
<script>
//더블클릭 방지
var dbl_chk = true;

//view
$(document).ready(function(){
	ajax_load();
	searchajax();
});

//reload
function ajax_load() {
	 $.ajax({
			url: "fieldModalEmpList",
			type: "GET",
			data : {field_code : $('#field_code').val() },
			success : function(data) {
//				console.log("start");
				var html = jQuery('<div>').html(data);
				var contents = html.find("div#leftListAjax").html();
				$("#left").html(contents);
				var contents2 = html.find("div#RightListAjax").html();
				$("#right").html(contents2);
			},
			error: function(){
				alert("Error");
			}
		});
}

//　検索
function searchajax() {
	$("#search_name").keyup(function() {
		var word = $("#search_name").val();
			$.ajax({
				url: "fieldModalEmpList",
				type: "GET",
				data : {word: word },
				success : function(data) {
//					console.log("search");
					var html = jQuery('<div>').html(data);
					var contents2 = html.find("div#RightListAjax").html();
					$("#right").html(contents2);
				}, 
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
				}
			});
	});
}//searchajax End

// (<<)　ボタン
$(function(){
	$('#r_button').on("click",function(){
		var right_check = new Array();
	    $("input[name=right_check]:checked").each(function(i) {
	    	right_check.push($(this).val());
	    });
		if($("input[name=right_check]:checked").val() == null){
			alert("社員をチェックしてください。");
			return false;
		}
		if(dbl_chk){
			dbl_chk = false;
			setTimeout(function(){dbl_chk = true; }, 1500)
			$.ajax({
				url: "modalFieldIn",
				type: "GET",
				data : {	//JSON.stringify(
					right_check: right_check
				},
				success : function(data) {
//					console.log("r_click");
					ajax_load();
				}, 
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
				}
			});	//ajax End
		}	//dbl_chk End
	}); //click event End
});

// (>>)　ボタンをクリック
$(function(){
	$('#l_button').click(function(){
		var left_check = new Array();
	    $("input[name=left_check]:checked").each(function(i) {
	    	left_check.push($(this).val());
	    });
		if($("input[name=left_check]:checked").val() == null){
			alert("社員をチェックしてください。");
			return false;
		}
		//중복 클릭 확인
		if(dbl_chk){
			dbl_chk = false;
			setTimeout(function() {dbl_chk = true; }, 1500)
			$.ajax({
				url: "modalFieldOut",
				type: "GET",
//				dataType:"json",
//				contentType: 'application/json',
				data : {left_check: left_check},
				success : function(data) {
//					console.log("l_click");
					ajax_load();
				}, 
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Outerror");
				}
			});	//ajax End
		}	//dbl_chk End
	}); //click event End
});

//　リーダー設定ボタンクリック
$(function() {
	$('#leader_set').click(function(){
	if($("input[name=left_check]:checked").length == 0){	//check valueがない場合
		alert("社員をチェックしてください。");
	}else{													//check valueがある場合
		var flug = true;
		var left_check = new Array();
		
		$("input[name=left_check]:checked").each(function(i) {	//　リーダーチェックfor文
			var leader = $(this).val();
			left_check.push($(this).val());
			if($('#'+leader).val() == 'Y'){
				flug = false
			}
		});//　チェック each End
		
		if(flug){
			if(dbl_chk){
				dbl_chk = false;
				setTimeout(function() {dbl_chk = true; }, 1500)
				
				$.ajax({
					url: "modalLeaderSet",
					type: "POST",
					data : {left_check: left_check},
					success : function(data) {
	//					console.log("leader_set");
						ajax_load();
					}, 
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("error");
					}
				});	//ajax End
			}	//bdl_chk End
		}else{
			alert("リーダーの社員がいます。");
		}
	}//checkbox確認 if文終了
		
	}); //click Event End
});	//function End

//　リーダー取消ボタンを押下すると。
$(function() {
	$('#leader_unset').click(function(){
	
	if($("input[name=left_check]:checked").length == 0){	//check valueがない場合
		alert("社員をチェックしたください。");
	}else{													//check valueがある場合
		var flug = true;
		var left_check = new Array();
		
		$("input[name=left_check]:checked").each(function(i) {	//　リーダーチェックfor文
			var leader = $(this).val();
			left_check.push($(this).val());
			
			if($('#'+leader).val() == 'N'){
				flug = false
			}
		});//　チェック each End
		if(flug){
			if(dbl_chk){
				dbl_chk = false;
				setTimeout(function() {dbl_chk = true;}, 1500)
				$.ajax({
					url: "modalLeaderUnset",
					type: "POST",
					data : {left_check: left_check},
					success : function(data) {
	//					console.log("leader_unset");
						ajax_load();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("error");
					}
				});	//ajax End				
			}	//dbl_chk End
		}else{
			alert("リーダーではない社員を選択してください。");
		}
	}//checkbox確認 if文終了
		
	}); //click Event End
});	//function End
</script>
<meta charset="UTF-8">
<title>KS情報システム</title>

</head>
<body>
<div id="contact-modal" class="modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg" data-backdrop="static">
		<div class="modal-content" style="width: 800px;" data-backdrop="static">
			<div class="modal-header" data-backdrop="static">
				<h2 class="ui header" style="font-size: x-large; margin: 0;">社員派遣</h2>
			</div>
			
			<!-- left form -->
			<div class='left-box'>
				<form id="left_list" name="left_list" role="form">
				<!-- c:forEach 利用して DBリスト出力 -->
				<div id="left" style ="text-align:left;"></div>
				</form>
			</div>
			
			<!-- (<<) (>>) button -->
			<button id="r_button" class="ui button" style="width: 100px; height: 45px; border:0; border-radius:100px/60px; outline:0; position: absolute; left: 325px; top: 180px;">＜＜</button>
			<button id="l_button" class="ui button" style="width: 100px; height: 45px; border:0; border-radius:100px/60px; outline:0; position: absolute; left: 325px; top: 300px;">＞＞</button>

			<!-- right form -->
			
			<div class="col-auto" style="position: absolute; top: 82px; left: 445px; width: 45%">
				<input type="text" class="form-control" id="search_name" placeholder="社員名を入力してください。">
			</div>
			
			<div class='right-box'>
				<form id="right_list" name="right_list" style="margin: -35px 0;"><br><br>
					<div id="right" style ="text-align:left;"></div>
				</form>
			</div>
			
			<!-- footer -->
			<div class="d-flex justify-content-between">
				<div>
					<button type="button" id="leader_set" class="ui button" style="font-size:1.0rem; width: 126px; height: 40px; margin: 10px; background-color: rgb(52, 152, 219); color: white;">リーダー設定</button>
					<button type="button" id="leader_unset" class="ui button" style="font-size:1.0rem; width: 126px; height: 40px; margin: 10px; background-color: tomato; color: white; background-color:tomato; color:white;">リーダー取消</button>
				</div>
				<div style="position: absolute; left: 680px;">
					<button type="button" class="ui button" data-dismiss="modal" style="font-size:1.0rem; width: 90px; height: 40px; margin: 10px;" onclick="reload()">閉じる</button>
				</div>
			 </div>
		</div>
	</div>
</div>	
</body>
</html>
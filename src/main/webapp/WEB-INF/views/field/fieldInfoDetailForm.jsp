<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/modal/field/fieldDetailModal.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>

<title>現場情報詳細</title>
<style>
td,th{padding: 12px;}
th{font-size: 18px; text-align: right; font-weight: bold; background:#e9ecef;}
span{font-size: 15px; text-overflow: ellipsis;}
text{font-size: 15px;}
.field_leader{
	background-image:url(${pageContext.request.contextPath}/images/leader_mem.png);
	background-repeat:no-repeat;
	background-position:6px 8px;
	background-size:50px;
}
.field_member{
	background-image:url(${pageContext.request.contextPath}/images/member.png);
	background-repeat:no-repeat;
	background-position: 6px 8px;
	background-size:60px;
}
.left-box {
  background-color:rgba(134,238,255,0.2);
  float: left;
  width: 35%;
  padding: 10px;
  margin: 10px;
  overflow: auto;
  height:400px;
}
.right-box {
  background-color:rgba(134,238,255,0.2);
  width: 40%;
  padding: 10px;
  margin: 10px;
  overflow: auto;
  height:360px;
  position: absolute;
  left: 455px;
  top: 112px;
 }

.display-none{ /*감추기*/
	display:none;
}
.canWrite{background:#afffb9; border:3px solid #afffb9;}

h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}
</style>
</head>
<!-- main -->
<script>
$(function(){
	$('#btn_copy').click(function(){
		$('#field_addr').select(); 
		document.execCommand("copy");
		alert('コピーしました。');
	});
});

function locationURL() {
	location.href="fieldList";
}
function reload() {
	location.reload();
}
function modalShow() {
    $('#contact-modal').modal('show');
    $('#contact-modal').modal({backdrop: 'static'});
}
</script>
<body style="margin:0px; padding:0px">
	
<div class="content_margin" style="height: 500px;">
<div style="position: absolute; left: 50%; transform:translate(-50%); width: 860px;">
<h1 ><i class="caret square left icon"></i>現場情報詳細</h1>
	<table class="table table-bordered">
		<tr>
			<th class="table-info">現場名</th>
			<td>${list.get(0).field_name}</td>
			<th class="table-info">勤務開始時間</th>
			<td>${list.get(0).work_start_time.substring(0,5)}</td>
		</tr>
		<tr>
			<th class="table-info">現場住所</th>
			<td>
				<input type="text" id="field_addr" value="${list.get(0).field_addr}" size="10" style="border:none;width:200px" readonly="readonly">
				<button type="button" id="btn_copy" class="ui teal right labeled icon button" style="font-size: 0.7em"><i class="copy icon"></i>Copy</button>
			</td>
			<th class="table-info">勤務終了時間</th>
			<td>${list.get(0).work_end_time.substring(0,5)}</td>
		</tr>
		<tr>
			<th class="table-info">難易度</th>
			<td>${list.get(0).field_difficulty}</td>
			<th class="table-info">休み時間</th>
			<td style="width:260px">昼休み：${list.get(0).work_break_time1}分　夜間：${list.get(0).work_break_time2}分</td>
		</tr>
		<tr>
			<th class="table-info" style="font-size: 17px;">現場コード</th>
			<td>${list.get(0).field_code}</td>
			<th class="table-info">登録日</th>
			<td>${list.get(0).reg_date}</td>
		</tr>
		<tr>
			<th class="table-info">ツール</th>
			<td><input type="text" id="field_tool" value="${list.get(0).field_tool}" size="20" style="border:none" readonly="readonly"></td>
			<th class="table-info">作業環境</th>
			<td><input type="text" id="field_env" value="${list.get(0).field_env}" size="20" style="border:none" readonly="readonly"></td>
		</tr>
		
		<tr>
			<th class="table-info" style="text-align: right; vertical-align: top;">派遣社員</th>
			
			<td>
				<div class="field_leader" style="width: 100px; height: 100px; float: left;"></div>
				<div style="height: 100px; overflow: auto; width: 220px; float: right;">
					<c:forEach items="${list}" var="list" varStatus="status">
					<c:if test="${list.leader_check eq 'Y'}">
		   				<a class="ui blue image label" style="margin: 3px;">
           					<c:if test="${list.emp_gender eq 'M'}">
							  <img src="images/male.jpg">
           					</c:if>
           					<c:if test="${list.emp_gender eq 'F'}">
							  <img src="images/female.jpg">
           					</c:if>
						  ${list.position_name}
						  <div class="detail">${list.emp_name}</div><br>
						</a>
					</c:if>
					</c:forEach>
				</div>
				
<!-- 				<textarea name="field_emp" id="field_emp" class="field_leader" rows="5" cols="30px" style="resize: none; padding:7px;" readonly="readonly"> -->
				</textarea>
			</td>
			
			<td colspan="2">
			<div class="field_member" style="width: 100px; height: 100px; float: left;"></div>
			<div style="height: 100px; overflow: auto; width: 220px; float: right;">
		            <c:forEach items="${list}" var="list" varStatus="status">
		            <c:if test="${!empty list.emp_gender}">
           				<a class="ui blue image label" style="margin: 3px;">
           					<c:if test="${list.emp_gender eq 'M'}">
							  <img src="images/male.jpg">
           					</c:if>
           					<c:if test="${list.emp_gender eq 'F'}">
							  <img src="images/female.jpg">
           					</c:if>
						  ${list.position_name}
						  <div class="detail">${list.emp_name}</div><br>
						</a>
					</c:if>	
		            </c:forEach>
		    </div>        
<!-- 			    <textarea name="field_emp" id="field_emp" class="field_member" rows="5" cols="41px" style="resize: none; padding:7px;" readonly="readonly">
		        </textarea>    	 -->
			</td>
		</tr>
		<tr>
			<th class="table-info" style="text-align: right; vertical-align: top;">メモ</th>
			<td colspan="4"><textarea name="field_memo" id="field_memo" rows="5" cols="77" style="resize: none; border: none;" readonly="readonly">${list.get(0).field_memo}</textarea></td>
		</tr>
	</table>
	<div class="ui">
		<div class="ui button left floated" onclick="locationURL()">
			<i class="left chevron icon"></i>戻る
		</div>
		<c:if test="${sessionScope.adminFlg == '01'}">
			<div id="contact">
				<button type="button" class="ui blue button right floated" name="btn_dispatch" onclick="modalShow()">社員派遣</button>
			</div>
		</c:if>						
	</div>				
</div>

<c:if test="${sessionScope.adminFlg == '01'}">
<!-- modal windows -->
</c:if>
</div>

</body>
</html>
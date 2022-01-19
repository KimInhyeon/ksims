<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C/elandview 리스트 값 가져오기/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Pragma" content="no-cache" />
	<script>
		$(document).ready(function() {
			fileCheck();
			titleChange();
			sheetChk();
			FloatTotimeString();
		});
		
		//1자리 숫자일경우 앞자리에 0 입력
		function fillZero(width, str){
		    return str.length >= width ? str:new Array(width-str.length+1).join('0')+str;//남는 길이만큼 0으로 채움
		}

		//총 등록시간 비교후 확인
		function sheetChk() {
			var notSheet = new Array(); 
	
			<c:forEach items="${sheetList}" var="sheetList">
				notSheet.push({sheetList: "${sheetList}"});
			</c:forEach>
			
			$.each(notSheet, function(key,value) {
				$('#'+value.sheetList).addClass("table-danger ");
//				$('#'+value.sheetList).css("background-color","lightskyblue");
			});
		}
		
		//소수점>> 시간 변경
		function FloatTotimeString() {
			var date = new Date();
			console.log(date);
			
			var work = new Array(); 
			<c:forEach items="${conductWorkList}" var="conductWorkList">
				work.push({worktime: "${conductWorkList.worktime}", sheet_number: "${conductWorkList.sheet_number}"});
			</c:forEach>
			$.each(work, function(key,value) {
				var time = value.worktime;
				var hoursMinutes = time.split(/[.]/);
				var hours = parseInt(hoursMinutes[0], 10);
				var str = hoursMinutes[1] ? parseFloat("0."+hoursMinutes[1]) : 0;
				if(str == 0){
					str = "00";
				}else{
					str = fillZero(2 , parseInt(str * 60).toString());
				}
				var minutes = str.substring(0,3);
				$('#worktimes'+value.sheet_number).val(hours + ":" + minutes);
			});
		}
		
		// 시간>> 소수점 변경
		function timePatternChk(obj,str) {
			var pattern = /^([0-9]){0,3}:([0-5][0-9])$/;
			var pattern2 = /^([0-9]){0,3}([0-5][0-9])$/;
			
			var time = obj.value;
			var min = "";
			
			if(pattern.test(time)){
				var result = timeStringToFloat(time);
				$('#worktime'+str).val(result);
			}else if(pattern2.test(time)){
				if(time.substring(0,time.length-2) ==""){
					min = "0";
				}else{
					min = time.substring(0,time.length-2);
				}

				time = min +":" + time.slice(-2);
				var result = timeStringToFloat(time);
				$('#worktime'+str).val(result);
				obj.value = time;

			}else{
				alert("正しい時間を入力してください　例）HH:mm");
				obj.value = "0:00";
				return false;
			}
		}
		
		//시간 변환
		function timeStringToFloat(time) {
			  var hoursMinutes = time.split(/[.:]/);
			  var hours = parseInt(hoursMinutes[0], 10);
			  var minutes = hoursMinutes[1] ? parseInt(hoursMinutes[1], 10) : 0;
			  minutes = Math.ceil((minutes / 60) * 100) / 100;
			  return hours + minutes;
		}
		
		
		function scheduleMove(str) {
			var sel_fieldCode = $('#fieldCode'+str).val();
			var emp_id = $('#empId'+str).val();
//			$('input[name=inqueryMonth]').val();
			$('#sid').val(emp_id);
			$('#field_code').val(sel_fieldCode);
			workRegi.action="conductSchedule";
			workRegi.method="get";
			workRegi.submit();
		}
			
		function backPage(){ // 뒤로가기
			if($("input[name=adminFlg]").val()=="01"){
				location.href='${pageContext.request.contextPath}/main';	
			}else{
				location.href='${pageContext.request.contextPath}/conductMain';
			}
		}
		
		function fileCheck(){//다운로드 버튼 안눌리게
			$.each($('input[name=fileName]'), function(index, el){
				if($(this).val()==""){
					document.getElementById('btn_dn'+index).setAttribute('disabled', 'disabled');
				}
			});
		}
	
		function Download(e){ //다운로드
			var fileName= $('#fileName'+e).val();
			var path =$('#fileUrl'+e).val();
			fileName = encodeURIComponent(fileName);
			path = encodeURIComponent(path);
			
			if (path == "") {
				alert("登録されたファイルがありません。");
				return false;
			}
			
			location.href = "${pageContext.request.contextPath}/download?path=" + path + "&fileName=" + fileName;
		}
			
		function checkDis() {
			var result = true;
			$.each($('input[name=plan_work_days]'), function(index, el){//총 업무일에 소수점 있는지 검사
				var planResult = $(this).val();
				if(!Number.isInteger(planResult % 1)){
				alert("総勤務日で小数点は存在できません。");
				result = false;
				}
			});
			if (result) {
				submitForm();
			}
		}
		
		function submitForm() {
			$('input[name=workId]').removeAttr('disabled'); //등록누르면 disabled 해제
			$('input[name=plan_work_days]').removeAttr('disabled');
			$('input[name=workName]').removeAttr('disabled');
			$('input[name=vacations]').removeAttr('disabled');
			$('input[name=workings]').removeAttr('disabled');
			$('input[name=worktimes]').removeAttr('disabled');
			workRegi.action='conductUpdate';
			workRegi.submit();
		}
		
		function isRegiNum(obj,index) {//입력방지
			var str = obj.value;
			plusTyping(index);
			
			if(str.match(/[^0-9]+(.[^0-9])/g)){
				alert("数字を入力してください");
				obj.value = "0";
				return false;	
			}
		
			if(str.match(/^[a-zA-Z]/g)){
				alert("数字を入力してください");
				obj.value = "0";
				return false;
			}
	
			if(str==""||str==" "||str=="  "){
				alert("内容を入力してください");
				obj.value = "0";
				obj.focus();
				return false; 
			}
			if(str > 31 || str < 0){
				alert("内容が正しくありません。");
				obj.value = "0";
				obj.focus();
				return false;
			}
		}
/*		
		function worktimeChk(obj) {
			var str = obj.value;
			if(str==""){
				alert("内容を入力してください");
				obj.value = "0"; 
				return false; 
			}
			if(str > 1000 ||str < 0){
				alert("内容が正しくありません。");
				obj.value = "0"; 
				return false; 
				
			}
			
		}
*/		
		function maxLengthCheck(object){
		    if (object.value.length > object.maxLength){
		      object.value = object.value.slice(0, object.maxLength);
		    }    
		  }
		
			
		function plusTyping(e){//업무일 합산
			var vacations = document.getElementById('vacation'+e).value;
			var workings  = document.getElementById('working'+e).value;
			var plan_work_days = document.getElementById('plan_work_days'+e).value;
			plan_work_days = Number(vacations)+Number(workings);
			document.getElementById('plan_work_days'+e).value = plan_work_days;  
				
			if(isNaN(plan_work_days)){
				document.getElementById('plan_work_days'+e).value="0";
				return false;
			}	
		}
			
		function titleChange(){//동그라미 타이틀
			$.each($('input[name=fileName]'), function(index, el){
				if($(this).val()!=""){
					var titleVal = $(this).val();
					$('#circleGreen'+index).attr("title",titleVal);
					$('#circleGreen'+index).addClass("green");
				}else{
					var titleVal = "ファイル無し";
					$('#circleRed'+index).attr("title",titleVal);
					$('#circleRed'+index).addClass("red");
				}
			});
		}
			
		function selectFile(index) {
			var selectedBtn = "btn" + index;
			document.getElementById(selectedBtn).click();
		}
		
		function getFileName(index) { //파일 업로드 버튼 문자 바뀌기
			var fileId = "#btn" + index;
			var fileName = $(fileId).prop('files')[0].name;
			
			var byteLength = 0;
			for (var inx = 0; inx < fileName.length; inx++) {
				var oneChar = escape(fileName.charAt(inx));
				if (oneChar.length == 1) {
					byteLength++;
				} else if (oneChar.indexOf("%u") != -1) {
					byteLength += 2;
				} else if (oneChar.indexOf("%") != -1) {
					byteLength += oneChar.length / 3;
				}
			}
			
			if(byteLength < 16){
				document.getElementById("btnUp" + index).innerHTML = fileName;	
			}else{
				var btnId = 'btnUp'+index;
				$('#' + btnId).attr('title', fileName); 

				fileName = fileName.substring(0,6) + "...";
				document.getElementById("btnUp" + index).innerHTML = fileName;	
			}
		}		
	</script>
	<c:if test="${sessionScope.adminFlg=='01'}">
		<script>
		function thisMonthConductGenerator(){
//			progressTest();
			$('#progress_modal').show();
			setInterval('progressTest()',100);
			
			workRegi.action="conductGenerator";
			workRegi.submit();
			$('#tMCDG_btn').css('pointer-events','none');
		}
		
		function selectMonth(){
			var inqueryMonth=workRegi.inqueryMonth.value;
			var curpage='${page.curPage}';
			location.href="${pageContext.request.contextPath}/conductMain?inqueryMonth="+inqueryMonth;
		}
		
		function addCdt(){
//			progressTest();
			$('#progress_modal').show();
			setInterval('progressTest()',100);
			
			workRegi.action="condAfterGenerator";
			workRegi.submit();
			$('#addCdtbtn').css('pointer-events','none');
		}
		
		function conductComp() {
			if(confirm("勤務情報を確定しますか？") == true){
//				location.href="conductComp?inqueryMonth="+day;
				workRegi.action="conductComp";
				workRegi.submit();
			}
		}
		
		//progress test
		function progressTest(){
			
			var obj = new Object();
			
			$.ajax({
				url:"progressTest",
				type:"POST",
				dataType:"json",
				contentType:"application/json",
				data:JSON.stringify(obj),
				success: function(data){
					$('#conductProgress').css('width',data.percent+'%');
					$('#percnt').html(parseInt(data.percent)+"%");
				},
				});
		}
		</script>
	</c:if>
<style>
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
	<div class="content_margin">
		<form class="ui form" id="workRegi" name="workRegi" action="conductUpdate" method="POST" enctype="multipart/form-data" onsubmit="return false">
			<h1><i class="caret square left icon"></i>勤務情報登録</h1>
			<div class="ui grid">
				<div class="column">
					<c:if test="${sessionScope.adminFlg=='01'}">
						<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM" var="thisMonth"/>
						<div class="ui calendar">
							<div class="ui input" style="float: left;">
								<input type="month" name="inqueryMonth" value="${inqueryMonth}" onchange="selectMonth()">
							</div>
							
							<div>
								<p style="margin-left: 200px; font-size:16px; ">
								勤務表が（勤務表の未アップロード、未作成、本社勤務表と勤務情報作成の総勤務時間が等しくない場合、<br>
								先月の勤務確定をしなかった場合）<span class="badge bg-danger" style="font-weight: normal; padding:3px;">　</span>
								の色で表示されます。
								</p>
							</div>
						</div><br/>
					</c:if>
					<c:if test="${sessionScope.adminFlg!='01'}">
						<input type="hidden" name="inqueryMonth" value="${inqueryMonth}">
					</c:if>
					<input type="hidden" name="work_id_result" value="" />
					<input type="hidden" name="today_result" value="" />
					<input type="hidden" name="adminFlg" value="${sessionScope.adminFlg}" />
					<input type="hidden" name="sid" id="sid" value="${sessionScope.sid}" />
					<input type="hidden" name="field_code" id="field_code" value=""/>
					
					<table class="table table-hover table-bordered table-sm">
						<thead class="table-info" align="center">
							<tr align="center">
								<th>社員番号</th>
			   				    <c:if test="${sessionScope.adminFlg == '01'}">
							    <th>現場名</th>
							    </c:if>								
								<th>勤務月</th>
								<th>総勤務日</th>
								<th>出勤日</th>
								<th>休み</th>
								<th>勤務時間</th>
								<th>本社勤務表作成</th>
								<th colspan="3">勤務表入力/出力</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty conductWorkList}">
								<c:forEach items="${conductWorkList}" var="work" varStatus="status">
									<tr id="${work.sheet_number}" class="hoverTd">	<!-- setting${status.index}-->
										<td style="width:150px">${work.emp_name}(${work.emp_id})
										</td>
									    <c:if test="${sessionScope.adminFlg == '01'}">
										<td style="width:20%">${work.field_name}
										</td >
									    </c:if>									
										<td  align="center" style="width:90px">${inqueryMonth}
										</td>
										<td  style="width:100px;text-align: right"> 
											<input type="input" id="plan_work_days${status.index}" value="${work.plan_work_days}" name="plan_work_days" class="form-control" style="text-align: right!important; width: 70px; float: left;" disabled />
										</td>
										<td align="center">
											<div class="ui right labeled input">
												<input type="number" min='0' max='31' step='0.5' id="working${status.index}" name="workings" value="${work.work_days}" class="form-control" style="text-align: right !important; width: 86px; float: left;" onkeyup="plusTyping(${status.index})" maxlength="4" onBlur='isRegiNum(this,${status.index})' oninput="maxLengthCheck(this)"
												<c:if test="${work.comp_flg eq 't' }">disabled</c:if> />
												<div class="ui basic label" style="font-size:0.8em">日</div>
											</div>
										</td>
										<td align="center">
											<div class="ui right labeled input">
												<input type="number" min='0' max='31' step='0.5' id="vacation${status.index}" name="vacations" value="${work.paid_vacation_days}" class="form-control" style="text-align: right !important; width: 72px; float: left;" onkeyup="plusTyping(${status.index})" onBlur='isRegiNum(this,${status.index})' maxlength="4" oninput="maxLengthCheck(this)"
												<c:if test="${work.comp_flg eq 't' }">disabled</c:if> />
												<div class="ui basic label" style="font-size:0.8em">日</div>
											</div>
										</td>
										<td align="center">
											<div class="ui input">
												<input type="text" id="worktimes${work.sheet_number}" name="worktimes" value="" class="form-control" style="text-align: right !important; width: 80px; float: left;" maxlength="6" onchange="timePatternChk(this,${work.sheet_number})" 
												<c:if test="${work.comp_flg eq 't' }">disabled</c:if> />
											<input type="hidden" name="worktime" id="worktime${work.sheet_number}" value="${work.worktime}">
											</div>
	　　									</td>
										<td style="width: 120px;" align="center">
											<div id="btnMove${status.index}" class="tiny ui brown button" name="btnMove${status.index}" onclick="scheduleMove(${status.index})" title="勤務表作成">
												<i class="ui calendar alternate icon"></i> 
											</div>
										</td>
										<c:if test="${!cdtFlg}">
											<td style="width: 150px;" align="center">
											<c:if test="${work.comp_flg ne 't' }">
												<div id="btnUp${status.index}" class="tiny ui blue button" name="btnName" onclick="selectFile(${status.index})" title="アップロード">
													<i class="ui cloud upload icon"></i>
												</div>
											</c:if>	
											</td>
										</c:if>
										<td style="width:150px" align="center">
											<div id="btn_dn${status.index}" class="tiny ui green button" name="btnDn" onclick="Download(${status.index})" title="ダウンロード">
												<i class="ui cloud download icon"></i>
											</div>
										</td>
										<c:if test="${!cdtFlg}">
											<td align="center" style="width: 70px;">
											<c:if test="${work.comp_flg ne 't' }">
												<c:if test='${work.file_name eq null || work.file_name eq ""}'>
													<i id="circleRed${status.index}" class="circle icon" title=""></i>
												</c:if>
												<c:if test='${work.file_name ne null && work.file_name ne ""}'>
													<i id="circleGreen${status.index}" class="circle icon" title=""></i>
												</c:if>
											</c:if>
											</td>
										</c:if>
								</tr>
		
								<input type="file" class="btn btn-primary" id="btn${status.index}" name="btn${status.index}" onchange="getFileName(${status.index})" hidden/>
								<input type="hidden" id="fileName${status.index}" name="fileName" value="${work.file_name}" />
								<input type="hidden" id="fileUrl${status.index}" name="fileUrl" value="${work.file_url}" />
								<input type="hidden" name="todays" value="<fmt:formatDate value ="${today}"  pattern="yyyy-MM"/>" />
								<input type="hidden" id="fieldCode${status.index}" name="workCode" value="${work.field_code}" />
								<input type="hidden" id="empName${status.index}" name="workName" value="${work.emp_name}" />
								<input type="hidden" id="empId${status.index}" class="conductBox" name="workId" value="${work.emp_id}" disabled />
							</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="10" align="center">
											<c:if test="${sessionScope.adminFlg=='01' && thisMonth == inqueryMonth}">
									<button class="btn btn-success" id="tMCDG_btn" onclick="thisMonthConductGenerator()">今月のレコード作成</button>
											</c:if>
										</td>
									</tr>
							</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<c:if test="${sessionScope.adminFlg=='01' && not empty conductWorkList}">	
			<div align="center">
				<div class="ui pagination menu">	
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="conductMain?curPage=1&inqueryMonth=${inqueryMonth}"><i class="angle double left icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="conductMain?curPage=${page.prevPage}&inqueryMonth=${inqueryMonth}"><i class="angle left icon"></i></a>
					</c:if>
					<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
						<c:choose>
							<c:when test="${pageNum == page.curPage}">
								<div class="active item">${pageNum}</div>
							</c:when>
							<c:otherwise>
								<a class="item" href="conductMain?curPage=${pageNum}&inqueryMonth=${inqueryMonth}">
									${pageNum}
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="conductMain?curPage=${page.nextPage}&inqueryMonth=${inqueryMonth}"><i class="angle right icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="conductMain?curPage=${page.totPage}&inqueryMonth=${inqueryMonth}"><i class="angle double right icon"></i></a>
					</c:if>
				</div>
			</div>
		</c:if>
					<div class="ui">
						<div class="ui button left floated" onclick="backPage()">
							<i class="left chevron icon"></i>戻る
						</div>
					
					
					<c:if test="${sessionScope.adminFlg =='01'}">
						<c:choose>
							<c:when test="${cdtFlg}">
								<div class="ui blue button right floated" onClick="conductComp()">
									勤務確定
								</div>
							</c:when>
							<c:otherwise>
								<button class="ui disabled button right floated">
									勤務確定
								</button>
							</c:otherwise>
						</c:choose>
					</c:if>
		
						<c:if test="${!compFlg}">
							<div class="ui blue button right floated" onClick="return checkDis()">
								登録
							</div>
						</c:if>
						
						<c:if test="${(thisMonth == inqueryMonth) && not empty conductWorkList && sessionScope.adminFlg=='01'}">
							<div class="ui button primary right floated" onclick="addCdt()" id="addCdtbtn">
								<i class="sync alternate icon"></i>入力対象者更新
							</div>
						</c:if>
						
					</div>
				</div>
			</div>
				
		</form>
	</div>
	
	<!-- modal -->
	<div class="modal" id="progress_modal" data-backdrop="static"
	data-keyboard="false" tabindex="-1" role="dialog"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content">
			<div class="modal-header" style="vertical-align: middle;">
				<h5 class="modal-title" id="exampleModalLongTitle">勤務表を生成中です。</h5>
			</div>
			<div class="modal-body">
				<div class="progress">
					<div class="progress-bar progress-bar-striped progress-bar-animated" id="conductProgress" role="progressbar" style="width: 0%"
						aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
				</div>
				<div id="percnt" style="font-size: 16px; text-align: center; font-weight: bold;"></div>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
	
	
</body>
</html>
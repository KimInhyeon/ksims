<%@page import="com.ksinfo.conduct.dto.ConductScheduleDto"%>
<%@page import="com.ksinfo.conduct.dto.ConductScheduleUpdateDto"%>
<%@page import="org.apache.poi.ss.usermodel.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 

<%

String month = request.getParameter("inqueryMonth");
int y = Integer.parseInt(month.substring(0,4));
int m = Integer.parseInt(month.substring(5,7));

Calendar cal = Calendar.getInstance();
cal.set(y, m-1, 1);

int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
pageContext.setAttribute("month", month) ;
pageContext.setAttribute("lday", lastDay) ;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>現場勤務表</title>
<style>
h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}
</style>
<script language="javascript" type="text/javascript">

$(document).ready(function(){
	$("input.time")
		.on("change", function(){
			var timet = $(this).val();
			var e = ($(this).attr("id")).slice(6);
			var namet = $(this).attr("name");
			var trTag = $("#work-tbody").find("tr");
			console.log(trTag.eq(0).find("input[name=wetime]").val());
			
			var nameOther = "";
			var breakTime = trTag.eq(e).find("input[name=wbtime]").val();
			var dateWstime = "";
			var dateWetime = "";
			var dateBrktime = "";
			var defaultwork = "${defaultwork}"
			var sdworktime = document.getElementById("sdefendtime").value;
			var edworktime = document.getElementById("edefendtime").value;
			
			var tt = new Date();
			var year = tt.getFullYear();
			var month = ('0'+(tt.getMonth()+1)).slice(-2);
			var day = ('0'+tt.getDate()).slice(-2);
			var dateString = year + '-' + month + '-' + day;
			var k = new Date(dateString+" "+"00:00");
			
			if(namet=="wstime"){
				nameOther = trTag.eq(e).find("input[name=wetime]").val();
				dateWstime = new Date(dateString+" "+timet);
				dateWetime = new Date(dateString+" "+nameOther);
			}else if(namet=="wetime"){
				nameOther = trTag.eq(e).find("input[name=wstime]").val();
				dateWstime = new Date(dateString+" "+nameOther);
				dateWetime = new Date(dateString+" "+timet);
			}else {
				timet = trTag.eq(e).find("input[name=wstime]").val();
				nameOther = trTag.eq(e).find("input[name=wetime]").val();
				dateWstime = new Date(dateString+" "+timet);
				dateWetime = new Date(dateString+" "+nameOther);
			}
			dateBrktime = new Date(dateString+" "+breakTime);
			sdworktime = new Date(dateString+" "+sdworktime);
			edworktime = new Date(dateString+" "+edworktime);

           var workTime = (dateWetime-dateWstime);
           
           if (nameOther == "" || timet == "" ) {
				//SKIP;
				return false;
		   }else {
		       if(workTime <= 0){
					alert('勤務時間を正しく入力してください。');
					$(this).val("");
					$(this).focus();
					return false;
				}	
			}

			//Start Time設定
			var dispVal1 = ("00" + dateWstime.getHours()).slice(-2);
			var dispVal2 = ("00" + dateWstime.getMinutes()).slice(-2);
			trTag.eq(e).find("input[name=wsttime]").val(dispVal1+":"+dispVal2);
			//End time設定
			dispVal1 = ("00" + dateWetime.getHours()).slice(-2);
			dispVal2 = ("00" + dateWetime.getMinutes()).slice(-2);
			trTag.eq(e).find("input[name=wedtime]").val(dispVal1+":"+dispVal2);

			dispVal1 = trTag.eq(e).find("input[name=wsttime]").val();
			dispVal2 = trTag.eq(e).find("input[name=wedtime]").val();
			
			if (dispVal1 == "00:00" || dispVal2 == "00:00") {
				trTag.eq(e).find("input[name=wktime]").val("00:00");
				trTag.eq(e).find("input[name=ovtime]").val("00:00");
				trTag.eq(e).find("input[name=worktime]").val("00:00");
			}else {
				var overTime = workTime - (edworktime - sdworktime);
	  			if(overTime<=0){
	  				overTime = 0;
	  			}
	
				var constSecond = 1000; 
				var constMinute = constSecond * 60;
				var constHour = constMinute * 60;
				var constDay = constHour * 24;

				//休憩時間チェック	
	  		    var breakTime1 = trTag.eq(e).find("input[name=wbtime1]").val(); 
	  		    var breakTime2 = trTag.eq(e).find("input[name=wbtime2]").val(); 
			    var totWorkTime = workTime;
			    var bWorkTime   = workTime;

  			    getTime1 = Math.floor(workTime % constDay / constHour);
  			    getTime2 = Math.floor(workTime %  constHour / constMinute);
			    var cuBreakTime = ("00" + getTime1).slice(-2) + ("00" + getTime2).slice(-2);
			    if (cuBreakTime < "0300"){   //出勤後3:00経過
				  //SKIP	   
			     }else if (cuBreakTime < "0845"){   //出勤後8:45経過    
				 	bWorkTime = totWorkTime -  (3*60*60*1000);  //3:00経過時間を引く
				 	
	             	if (bWorkTime < (breakTime1*60*1000)) {
			           totWorkTime = workTime - bWorkTime; 
	               }else {
		           	   totWorkTime = workTime - (breakTime1*60*1000);
	               }
			    }else{
				   bWorkTime = totWorkTime -  (8*60*60*1000) - (45*60*1000);  //8:45経過時間を引く
	               if (bWorkTime < (breakTime2*60*1000)) {
			           totWorkTime = workTime -  (breakTime1*60*1000) - bWorkTime; 
	               }else {
		           	   totWorkTime = workTime - (breakTime1*60*1000) - (breakTime2*60*1000);
	               }
			    }
	           
				// 시간 계산
				var days = Math.floor(workTime / constDay);
				var hour = Math.floor(workTime % constDay / constHour);
				var days2 = Math.floor(overTime / constDay);
				var hour2 = Math.floor(overTime % constDay / constHour);
				var days3 = Math.floor(totWorkTime / constDay);
				var hour3 = Math.floor(totWorkTime % constDay / constHour);
	
				if(hour<10){
					var hours = "0"+hour;
				}else{
					var hours = hour;
				}
				var minute = Math.floor(workTime % constHour / constMinute);
				if(minute<10){
					var minutes = "0"+minute;
				}else{
					var minutes = minute;
				}
				
				if(hour2<10){
					var hours2 = "0"+hour2;
				}else{
					var hours2 = hour2;
				}
				
				var minute2 = Math.floor(overTime % constHour / constMinute);
				if(minute2<10){
					var minutes2 = "0"+minute2;
				}else{
					var minutes2 = minute2;
				}
	
				if(hour3<10){
					var hours3 = "0"+hour3;
				}else{
					var hours3 = hour3;
				}
				var minute3 = Math.floor(totWorkTime % constHour / constMinute);
				if(minute3<10){
					var minutes3 = "0"+minute3;
				}else{
					var minutes3 = minute3;
				}
				
				if(days > 0){
				} else {
					if(hour > 0){
						console.log(hours+":"+minutes);
						trTag.eq(e).find("input[name=wktime]").val(hours+":"+minutes);
					} else {
						if(minute > 0){
							console.log(hours+":"+minutes);
							trTag.eq(e).find("input[name=wktime]").val(hours+":"+minutes);
						}
					}
				}
				
    			trTag.eq(e).find("input[name=ovtime]").val(hours2+":"+minutes2);
				
				trTag.eq(e).find("input[name=worktime]").val(hours3+":"+minutes3);
			}
			
			//合計出力
            sumcalc();
		});
});


function backPage(){
    location.href='${pageContext.request.contextPath}/conductMain';
}

function sumcalc(){
	var trTag = $("#work-tbody").find("tr");
	var len = trTag.length;
	var addHour1 = 0; 
	var addMin1 = 0; 
	var addHour2 = 0; 
	var addMin2 = 0; 
	var addHour3 = 0; 
	var addMin3 = 0; 
	
	var Days = 0;
	var vcDays = 0;
	var wkDays = 0;

	for ( var i=0; i< len - 2; i++){
		var wk= document.getElementById("wktime"+i).value;
		var ov = document.getElementById("ovtime"+i).value;
		var tot = document.getElementById("worktime"+i).value;

		if (wk.indexOf(':') == -1) {
			wk = "00:00";
		}
		var wkTiimes  = wk.split(":");
		addHour1 = addHour1 + Number(wkTiimes[0]);
		addMin1   = addMin1 + Number(wkTiimes[1]);
		
		if (ov.indexOf(':') == -1) {
			ov = "00:00";
		}
		wkTiimes  = ov.split(":");
		addHour2 = addHour2 + Number(wkTiimes[0]);
		addMin2   = addMin2 + Number(wkTiimes[1]);
		
		if (tot.indexOf(':') == -1) {
			tot = "00:00";
		}
		wkTiimes  = tot.split(":");
		addHour3 = addHour3 + Number(wkTiimes[0]);
		addMin3   = addMin3 + Number(wkTiimes[1]);

		var wkGubun= document.getElementById("workgubun"+i).value;
		var wkCode= document.getElementById("workcode"+i).value;
		
		if (wkGubun == 'W') {
			Days = Days + 1;
		}
		if (wkCode == '11') {
			vcDays = vcDays + 1;
		}else if(wkCode != '00'  && wkCode != '21'){
			wkDays = wkDays + 1;
		}
	}
	//TOT_総勤務時間設定
	var anVal = Math.floor(addMin1 / 60);
    addHour1 = addHour1 + anVal;
    addMin1   = addMin1 % 60;
	var strDisp1 = ("000" + addHour1).slice(-3);
	var strDisp2 = ("000" + addMin1).slice(-2);
	document.getElementById("worktotal").value=strDisp1 + ":" +strDisp2;
	
	//TOT_残業時間設定
    anVal = Math.floor(addMin2 / 60);
    addHour2 = addHour2 + anVal;
    addMin2   = addMin2 % 60;
	strDisp1 = ("000" + addHour2).slice(-3);
	strDisp2 = ("000" + addMin2).slice(-2);
	document.getElementById("overtotal").value=strDisp1 + ":" +strDisp2;

	//TOT_稼働時間設定
    anVal = Math.floor(addMin3 / 60);
    addHour3 = addHour3 + anVal;
    addMin3   = addMin3 % 60;
	strDisp1 = ("000" + addHour3).slice(-3);
	strDisp2 = ("000" + addMin3).slice(-2);
	document.getElementById("total").value=strDisp1 + ":" +strDisp2;
	for ( i=0; i< len - 1; i++){
		document.getElementById("tot_work_time"+i).value=strDisp1 + "." +strDisp2;
	}

	document.getElementById("basedays").value=Days;
	document.getElementById("vcdays").value=vcDays;
	document.getElementById("workdays").value=wkDays;
}

// 시간>> 소수점 변경
function timePatternChk(obj) {
	var pattern = /^([0-2][0-9]):([0-5][0-9])$/;
	
	var time = obj.value;
	var min = "";
	
	if(pattern.test(time)){
	}else{
		alert("正しい時間を入力してください　例）HH:mm");
		obj.value = "00:00";
		return false;
	}
}

function go_table(){
	var trTag = $("#work-tbody").find("tr");
	var len = trTag.length;
	
	//出勤時間.退勤時間の整合性チェック
	for ( var i=0; i< len - 1; i++){
		var wkStTime= document.getElementById("wstime"+i).value;
		var wkEdTime= document.getElementById("wetime"+i).value;
		var wkStime= document.getElementById("wsttime"+i).value;
		var wkEtime= document.getElementById("wedtime"+i).value;
		var wkGubun= document.getElementById("workgubun"+i).value;
		var wkCode= document.getElementById("workcode"+i).value;
		
		var bufSTime =  wkStime.slice(0,2) + wkStime.slice(3,5); 
		var bufETime =  wkEtime.slice(0,2) + wkEtime.slice(3,5); 
		
		if (wkCode == "00" && wkGubun == "W") {
		}else{ 
    		if (wkGubun == "W"){
    			if (wkCode != "11" && wkCode != "21" && wkCode != "22" && wkCode != "23"){
			        if (wkStTime == "" ) {
    					alert("出勤時間を入力してください");
    					document.getElementById("wstime"+i).focus();
    					return false;
    				}
			        if (wkEdTime == "" ) {
        	   			alert("退勤時間を入力してください");
	    	   			document.getElementById("wetime"+i).focus();
    		   			return false;
					}
					 
			        if (wkCode == "12"){
			        	if (bufSTime < "1200" ) {
	    					alert("午前休みです。出勤時間を「12:00」以降で入力してください");
    						document.getElementById("wstime"+i).focus();
    						return false;
			        	}	
    				}else if (wkCode == "13"){
				        if (bufETime > "1200" ) {
    						alert("午後休みです。退勤時間を「12:00」以前で入力してください");
    						document.getElementById("wetime"+i).focus();
    						return false;
    					}
    				}
				}else{
			        if (wkStTime != "" ) {
    					alert("区分が休みで設定されています。出勤時間を削除してください");
    					document.getElementById("wstime"+i).focus();
    					return false;
    				}
			        if (wkEdTime != "" ) {
        	   			alert("区分が休みで設定されています。退勤時間を削除してください");
	    	   			document.getElementById("wetime"+i).focus();
    		   			return false;
					}
				}
			}else{
    			if (wkCode != "02" && wkCode != "00"){
					alert("休日です。区分を変更してください。");
       				document.getElementById("pst_select"+i).focus();
	   				return false;
    			}else if (wkCode == "02"){
			        if (wkStTime == "" ) {
    					alert("休日勤務で設定されています。出勤時間を入力してください");
    					document.getElementById("wstime"+i).focus();
    					return false;
    				}
			        if (wkEdTime == "" ) {
        	   			alert("休日勤務で設定されています。退勤時間を入力してください");
	    	   			document.getElementById("wetime"+i).focus();
    		   			return false;
					}
    			}
			}
		}
	}
	
	timecalc.action='ConductScheduleUpdate';
	timecalc.submit();
}

function changeDropdown(s, value) {
	document.getElementById("workcode"+s).value=value;

	if (value == '00' || value == '11' || value == '21' || value == '22' || value == '23') {
		document.getElementById("wsttime"+s).value="";
		document.getElementById("wedtime"+s).value="";
		document.getElementById("wstime"+s).value="";
		document.getElementById("wetime"+s).value="";
		document.getElementById("wktime"+s).value="00:00";
		document.getElementById("ovtime"+s).value="00:00";
		document.getElementById("worktime"+s).value="00:00";
	}	
	
	sumcalc();
}

function valueClear(obj) {
	if (window.event.keyCode == 46) {
		obj.value = '';	
	}
}	
</script>

</head>
<body onload="sumcalc();">>
<form name="timecalc" action="conductScheduleUpdate" method="POST" enctype="multipart/form-data">
 <DIV class="content_margin">
  <h1><i class="caret square left icon"></i>勤務表作成</h1> <h2>作成年月：${ month}</h2>
  
  <table class="table table-bordered profiletable table-sm" style="width:70%; height:10%; margin:3px auto;">
           		 <tr>
           		 <th class="table-info" style="width:8%; padding:10px; vertical-align:middle !important;">
             	   		ID
             	   	</th>
             	   	<td>
                  		<input type = "text" class="form-control-plaintext disis" name = "emp_id" value="${sch.emp_id} " style="background-color:#fff;" disabled>
                	</td>
             	   <th class="table-info" style="width:8%; padding:10px; vertical-align:middle !important;">
             	   		氏名
             	   	</th>
                	<td>
                  		<input type = "text" class="form-control-plaintext disis" name = "emp_name" value="${sch.emp_name}" disabled>
                	</td>
                	<th class="table-info" style="width:8%; padding:10px; vertical-align:middle !important;">
                		現場
                	</th>
                	<td>
                		<input type = "text" class="form-control-plaintext disis" name = "field_name" value="${sch.field_name}" disabled>
                	</td>
                	<th class="table-info" style="width:8%; vertical-align:middle !important;">
                		職位
                	</th>
                	<td>
                		<input type = "text" class="form-control-plaintext disis"  class="form-control" name = "position_name" value="${sch.position_name}" disabled/>
                        <input type="hidden" id="sdefendtime" name="sdefendtime" value="${sch.work_start_time}" > 
                        <input type="hidden" id="edefendtime" name="edefendtime" value="${sch.work_end_time}" >
                	</td>
                	
         	   </tr>
         	 </table>
	<table>
	<tr>
	<table class="table-hover table-bordered " style=" margin-right: auto !important;table-layout:fixed; ">
						<thead class="table-info" align="center">
							<tr align="center">
			                	<th >日付</th>
			                    <th>曜日</th>
			                    <th>区分</th>
			                    <th>出勤時間</th>
			                    <th>退勤時間</th>
			                    <th>休憩時間</th>
			                    <th>総勤務時間</th>
			                    <th>残業時間</th>
			                    <th>実稼働時間</th>
			                    <th>作業内容</th>
			                </tr>
			            </thead>
			            <tbody id="work-tbody">
			            <c:forEach items="${cslist}" var="list" varStatus="status">
			        	 <fmt:parseDate value="${list.conduct_year}${list.conduct_month}${list.conduct_day}" var="dateFmt" pattern="yyyyMMdd"/>
   					 	 <fmt:formatDate value="${dateFmt}" pattern="E" var="today"/>   					 	
							<c:if test="${today eq 'Mon'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 月"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#fff"/>                        
							</c:if>
							<c:if test="${today eq 'Tue'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 火"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#fff"/>                        
							</c:if>
							<c:if test="${today eq 'Wed'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 水"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#fff"/>                        
							</c:if>
							<c:if test="${today eq 'Thu'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 木"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#fff"/>                        
							</c:if>
							<c:if test="${today eq 'Fri'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 金"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#fff"/>                        
							</c:if>
							<c:if test="${today eq 'Sat'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 土"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#eee"/>                        
							</c:if>
							<c:if test="${today eq 'Sun'}">                        
	　　　　　　　　　　 		<c:set var="chgToday" value=" 日"/>                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#eee"/>                        
							</c:if>
							<c:if test="${list.h_desc != null}">                        
	　　　　　　　　　　 		<c:set var="chgColor" value="#eee"/>                        
							</c:if>

			                  <tr  style="background-color:${chgColor}">
			                        <td align="center" >
										<c:choose>
				                        <c:when test="${today eq 'Sat'}">
  				                  		<input type="text" value="${list.conduct_day}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
				                        </c:when>
				                        <c:when test="${today eq 'Sun'}">
  				                  		<input type="text" value="${list.conduct_day}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
				                        </c:when>
				                        <c:when test="${list.h_desc != null}">
  				                  		<input type="text" value="${list.conduct_day}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
				                        </c:when>
				                        <c:otherwise>
  				                  		<input type="text" value="${list.conduct_day}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#fff;"disabled>
				                        </c:otherwise>
				                        </c:choose>
			                        </td>
			                        <td align="center">
										<c:choose>
				                        <c:when test="${today eq 'Sat'}">
				                        <input type="text" value="${chgToday}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
										<input type="hidden" id="workgubun${status.index}" name="workgubun" value="H" >	
				                        </c:when>
				                        <c:when test="${today eq 'Sun'}">
				                        <input type="text" value="${chgToday}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
										<input type="hidden" id="workgubun${status.index}" name="workgubun" value="H" >	
				                        </c:when>
				                        <c:when test="${list.h_desc != null}">
				                        <input type="text" value="${chgToday}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#eee;"disabled>
										<input type="hidden" id="workgubun${status.index}" name="workgubun" value="H" >	
				                        </c:when>
				                        <c:otherwise>
				                        <input type="text" value="${chgToday}" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 60px; background-color:#fff;"disabled>
										<input type="hidden" id="workgubun${status.index}" name="workgubun" value="W">	
				                        </c:otherwise>
				                        </c:choose>
			                        </td>
			                        <td align="center">
										<select name="pst_select" id="pst_select${status.index}" onchange="changeDropdown('${status.index}', this.value)"  class="ui search dropdown" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 110px; background-color:#fff;"" >
										<c:forEach items="${workDropBox}" var="codelist" varStatus="vs">
										<option value="${codelist.workCode}"
										<c:if test="${codelist.workCode eq list.work_code}">selected</c:if>
										>${codelist.workName}</option>
										</c:forEach>
										</select>
										<input type="hidden" id="workcode${status.index}" name="workcode" value="${list.work_code}" >	
				                   </td>
			                        <td align="center">
			                        <input type="time"  step="900" value="${list.sttime}"  ID="wstime${status.index}" name="wstime"  maxlength="5"   class="form-control time"   style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 130px; background-color:#fff;" onkeyup="valueClear(this)">
			                        <input type="hidden" id="wsttime${status.index}" name="wsttime" value="${list.work_start_time}">			                      
				                   </td>
			                        <td align="center">
			                        <input type="time"  step="900" value="${list.edtime}" ID="wetime${status.index}" name="wetime"  maxlength="5"  class="form-control time" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 130px; background-color:#fff;" onkeyup="valueClear(this)">
			                        <input type="hidden" id="wedtime${status.index}" name="wedtime" value="${list.work_end_time}">
			                        </td>
			                        <td align="center">
			                        <input type="text" value="${list.work_break_time}" ID="wbtime${status.index}" name="wbtime"  readonly class="form-control time"  style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 90px; background-color:#fff;">
			                        <input type="hidden" value="${list.work_break_time1}" ID="wbtime1${status.index}" name="wbtime1"  >
			                        <input type="hidden" value="${list.work_break_time2}" ID="wbtime2${status.index}" name="wbtime2"   >
			                        </td>
			                        <td align="center">
			                        <input type="text" value="${list.worktime_hours}" ID="wktime${status.index}" name="wktime" readonly class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 150px; background-color:#fff;">
			                        </td>
				                    <td align="center">
				                    <input type="text" value="${list.overtime_hours}" ID="ovtime${status.index}" name="ovtime" readonly  class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 150px; background-color:#fff;">
			                        </td>
			                        <td align="center">
			                        <input type="text" value="${list.worktime}" ID="worktime${status.index}" name="worktime" readonly class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 150px; background-color:#fff;">
			                        </td>
			                        <td align="center">
			                        <input type="text" value="${list.conduct_note}" ID="conduct_note${status.index}" name="conduct_note" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: left !important; width: 150px; background-color:#fff;" >
 									<input type="hidden" id="tot_work_time${status.index}" name="tot_work_time" value="0.00" >
 									<input type="hidden" id="ks_conduct_id" name="ks_conduct_id" value="${list.ks_conduct_id }">
 									<input type="hidden" id="defaultwork" name="defaultwork" value="${defaultwork }" >
			                        </td>
			                    </tr>
			                    </c:forEach>
			                    <tr>
			                    <th class="table-info" colspan="6" style="text-align: center">TOTAL</th>
			                    <td style="text-align: center">
								 <input type="text" value="000:00" ID="worktotal" name="worktotal" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
								</td>
								<td style="text-align: center">
								 <input type="text" value="000:00" ID="overtotal" name="overtotal"  class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
								</td>	
                  			  	<td style="text-align: center">
								 <input type="text" value="000:00" ID="total" name="total" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
								</td>
								<td style="text-align: center">
								 <input type="text" value="" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
								</td>
								</tr>
			            </tbody>
			        </table>
	</tr>
	<tr>
					<table class="table-bordered " style="align:left; width:51%;  ">
						<tr>
						<th  class="table-info" style="width:20%; padding:10px; vertical-align:middle !important;">
						基準日数
						</th>
						<td style="text-align: center">
						<input type="text"   id="basedays" name="basedays" value="" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
						</td>
						<th  class="table-info" style="width:20%; padding:10px; vertical-align:middle !important;">
						出勤日数
						</th>
						<td style="text-align: center">
						<input type="text"  id="workdays" name="workdays" value="" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
						</td>
						<th  class="table-info" style="width:20%; padding:10px; vertical-align:middle !important;">
						休暇日数
						</th>
						<td style="text-align: center">
						<input type="text"  id="vcdays" name="vcdays" value="" class="form-control" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; text-align: center !important; width: 95%; background-color:#fff;" disabled>	
						</td>
						</tr>
					</table>
	</tr>
	<tr>
	<table style="width: 88% !important;">
	<td style="width:50%">				           
			     <div class="ui">
				<div class="ui button left floated" onclick="backPage()">
					<i class="left chevron icon"></i>戻る
				</div>
				</div>
	 		</DIV>
	</td>	
	<td >				           
    		<div class="ui">
				<div class="ui blue button right floated" onClick="go_table()">
								更新
				</div>
	</td>				
	</table>			
	</tr>
	</table>	 		
 </DIV>
 </form>
</html>
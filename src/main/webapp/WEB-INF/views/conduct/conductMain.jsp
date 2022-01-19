<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/><meta http-equiv="Pragma" content="no-cache"/>
	<title>勤務管理</title>
	<script>
		function backPage(){
		    location.href='${pageContext.request.contextPath}/main';
		}
		
		function writing(index){
		    var fieldCode = $("#btn"+index).val();
		    var month=form.inqueryMonth.value;
		    location.href= "conductRegist?fieldCode=" + fieldCode+"&inqueryMonth="+month;
		}
		function quiryMonth(){
			var month=form.inqueryMonth.value;
			location.href= "${pageContext.request.contextPath}/conductMain?inqueryMonth="+month;
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
</head>
<body>
	<div class="content_margin_small">
		<form class="ui form" name="form">
			<h1><i class="caret square left icon"></i>勤務情報登録</h1>
			<div class="ui grid">
				<div class="column">
						<div class="ui calendar">
							<div class="ui input">
								<input type="month" name="inqueryMonth" value="${inqueryMonth}" onchange="quiryMonth()">
							</div>
						</div><br/>
					<table class="table table-striped table-hover table-bordered table-sm">
			            <thead class="table-info" align="center">
			                <tr>
			                    <th>社員番号</th>
			                    <th>現場名</th>
			                    <th>現場表作成</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach items="${ConductTeamList}" var="team" varStatus="status">
			                    <tr>
			                        <td align="center">
			                        	${team.emp_name} (${team.emp_id})
			                        	<input type="hidden" id = "id" class="conductBox" value="(${team.emp_id})" name ="team_emp_id" disabled />
			                        </td>
			                        <td align="center">
			                        	<a href="${pageContext.request.contextPath}/fieldList">${team.field_name}</a>
			                        </td>
			                        <td align="center">
			                        	<button class="ui blue button" type="button" id ="btn${status.index}" class="btn btn-primary" style="margin-top:-5px !important; margin-bottom:-5px !important; font-size: 1.0em!important;" value="${team.field_code}" onclick="writing(${status.index})">作成</button>
			                        </td>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
				</div>
			</div>
			<br>
			<div class="ui">
				<div class="ui button left floated" onclick="backPage()">
					<i class="left chevron icon"></i>戻る
				</div>
			</div>
		</form>
	</div> 
</body>
</html>
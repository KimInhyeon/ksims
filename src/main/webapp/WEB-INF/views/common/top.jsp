<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>KS情報システム株式会社</title>
<script>
$(document).ready(function(){
    $('.ui.dropdown.item').dropdown();
    
    $('.message .close')
    .on('click', function() {
      $(this)
        .closest('.message')
        .transition('fade');
    });
    
    $('.clickMenu').click(function(){
        $(this).children('a').get(0).click();
     });
});

	function check() {
		var format =  /^([0-9][0-9][0-9][0-9][0-9])$/
		var checkId = login.userId.value
		if (!format.test(checkId)){
			alert("IDは5桁の数字をご入力ください")
			login.userId.value = ""
			return false;
		}
		if (!login.userId.value || !login.userPw.value) {
			alert("IDとパスワードを確認して下さい");
			return false;
		}
	}
	
	function logout() {
		if (confirm("ログアウトしますか？") == true) {
			location.href="/ksims/logout"
		} else {
			return;
		}
	}

	function excelOpen() {
		var excel = new ActiveXObject("Excel.Application");
		excel.Visible = true;
		var sourcefile="D:\\share\\excel\\KSIMS_MailTool.xlsm";
		var controlbook=excel.workbooks.open(sourcefile);
	}
	
</script>
</head>

<body>
<%  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Pragma","no-cache");  
response.setDateHeader("Expires",0);
if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>  
		<c:choose> 
			<c:when test="${sessionScope.login ne 0}">
				<c:if test="${param.msg eq 1}">
					<script>
						alert("ご利用ありがとうございました。");
					</script>
				</c:if>
				<c:if test="${param.msg eq 0}">
					<script>
						alert("ログインが必要なサービスです。");
					</script>
				</c:if>
				<c:if test="${param.msg == -1}">
					<script>
						alert("ID又はPWをご確認ください")
					</script>
				</c:if>
				<c:if test="${param.msg == -2}">
					<script>
						alert("接続権限がありません。\n管理者にお問い合わせください。")
					</script>
				</c:if>
			</c:when>
		</c:choose>
 		<div class="ui Compact menu"  style="font-weight: bold; font-size: 1.3em; line-height: 1.0em;  margin-top:-5px">  
		  <div class="item" style="width: 200px;height: 75px">
            	  <a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/css/images/logo.png" style="margin-left:-25px"/></a>
          </div>
                <c:if test="${empty sessionScope.adminFlg}">
                <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'" style="color: #191970"><i class="address book icon"></i>社員管理</a>
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/conductMain'" style="color: #191970"><i class="cogs icon"></i>現場管理</a>
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/conductMain'" style="color: #191970"><i class="calendar alternate icon"></i>勤務管理</a>
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/conductMain'" style="color: #191970"><i class="money bill alternate icon"></i>給料管理</a>
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'" style="color: #191970"><i class="chart pie icon"></i>評価管理</a>
			    </c:if>
          		<c:if test="${sessionScope.adminFlg == '01'}">
			    <div class="ui dropdown item" style="color: #191970" ><i class="address book icon"></i>社員管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/EmployeesListController?curPage=1">社員情報照会</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/EmployeesWriteController">社員情報登録</a></div>                   
                    </div>			    
			    </div>
                <div class="ui dropdown item" style="color: #191970"><i class="cogs icon"></i>現場管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/fieldList">現場照会</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/fieldInfoRegistForm">現場登録</a></div>
                    </div>
			    </div>
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/conductMain'" style="color: #191970"><i class="calendar alternate icon"></i>勤務管理</a>
			    <div class="ui dropdown item" style="color: #191970"><i class="money bill alternate icon"></i>給料管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/MoveTosalaryMain?curPage=1">給料明細照会・作成</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/ExcelReader">給料マスタ情報登録</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/taxedSalarySearch?curPage=1&param=1">給料出力</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/salaryConfirm">給料確定</a></div>
                       <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/payslipSearch">過去給料照会</a></div>
                       <!-- <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/mailSend">給料明細書発送</a></div>  -->
                </div>
                </div>
			   　<a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'" style="color: #191970;margin-left:-20px;"><i class="chart pie icon"></i>評価管理</a>
                 <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/OrderMain'" style="color: #191970"><i class="dollar sign icon"></i>受注管理</a>
			    </c:if>
			    <c:if test="${sessionScope.adminFlg == '02' || sessionScope.adminFlg == '03'}">
			    <div class="ui dropdown item" style="color: #191970"><i class="address book icon"></i>社員管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/EmployeesListController?curPage=1">社員情報照会</a></div>
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/EmployeesChangePasswordController">パスワード変更</a></div>
                    </div>			    
			    </div>
                <div class="ui dropdown item" style="color: #191970"><i class="cogs icon"></i>現場管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/fieldList">現場照会</a></div>
                    </div>
			    </div>		
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/conductMain'" style="color: #191970"><i class="calendar alternate icon"></i>勤務管理</a>			    
			    <div class="ui dropdown item" style="color: #191970"><i class="money bill alternate icon"></i>給料管理
                    <div class="menu">
                      <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/MoveTosalaryMain">給料明細照会</a></div>
            		  <div class="item clickMenu" style="font-size: 0.8em !important;"><a href="${pageContext.request.contextPath}/payslipSearch">過去給料照会</a></div>
                    </div>
                </div>   
			    <a  class="browse item" href="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'" style="color: #191970;"><i class="chart pie icon"></i>評価管理</a>
			    </c:if>
		
		<!--  Message Area -->
		<c:if test="${not empty exception.message}">
			    <div class="ui negative message" style="width: 350px!important;font-size: 0.6em !important; position:absolute; 
			    top: 4%; left: 50%; transform: translateY(-50%) translateX(-50%); -webkit- transform: translateY(-50%) translateX(-50%); z-index:999;">
			        <i class="close icon"></i>
                    <div class="header">${exception.message}</div>
		        </div>
		</c:if>
			<c:if test="${not empty exceptionMessage}">
			    <div class="ui negative message" style="width: 350px!important;font-size: 0.6em !important; position:absolute; 
			    top: 4%; left: 50%; transform: translateY(-50%) translateX(-50%); -webkit- transform: translateY(-50%) translateX(-50%); z-index:999;">
			        <i class="close icon"></i>
                    <div class="header">${exceptionMessage}</div>
		        </div>
		</c:if>	
		<c:if test="${not empty successMessage}">
			    <div class="ui success message" style="width: 350px!important;font-size: 0.8em !important; position:absolute; 
			    top: 4%; left: 50%; transform: translateY(-50%) translateX(-50%); -webkit- transform: translateY(-50%) translateX(-50%); z-index:999;">
			        <i class="close icon"></i>
                    <div class="header">${successMessage}</div>
		        </div>
		</c:if>
		<!--  Login Area -->
        <div class="right menu" style="margin-top:20px;margin-right:5px">
		<form name="login" action="login" method="post" onsubmit="return check();"> 
		<c:choose> 
			<c:when test="${sessionScope.login ne 1}">
				<div class="login" style="display: flex;">
						<input type="text" class="form-control"  name="userId" placeholder="ID" maxlength="5"/>&nbsp;&nbsp;
						<input type="password" class="form-control"  name="userPw" placeholder="PW" maxlength="20" />&nbsp;&nbsp;
						<input class="ui primary button" type="submit" value="LOGIN" />
				</div>
			</c:when>
			<c:when test="${sessionScope.login ne 0}">
					<div class="textboxInfoLine" style="font-size: 0.9em; display: flex;">
						<i class="id card icon" title="社員番号" style="margin-top:10px"></i><div  style="margin-top:10px">&nbsp;${sessionScope.sid}&nbsp;&nbsp;&nbsp;</div><i class="user circle icon" title="社員名"  style="margin-top:10px"></i><div style="margin-top:10px">&nbsp;${sessionScope.sname}&nbsp;様</div> 
						<c:if test="${sessionScope.adminFlg == '01'}"><font color="red"  style="margin-top:10px">【管理者】</font></c:if>
						<c:if test="${sessionScope.adminFlg == '02'}"><font color="blue" style="margin-top:10px">【リーダ】</font></c:if>
						<c:if test="${sessionScope.adminFlg == '03'}"><font color="blue" style="margin-top:10px">【社員】</font></c:if>
						<input class="ui primary button" type="button" value="LOGOUT" onclick="logout();" tabindex="3" />
					</div>
			</c:when>			
		</c:choose>
		</form>
		</div>
	</div>

</body>
</html>
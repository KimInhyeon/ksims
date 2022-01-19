<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>

<title>Insert title here</title>
<script>
$(document).ready(function(){
	$('#footerHistory').hide();
	$('#footerHistoryOnOffId').checkbox();
	$('#footerHistoryOnOffId').change( function(){
	    if ($(this).children('input').is(":checked")) {
	    	$('#footerHistory').show();
	    	$('#footerHistoryOnOffLabel').html("");
	    }else{
	    	$('#footerHistory').hide();
	    	$('#footerHistoryOnOffLabel').html("<div style='font-size: 0.8em!important;'>開いたページを確認する。</div>");
	    }
	});
});
</script>
</head>
<body>	


<div align="left" style="font-size: 1.0em!important; display: flex; height: 15px">
<c:if test="${not empty urlArr && mainFlg != 1}">
	<div id="footerHistoryOnOffId" class="ui checkbox" style="margin-left: 0px;font-size: 1.0em;">
		<input id="footerHistoryOnOff" type="checkbox" />
		<label id="footerHistoryOnOffLabel"><div style="font-size: 0.8em!important;"> 開いたページを確認する。</div></label>
	</div>
	<div id = footerHistory style="margin-top:-1px;font-size: 0.8em!important;">
	開いたページ：<c:forEach var="urlArr" items="${urlArr}" varStatus="i"><i class="right chevron icon divider"></i>
		<c:if test = "${i.last == false}">
			<a class=footerHistory href='${urlArr.arrPageURL}'> ${urlArr.arrPageName} </a>
		</c:if>
		<c:if test = "${i.last == true}">
		 ${urlArr.arrPageName} 
		</c:if>
	</c:forEach>
	</div>
</c:if>
</div><br>
</body>
</html>
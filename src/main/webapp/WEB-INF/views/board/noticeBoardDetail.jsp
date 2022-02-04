<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
	
		<div class="wrapped">
			<div class="article-view-head">
				<h2 class="article-view-headH2">${ndto.notice_title}</h2>
				<p class="pName">KS&nbsp;InfoSys&nbsp;·&nbsp;${ndto.notice_writer}</p>
				<div class="wrap-info">
					<c:set var="dateValue" value="${ndto.notice_regdate }" />
					<span class="wDate"><i class="time icon"></i>${fn:substring(dateValue,0,10)}</span>
					<span class="pv"><i class="eye icon"></i>${ndto.notice_readcount}</span>
				</div>
			</div>
			<div class="article-view-content">
				<div class="content-area">
					${ndto.notice_content}
				</div>
			</div>
			<hr style="margin-top:30px;"/>
			<c:if test="${!empty fileList}">
				<div class="form-group fileArea">
					<div class="fileWrap">
						<label for="inp-type-4" class="control-label">File</label>
						<div>
							<div class="form-control file_list">
								<c:forEach var="list" items="${fileList}">
									<a href="/ksims/noticeBoardDownload/${list.notice_file_idx}" class="disply_block">
										<i class="file icon" aria-hidden="true"></i>
										${list.original_name}
									</a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		
		<div class="inputArea">
			<button type="button" onclick="backPage()" id="list_Btn"
				class="btn btn-primary">戻る</button>
			<c:if test="${authCode==01}">
				<button type="button" id="modify_Btn" class="btn btn-warning">修正</button>
				<button type="button" id="delete_Btn" class="btn btn-danger">削除</button>
			</c:if>
		</div>
		<form id="utilForm" method="get">
			<input type="hidden" id="notice_id" name="notice_id" value='<c:out value="${ndto.notice_id}"/>'>
			<input type="hidden" id="curPage" name="curPage" value='<c:out value="${cri.curPage}"/>'>
		</form>
	</div>
<script>
	$(document).ready(function(){
		function backPage() {
			location.href=`${pageContext.request.contextPath}/NoticeBoardList?curPage=${cri.curPage}`;
		}
	
		function modifyNoticeBoard(notice_id) {
			location.href=`${pageContext.request.contextPath}/noticeBoardModify/${notice_id}`;
		}
	
		let form = $("#utilForm");
		
		$("#list_Btn").on("click", function(e){
			form.find("#notice_id").remove();
			form.attr("action", "/ksims/NoticeBoardList?curPage=${cri.curPage}");
			form.submit();
		});	
		
		$("#delete_Btn").on("click", function(e){
			if (confirm("投稿を削除しますか?") == true) {
				form.attr("action", "/ksims/noticeBoardDelete");
				form.submit();
			}else{
				return false;
			}
		});
		
		$("#modify_Btn").on("click", function(e){
			form.attr("action", "/ksims/noticeBoardModify");
			form.submit();
		});	
	});
</script>
</body>
</html>
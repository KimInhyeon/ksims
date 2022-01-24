<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ</title>

</head>
<body>
	<div class="container">
		<!--<button class="btn btn-primary" onclick="imageClean()">이미지 청소</button>-->
		<div class="row marketing">
			<div class="col-lg-12">
				<table class="table" style="margin-top: 30px;">
					<thead>
						<tr>
							<th>NO</th>
							<th>タイトル</th>
							<th>作成者</th>
							<th>照会数</th>
							<th>日付</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty nlist}">
								<tr>
									<td colspan="5" style="text-align:center;"><h4>登録された投稿がありません。</h4></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${nlist}" var="nlist">
								<c:set var="dateValue" value="${nlist.notice_regdate }" />
								<c:set var="noticeTitle" value="${nlist.notice_title }" />
									<tr>
										<td class="col-md-1">${nlist.notice_id}</td>
										<c:choose>
											<c:when test="${nlist.notice_title.length()>30}">
												<td class="col-md-6"><a class="noticeDetail" href='<c:out value="${nlist.notice_id}"/>'>${fn:substring(noticeTitle,0,30)}...</a></td>
											</c:when>
											<c:otherwise>
												<td class="col-md-6"><a class="noticeDetail" href='<c:out value="${nlist.notice_id}"/>'>${noticeTitle}</a></td>
											</c:otherwise>
										</c:choose>
										<td class="col-md-2">${nlist.notice_writer}</td>
										<td class="col-md-1 readCount">${nlist.notice_readcount}</td>
										<td class="col-md-2">${fn:substring(dateValue,0,10)}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<form id="noticeBoardDetail" method="get">
					<input type="hidden" name="curPage" value="${page.curPage }">

				</form>
			</div>
		</div>
		<div class="btn-area" style="text-align: end; margin-top: 20px">
			<button class="btn btn-primary"
				onclick="location.href='/ksims/NoticeBoardWriteController'">書き込み</button>
		</div>
		<div align="center">
			<div class="ui pagination menu">
				<c:if test="${page.curBlock > 1}">
					<a class="item" href="1"><i class="angle double left icon"></i></a>
				</c:if>
				<c:if test="${page.curBlock > 1}">
					<a class="item" href="${page.prevPage}"><i
						class="angle left icon"></i></a>
				</c:if>
				
				<c:forEach var="pageNum" begin="${page.blockBegin}"
					end="${page.blockEnd}">
					<c:choose>
						<c:when test="${pageNum == page.curPage}">
							<div class="active item">${pageNum}</div>
						</c:when>
						<c:otherwise>
							<a class="item" href="${pageNum}"> ${pageNum} </a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${page.curBlock < page.totBlock}">
					<a class="item" href="${page.nextPage}"><i
						class="angle right icon"></i></a>
				</c:if>
				<c:if test="${page.curBlock < page.totBlock}">
					<a class="item" href="${page.totPage}"><i
						class="angle double right icon"></i></a>
				</c:if>
			</div>
		</div>
	</div>
<script>
	$(document).ready(function() {
		let Result = '<c:out value="${resultOk}"/>';
		checkOk(Result);
		function checkOk(Result) {
			if (Result === "ok") {
				alert("投稿が登録されました。");
			}else if(Result === "modifyOk"){
				alert("投稿が修正されました。");
			}else if(Result === "deleteOk"){
				alert("投稿が削除されました。");
			}else{
				return;
			}
		}
		

		let moveForm = $("#noticeBoardDetail");

		$(".noticeDetail").on("click",function(e) {
			e.preventDefault();

			moveForm.append("<input type='hidden' name='notice_id' value='" + $(this).attr("href")+ "'>");
			moveForm.attr("action","/ksims/noticeBoardDetail");
			moveForm.submit();
		});

		$(".ui.pagination.menu a").on("click",function(e) {
			e.preventDefault();
			moveForm.find("input[name='curPage']").val($(this).attr("href"));
			moveForm.attr("action","/ksims/NoticeBoardList");
			moveForm.submit();
		});
	});
	
	
	function imageClean(){
		$.ajax({
			url:`/ksims/noticeImageClean2`,
			dataType:"json"
		}).done(res=>{
			console.log(res);
			if(res.result=="ok"){
				alert('이미지 청소 성공');
			}
		}).fail(error=>{
			console.log("실패",error);
		});
	}
	
</script>
</body>
</html>
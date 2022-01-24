<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoticeModify</title>
<script defer src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<script src="summernote/summernote-bs4.js"></script>
<link href="summernote/summernote-bs4.css" rel="stylesheet">

<script>
function backPage() {
	if (confirm("キャンセルしますか?") == true) {
		window.history.back();
	}else{
		return false;
	}
	
}
/*
function noticeBoardModify(e,notice_id) {
	e.preventDefault()
	if($('#title').val() == ""){
	    alert("제목을 입력하세요.");
	    $('#title').focus();
	    return false;
	}else if($('#content').val() == ""){
		alert("내용을 입력하세요.");
		$('#content').focus();
		return false;
	}else{
		if (confirm("登録しますか？") == true) {
			
			let data={
				emp_id:$('#emp_id').val(),
				notice_writer:$('#notice_writer').val(),
				notice_readcount:$('#notice_readcount').val(),
				notice_title:$('#notice_title').val(),
				notice_content:$('#notice_content').val(),
			};
			console.log(data);
			
			$.ajax({
				type:"PUT",
				url:`/ksims/noticeBoardModify/${notice_id}`,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				dataType:"json"
			}).done(res=>{ 
				console.log("성공",res);
				alert('게시글이 수정 되었습니다!');
				location.href=`${pageContext.request.contextPath}/NoticeBoardList?curPage=1`;
			}).fail(error=>{ 
				alert('수정실패');
				console.log(error);
				
			});
			
		} else {
			return false;
		}
	}
	
}
*/

function check() {
	if (confirm("投稿を修正しますか?") == true) {
		if($('#notice_title').val() == ""){
			alert('タイトルを入力してください。');
			$('#notice_title').focus();
			return false;
		}else if($('#notice_content').val() == ""){
			alert('内容を入力してください。');
			$('#notice_content').focus();
			return false;
		}
		return true
	}else{
		return false;
	}
	
}

/////////// 파일관련
///// 파일추가
const fileList = '<c:out value="${empty fileList}"/>';

let fileIdx = <c:out value="${empty fileList}"/> ? 0 : fileList.length; /*[- 파일 인덱스 처리용 전역 변수 -]*/
function addFile() {
const fileDivs = $('div[data-name="fileDiv"]');
if (fileDivs.length > 2) {
	alert('ファイルは最大3つまでアップロードできます。');
	return false;
}
document.getElementById('changeYn').value = 'Y';
fileIdx++;
const fileHtml = 
	`<div data-name="fileDiv" class="form-group filebox bs3-primary">
		<div class="labelWrap">
		<label for=file_`+fileIdx+` class="control-label">ファイル</label>
		</div>
		<div class="fileWrapped">
			<input type="text" class="upload-name" value="ファイル探索" readonly />
			<label for=file_`+fileIdx+` class="control-label">ファイル添付</label>
			<input type="file" name="files" id=file_`+fileIdx+` class="upload-hidden" onchange="changeFilename(this)" />

			<button type="button" onclick="removeFile(this)" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
				<i class="minus icon" aria-hidden="true"></i>
			</button>
		</div>
	</div>
`;

$('#btnDiv').before(fileHtml);
}
///// 파일삭제
function removeFile(elem) {
document.getElementById('changeYn').value = 'Y';

const prevTag = $(elem).prev().prop('tagName');
if (prevTag === 'BUTTON') {
	const file = $(elem).prevAll('input[type="file"]');
	const filename = $(elem).prevAll('input[type="text"]');
	file.val('');
	filename.val('ファイル探索');
	
	$(elem).prevAll('input[name="fileIdxs"]').remove();
	return false;
}

const target = $(elem).parents('div[data-name="fileDiv"]');
target.remove();
}

//파일이름 렌더링
function changeFilename(file) {
	
document.getElementById('changeYn').value = 'Y';

file = $(file);
const filename = file[0].files[0].name;
const target = file.prevAll('input.upload-name');
target.val(filename);
file.prevAll('input[name="fileIdxs"]').remove();
}

</script>
<style>
/*
 * start of file css
 */
.filebox{
 	display:flex;
 }
.filebox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}
.filebox.bs3-primary .fileWrapped>label {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
}
.filebox .fileWrapped>label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	font-weight: 600;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}
.filebox .upload-name {
	display: inline-block;
	width: 350px;
	padding: .5em .75em;
	/* label의 패딩값과 일치 */
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none;
	/* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}
.btn_write_area{
	margin-top:30px;
	display: flex;
    justify-content: space-between;
}
.labelWrap{
	margin: 8px 10px 0 0;
}
.contentWrap{
	margin-bottom:30px;
}
/*
 * end of file css
 */
</style>
</head>


<body>
<div class="container">
	<form id="noticeBoardModifyForm" method="post" action="/ksims/noticeBoardModify" name="noticeBoardModifyForm" role="form" onsubmit="return check();" enctype="multipart/form-data">
		<input type="hidden" id="curPage" name="curPage" value="${cri.curPage}">
		<input type="hidden" id="notice_id" name="notice_id" value="${ndto.notice_id }">
		<input type="hidden" id="emp_id" name="emp_id" value="${ndto.emp_id}">
		<input type="hidden" id="notice_writer" name="notice_writer" value="${ndto.notice_writer}">
		<input type="hidden" id="notice_readcount" name="notice_readcount" value="${ndto.notice_readcount}">
		<input type="hidden" id="changeYn" name="changeYn" value="N" />
		
		<br style="clear: both">
		<h3 style="margin-bottom: 25px;">投稿修正</h3>
		<div class="form-group">
			<input type="text" class="form-control" id="notice_title" name="notice_title" value="${ndto.notice_title}"
				placeholder="Title">
		</div>
		<div class="form-group contentWrap">
			<textarea class="form-control summernote" id="notice_content" name="notice_content" placeholder="content" maxlength="140" rows="7">${ndto.notice_content}</textarea>
		</div>
		
		<c:choose>
			<c:when test="${empty fileList }">
				<div data-name="fileDiv" class="form-group filebox bs3-primary">
					<div class="labelWrap">
						<label for="file_0" class="control-label">ファイル</label>
					</div>
					<div class="fileWrapped">
						<input type="text" class="upload-name" value="ファイル探索" readonly />
						<label for="file_0" class="control-label">ファイル添付</label>
						<input type="file" name="files" id="file_0" class="upload-hidden" onchange="changeFilename(this)" />
					
						<button type="button" onclick="addFile()" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
							<i class="plus icon" aria-hidden="true"></i>
						</button>
						<button type="button" onclick="removeFile(this)" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
							<i class="minus icon" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="list" items="${fileList}" varStatus="status">
					<div data-name="fileDiv" class="form-group filebox bs3-primary">
						<div class="labelWrap">
							<label for="file_${status.index}" class="control-label">ファイル</label>
						</div>
						<div class="fileWrapped">
							<input type="hidden" name="fileIdxs" value="${list.notice_file_idx }">
							<input type="text" class="upload-name" value="${list.original_name }" readonly />
							<label for="file_${status.index }" class="control-label">ファイル添付</label>
							<input type="file" name="files" id="file_${status.index }" class="upload-hidden" onchange="changeFilename(this)" />
							
							<c:if test="${status.first}">
								<button type="button" onclick="addFile()" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
									<i class="plus icon" aria-hidden="true"></i>
								</button>
							</c:if>
							
							<button type="button" onclick="removeFile(this)" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
								<i class="minus icon" aria-hidden="true"></i>
							</button>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<div id="btnDiv" class="btn_write_area">
			<div class="ui button left floated"  onclick="backPage()"><i class="left chevron icon"></i>戻る</div>
					
			<div class="btn_write"><button type="submit" id="submit" name="submit" class="btn btn-primary pull-right">修整</button></div>
		</div>
			
	</form>
</div>

<script>
	$(document).ready(function() {
		
		$('.summernote').summernote({
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
			toolbar : [
				// 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
				['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				['fontsize', ['fontsize']],
				// 글자색
				['color', ['color']],
				// 표만들기
				['table', ['table']],
				// 글머리 기호, 번호매기기, 문단정렬
				['para', ['ul', 'ol', 'paragraph']],
				// 줄간격
				['height', ['height']],
				// 그림첨부, 링크만들기, 동영상첨부
				['insert',['picture','link']],
				// 코드보기, 확대해서보기, 도움말
				['view', ['codeview','fullscreen', 'help']]
			],
			fontSizes: ['8','9','10','11','12','13','14','15','16','17','18','19','20','24','30','36','48','64','82','150'],
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					if(files[0].type != "image/jpeg" && files[0].type != "image/jpg" && files[0].type != "image/png"){
						alert("JPG, JPEG, PNG 拡張子だけアップロード可能です。");
			            return;
					}
					sendFile(files[0], this);
				},onChange:function(contents, $editable){
					setContentsLength(contents, 0);
				}
			}
		});
		
		$('#notice_title').on('keyup', function() {
			console.log('111',$(this).val());
	        if($(this).val().length > 50) {
	        	alert('入力出来る文字数を超過しています。');
	            $(this).val($(this).val().substring(0, 50));
	        }
	    });
		
	});

	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file2', file);
		$.ajax({
			data : form_data,
			type : "POST",
			url : '/ksims/noticeBoardImage',
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(url) {
				$(el).summernote('insertImage', url, function($image) {
					$image.css('width', "50%");
				});
			}
		});
	}
	
	function setContentsLength(str, index) {
		var status = false;
		var textCnt = 0; //총 글자수 
		var maxCnt = 2000; //최대 글자수
		var editorText = f_SkipTags_html(str); //에디터에서 태그를 삭제하고 내용만 가져오기
		//editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
		//editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거 
		textCnt = editorText.length;
		if(maxCnt > 0) {
			if(textCnt > maxCnt) {
				status = true;
				} 
			} 
		if(status) {
			//var msg = "등록오류 : 글자수는 최대 "+maxCnt+"까지 등록이 가능합니다. / 현재 글자수 : "+textCnt+"자";
			alert('入力出来る文字数を超過しています。');
			$('.summernote').summernote("code",editorText.substring(0,1950));
			}
		}

	function f_SkipTags_html(input, allowed) { // 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
		allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
		var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi;
		return input.replace(tags, function ($0, $1) {
			return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
		});
	}
</script>
</body>
</html>
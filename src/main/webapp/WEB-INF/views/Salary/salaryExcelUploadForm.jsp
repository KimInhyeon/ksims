<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/modal/Salary/excelUploadProgressModal.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />

<title></title>

<script>
function pageSubmit() {
	var fileSize = $("#iSize").val();
	var numSize = Number(fileSize);
	if(excelForm.file_type.value==""){
		alert("表のタイプを選択してください。");
		return false;
	}
	if(numSize==0){
		alert("ファイルを選択してください。");
		return false;
	}
	if (confirm("保存しますか？") == true) {
		
		if (numSize > 1024*1024*10) {
			alert("ファイルは最大10MBを超えられないです。");
			return false;
		}
		
		document.getElementById("excelForm").action="ExcelReaderUpload"
		document.getElementById("excelForm").submit()
		
	} else {
		return;
	}
	$('#progress_modal').show();
	setInterval('progressTest()',100);
}

</script>
<script>
/* ファイル容量測定 */
jQuery.browser = {};
$(document).ready(function() {
	
    $("#upload").change(function() {
        var iSize = 0;
        
        if($.browser.msie){
            
        	var objFSO = new ActiveXObject("Scripting.FileSystemObject");
            var sPath = $("#upload")[0].value;
            var objFile = objFSO.getFile(sPath);
            var realSize = objFile.size;
            iSize = realSize/ 1024;
        }else {
        	iSize = ($("#upload")[0].files[0].size / 1024);
        	realSize = ($("#upload")[0].files[0].size);
        }
       
        if (iSize / 1024 > 1) {
            
        	if (((iSize / 1024) / 1024) > 1) {
                iSize = Math.round(((iSize / 1024) / 1024) * 100) / 100;
                $("#lblSize").html( iSize + "Gb");
                $("#iSize").val( realSize );
                
            }else{
                iSize = Math.round((iSize / 1024) * 100) / 100;
                $("#lblSize").html( iSize + "Mb");
                $("#iSize").val( realSize );
            }
        }else{
        	iSize = Math.round(iSize * 100) / 100;
            $("#lblSize").html( iSize  + "kb");
        	$("#iSize").val( realSize );
        }  
    });
});
function selectFile() {
	document.getElementById("upload").click();
}
function getFileName() {
	var fileInput = document.getElementById("upload");
	const files = fileInput.files;
	document.getElementById("display").innerHTML=files[0].name;
	var fileName = "";
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        if ( checkFileName(file.name) ){
        	alert(fileName);
        	fileName += file.name + '\r\n';
        } else{
        	return false;
        }
    }
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

	th{background:#e9ecef;}
</style>
</head>

<body>
<!-- exception test -->
	<form id="excelForm" name="excelForm" method="post"
		encType="multipart/form-data">
	<div style="margin-left:20%;">
		<h1><i class="caret square left icon"></i>給料マスター情報登録</h1>
	</div>
		<center>
			<table width="60%" class="table table-bordered" style="width:60%;">
				<tr>
					<th width="20%" scope="col">Excelファイル</th>
					<td style="vertical-align:middle;">
						<div style="width:100%; height:30%; display:table;">
						<div class="slectionContainer" style="width:70%; text-align: center; float:left; display: table-cell;">
							<select name="file_type" class="ui fluid dropdown">
								<option value="" selected>ファイル内容選択</option>
								<option value="income_tax">給与所得の源泉徴収税額表(月額表)(.xls)</option>
								<option value="insurance">健康保険・厚生年金保険の保険料額表(.xlsx)</option>
							</select>
						</div>
						
						<div style="width:30%; float:right; display: table-cell;">
						<button type="button" class="ui primary button" onclick="selectFile()" style="margin:0 5px;">ファイル選択</button>		
						</div>
						</div>
						<div style="display: inline;">
						ファイル名 : <span id="display" style="font-weight: bold;"></span><br>
						Size is : <b><label id="lblSize"></label></b>
						<input type="file" name="upload" id="upload" onchange="getFileName()" hidden="hidden" /> 
								<input type="hidden" id="upload_path" name="upload_path" value=""/>
								<input type="hidden" id="file_path_arr" name="file_path_arr" value=""/>
								<input type="hidden" id="org_number_arr" name="org_number_arr" value=""/>	
								<input type="hidden" id="current_level_fileUpload" name="current_level_fileUpload" value="" />
						<br/><font color="red" size="2">アップロードは最大10MBまで可能です。既存のファイルは交替されます。</font> <br />
					<input type="hidden" name="iSize" id="iSize" value="0"
						readonly="readonly"/>
						</div>
					</td>

				</tr>
				<tr>
				<th>外部リンク</th>
				<td colspan="2">
					<a href="https://www.nta.go.jp/users/gensen/index.htm">源泉徴収税額表(国税庁)</a>
					<br>
					<a href="https://www.kyoukaikenpo.or.jp/g7/cat330/sb3150/">保険料額表(全国健康保険協会)</a>
				</td>
				</tr>
			</table>
		</center>

		<!-- button box -->
		<div class="ui grid" style="margin-right:18.9%; margin-top:0.1%; float:right">
			<div align="center">
				 <input class="ui primary button"
					type="button" value="登録" style="float:right;" onclick="pageSubmit();"/>
			</div>
		</div>
	</form>

</body>
</html>
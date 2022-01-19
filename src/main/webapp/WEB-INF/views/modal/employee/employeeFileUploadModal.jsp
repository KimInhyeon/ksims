<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.dz-image img{
	    width: 100px;
	    height: 100px;
	}
	.alink {
		color: yellow;
	}
	
 	@media screen and (max-width: 320px) {
	
		.dropzone .dz-preview {
			width:98px;
		}
		.dropzone {
			padding:0px 0px
		}
		.dropzone .dz-preview  {
			margin:3px;
		}
		
		.dropzone .dz-preview .dz-image {
			width:98px;
			hight:100px;
		}
	} 
</style>
<script>

Dropzone.options.fileDropzone = {
		url: '/ksims/empFileUpload',           //업로드할 url (ex)컨트롤러)
	    init: function () {
	    	this.on("sending", function(file, xhr, formData) {
	            formData.append("uploadEmpId", ${empManage.empId});
	            console.log(formData)
	          }); 
	        /* 최초 dropzone 설정시 init을 통해 호출 */
	        var dropArea = document.querySelector("#fileDropzone");
	        var myDropzone = this;
	        var flagCount = 0;     
	        var fileList = new Array(); 
	        <c:forEach items="${fileList}" var="fileList" varStatus="vs">
	        
	   		    fileList.push({index:"${vs.count}", empFileIdx: "${fileList.empFileIdx}", empId: "${fileList.empId}", logicalDelFlg: "${fileList.logicalDelFlg}", fileName: "${fileList.fileName}", fileUrl: "${fileList.fileUrl}", fileUpdateDate: "${fileList.fileUpdateDate}", empIdx: "${fileList.empIdx}"});
	        	console.log(fileList);
	        </c:forEach>
	        
	        
	        displayFiles(myDropzone, fileList);
	        addLinks(fileList);
	        
	        dropArea.addEventListener("drop", function (e) {
	            flagCount = 0;
	            $('.dz-preview' ).remove();
	            
	            myDropzone.processQueue(); 
	        });
	        
	        myDropzone.on("addedfile", function (e) {
	            flagCount = 0;
	            $('.dz-preview' ).remove();
	            
	            myDropzone.processQueue(); 
	        });
	       
	        // ファイルアップロード以後のイベント
	        this.on("complete", function (file) {
	            if (this.getUploadingFiles().length === 0 && this.getQueuedFiles().length === 0) {
	              if (flagCount === 0) {
	            	  flagCount++;
	                  $.ajax({
	                      url: 'getEmpFileInfo',
	                      type: 'POST',
	          			  data:{
	        			  	  emp_id : ${empManage.empId},
	                  		  emp_idx : ${empManage.empIdx}
	        			  },
	                      dataType: 'JSON',
	                      success: function(response){
	                          displayFiles(myDropzone, response);
	                          addLinks(response);
	                        //function updateTable(myDropzone, response) 
	                      }
	                  });
	              }
	          }
	       });
	    },
	    autoProcessQueue: false,    // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
	    clickable: true,            // 클릭가능여부
	    //thumbnailHeight: 90,        // Upload icon size
	    //thumbnailWidth: 90,         // Upload icon size
	    //maxFiles: 5,                // 업로드 파일수
	    maxFilesize: 10,            // 최대업로드용량 : 10MB
	    parallelUploads: 99,        // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
	    addRemoveLinks: false,       // 삭제버튼 표시 여부
	    uploadMultiple: true,       // 다중업로드 기능
/* 	    renameFilename: false, */
	};
	    
	 function displayFiles(myDropzone, fileList) {
		 var html = "";
		 $("#dynamicTbody").html("");
		 var dCheck = new Array(); 
	     $.each(fileList, function(key,value) {
	    	 
	    	 var empIdx = fileList[0].empIdx;
	    	 console.log(fileList);
	    	 var mockFile = { name: value.fileName, size: value.fileUpdateDate };
	        //myDropzone.emit("addedfile", mockFile);
	        // myDropzone.emit("thumbnail", mockFile, "http://localhost:8080/ksims/images/" + value.fileUrl);
	        // myDropzone.emit("complete", mockFile);
	       //  $('div').remove('.dz-progress');
	       //  $('div').remove('.dz-size');
	       
	         html = html +  "<tr><td>" + value.fileName + "</td>" +
		     "<td>" + value.fileUpdateDate + "</td>" +     	   
	    	 "<td><div style='margin-top: 10px !important;' class='link_buttons'>" +
	         "<a id='addLinks' href='/ksims/download?fileUrl=" + value.fileUrl + "&amp;fileName=" + value.fileName + "' class='addLinks'>" +
	         "<i class='big arrow alternate circle down icon'></i></a>" +
	         "<input type='text' id='empFileIdx' name='empFileIdx' class='form-control' value='" + value.empFileIdx + "' hidden='hidden' />" +
	         "<a id='addLinks' href='/ksims/deleteFile?empFileIdx=" + value.empFileIdx + "&empId=" + value.empId + "&empIdx=" + empIdx + "' class='addLinks'>" + 
	         "<i class='big trash alternate icon'></i></a></div></td></tr>";
	       	
/* 				if(value.includes(fileName)){
					myDropzone.removeFile(value);
				} */
	         
	     });
         $("#dynamicTbody").append(html);
         

	 }
	 
	 
/* 	 function deleteFile(myDropzone, fileList) {
	     $.each(fileList, function(key,value) {
             $.ajax({
                 url: 'deleteFile',
                 type: 'POST',
     			  data:{
   			  	  emp_id : ${empManage.empId},
             		  emp_idx : ${empManage.empIdx}
   			  },
                 dataType: 'JSON',
                 success: function(response){
                 	if(value.indexOf(fileName) == -1) value.push(fileName);
         	       console.log(html);
                 } */
                 /*          myDropzone.on("complete", function(file) {
           	  myDropzone.removeFile(file);
           	}); */

	 
	  function addLinks(fileList) {
	     $('.dz-filename').each(function(index, element) {
	   	    (function(index) {
	   	      $(element).attr("id", "filename_" + index);
	   	    }(index));
	   	    
	   	    var myClass = "link_buttons";
	   	    var delbtn = "delete_link";
	   	    var fileId = "filename_" + index;
   	    
    	    var download_link = $("<div style='margin-top: 10px!important;'><a href='/ksims/download?fileUrl=" + fileList[index].fileUrl + "&amp;fileName=" + fileList[index].fileName + "' class='alink'><i class='big arrow alternate circle down icon'></i></a>").addClass(myClass);
    	   	var delete_link = $("<a href='/ksims/deleteFile?empFileIdx=" + fileList[index].empFileIdx + "' class='alink'><i class='big trash alternate icon'></i></a></div>").addClass(delbtn); 
   	 
   	   
   	    $("#"+ fileId).after(delete_link);
   	    $("#"+ fileId).after(download_link); 
   	    
   		});
 	}
	  
    function closeModal() {
		    $('#emp_upload_modal').modal('toggle');
		    //alert();
    }

	  
 </script>
<body>

    <div  class="dropzone-area" id="emp_upload_modal">
	<button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onclick="closeModal()">
			<span aria-hidden="true">&times;</span>
	</button>	    
<!--     <div class="dropzone" id="fileDropzone" align="center"> -->
        <form id="fileDropzone" class="dropzone" align="center" enctype="multipart/form-data" >
				<div class="column">
            <label for="fld">KS情報システム</label>
            <h1>ファイルをDrag＆Dropしてください。</h1>
            <table style="width: 800px; margin:auto;" id="dynamicTable" name = "dynamicTable" class="dynamicTable" >
            <thead class="table-info" align="center" data-backdrop="static"> 
            	<tr>
            		<th style="text-align:center ;margin-left:50px">ファイル名</th>
            		<th style="text-align:center ;margin-left:50px">ファイル更新日付</th>
            		<th style="text-align:center ;margin-left:50px"></th>
            	</tr>
			</thead>
			<tbody id="dynamicTbody">

			</tbody>
		</table>
</div>

        </form>
        
    </div>

 </body>
</html>
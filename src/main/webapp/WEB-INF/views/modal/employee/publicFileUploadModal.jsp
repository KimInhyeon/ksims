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
    url: '/ksims/pubFileUpload',          //업로드할 url (ex)컨트롤러)
    init: function () {
        /* 최초 dropzone 설정시 init을 통해 호출 */
        var dropArea = document.querySelector("#pubFileDropzone");
        var myDropzone = this;
        var flagCount = 0;     
        var pubFileList = new Array(); 
        <c:forEach items="${pubFileList}" var="pubFileList" varStatus="vs">
        
        pubFileList.push({pubFileIdx: "${pubFileList.pubFileIdx}", empId: "${pubFileList.empId}", pubFileName: "${pubFileList.pubFileName}", pubFileUrl: "${pubFileList.pubFileUrl}", pubFileUpdateDate: "${pubFileList.pubFileUpdateDate}"});
        	console.log(pubFileList);
        </c:forEach>
        
        displayFiles(myDropzone, pubFileList);
        addLinks(pubFileList);
        
        dropArea.addEventListener("drop", function (e) {
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
                      url: 'getPubFileInfo',
                      type: 'GET',
                      data: {},
                      dataType: 'JSON',
                      success: function(response){
                        displayFiles(myDropzone, response);
                        addLinks(response);
                      }
                  });
              }
          }
       });
    },
    autoProcessQueue: false,    // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
    clickable: true,            // 클릭가능여부
    thumbnailHeight: 90,        // Upload icon size
    thumbnailWidth: 90,         // Upload icon size
    //maxFiles: 5,                // 업로드 파일수
    maxFilesize: 10,            // 최대업로드용량 : 10MB
    parallelUploads: 99,        // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
    addRemoveLinks: false,       // 삭제버튼 표시 여부
    uploadMultiple: true,       // 다중업로드 기능
};
    
 function displayFiles(myDropzone, photoList) {
     $.each(photoList, function(key,value) {
         var mockFile = { name: value.pubFileName, size: value.pubFileUpdateDate };
         myDropzone.emit("addedfile", mockFile);
         myDropzone.emit("thumbnail", mockFile, "http://localhost:8080/ksims/images/" + value.pubFileUrl);
         myDropzone.emit("complete", mockFile);
         $('div').remove('.dz-progress');
         $('div').remove('.dz-size');
     });
 }
 
 function addLinks(photoList) {
     $('.dz-filename').each(function(index, element) {
   	    (function(index) {
   	      $(element).attr("id", "filename_" + index);
   	    }(index));
   	    
   	    var myClass = "link_buttons";
   	    var fileId = "filename_" + index;
   	    
   	    var download_link = $("<div style='margin-top: 10px!important;'><a href='/vender/goods/download?filePathName=" + photoList[index].photoUrl + "&fileOrgName=" + photoList[index].photoName + "' class='alink'><i class='big arrow alternate circle down icon'></i></a><a href='/vender/goods/deletePhoto?photoIndex=" + photoList[index].photoIdx +"' class='alink'><i class='big trash alternate icon'></i></a></div>").addClass(myClass);
   	//    var delete_link = $("<a href='/deletePhoto?photoIndex=" + photoList[index].photoIdx + "'><i class='trash alternate icon'></i></a>").addClass(myClass);
   	 
   	   
   	//   $("#"+ fileId).after(delete_link);
   	    $("#"+ fileId).after(download_link);
   	    
   	});
 }
</script>
<body>
    <div class="dropzone-area" id="pub_upload_modal">
        <form id="pub" name="pub">
            <label for="fld">KS情報システム</label>
            <h1>ファイルをDrag＆Dropしてください。</h1>
            <div class="dropzone" id="fileDropzone" align="center"></div>
        </form>
    </div>
 </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/modal/employee/employeeFileUploadModal.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
var categoryContentBasic = [
	{ category: 'South America', title: 'Brazil' },
	{ category: 'South America', title: 'Peru' },
	{ category: 'North America', title: 'Canada' },
	{ category: 'Asia', title: 'South Korea' },
	{ category: 'Asia', title: 'Japan' },
	{ category: 'Asia', title: 'China' },
	{ category: 'Europe', title: 'Denmark' },
	{ category: 'Europe', title: 'England' },
	{ category: 'Europe', title: 'France' },
	{ category: 'Europe', title: 'Germany' },
	{ category: 'Africa', title: 'Ethiopia' },
	{ category: 'Africa', title: 'Nigeria' },
	{ category: 'Africa', title: 'Zimbabwe' },
];

$(document).ready(function () {
	$('#emp_upload_modal').modal('hide');
	
	var partnerCompFlg = ${empManage.partnerCompFlg};
	
	if (partnerCompFlg) {
		$("#empPostNo").attr("readonly",true).attr("disabled",true);
		$("#empAddr").attr("readonly",true).attr("disabled",true);
		$("#emergencyPhoneNumber").attr("readonly",true).attr("disabled",true);
		$("#empRelationDd").attr("readonly",true).attr("disabled",true);
	}

	//empfile
	$('#btnUp').popup({
		popup : $('#fileListArea')
	});
	
	$('#btnUp').mouseover(function () {
		var empId = $('#empId').val();
		var empIdx = $('#empIdx').val();
		console.log(empId);
		
		$.ajax({
			url:"getEmpFileInfo",
			type:"POST",
			data:{
				emp_id : empId,
				emp_idx : empIdx
			},
			success: function(data){
				$('#fileListArea').html("");
				
				$.each(data, function(index, value) {
				      console.log(index + " : " + value.fileUrl);
				      
				      $('#fileListArea').append(
				    		  value.fileUrl +
				    		  "<br/>"
				       );
				});

			},
			error: function(){
				alert("通信Error");
			}
		});
	});

	$('.emp.checkbox').checkbox();
	
	empInsuranceFlgResult();
	pensionFlgResult();
	healthInsuranceFlgResult();
	empSpouseFlgResult();

	//toggle状態
	function healthInsuranceFlgResult() {
		var healthInsuranceFlg = document.getElementById("healthInsuranceFlg").value;
		if(healthInsuranceFlg=="true"){
			$('#healthInsuranceFlgCkb').checkbox('check');
		}else{
			$('#healthInsuranceFlgCkb').checkbox('uncheck');
		}
	};

	function pensionFlgResult() {
		var pensionFlg = document.getElementById("pensionFlg").value;
		if(pensionFlg=="true"){
			$('#pensionFlgCkb').checkbox('check');
		}else{
			$('#pensionFlgCkb').checkbox('uncheck');
		}
	};

	function empInsuranceFlgResult() {
		var empInsuranceFlg = document.getElementById("empInsuranceFlg").value;
		if(empInsuranceFlg=="true"){
			$('#empInsuranceFlgCkb').checkbox('check');
		}else{
			$('#empInsuranceFlgCkb').checkbox('uncheck');
		}
	};

	function empSpouseFlgResult() {
		var empSpouseFlg = document.getElementById("empSpouseFlg").value;
		if(empSpouseFlg=="true"){
			$('#empSpouseFlgCkb').checkbox('check');
		}else{
			$('#empSpouseFlgCkb').checkbox('uncheck');
		}
	};
	
	$('#empSpouseFlgCkb').checkbox({ onChange: function() {
	    if ($(this).is(':checked')) {
	    	$('#empSpouseFlg').val(true);
	    } else {
	    	$('#empSpouseFlg').val(false);
	    }
	}
	});
	
	$('#empRelation option[value=P]')
	.prop('selected',
			'selected')
	.change();
	$("#empRelation").attr(
	"readonly", true)
	.attr("disabled",
			true);
	
	//カレンダー
	 $("#stayExpirationDate").datepicker({
			closeText: '閉じる',
			prevText: '先月',
			nextText: '来月',
			currentText: '今日',
			monthNames: ['1月(JAN)','2月(FEB)','3月(MAR)','4月(APR)','5月(MAY)','6月(JUN)',
			'7月(JUL)','8月(AUG)','9月(SEP)','10月(OCT)','11月(NOV)','12月(DEC)'],
			monthNamesShort: ['1月','2月','3月','4月','5月','6月',
			'7月','8月','9月','10月','11月','12月'],
			dayNames: ['日','月','火','水','木','金','土'],
			dayNamesShort: ['日','月','火','水','木','金','土'],
			dayNamesMin: ['日','月','火','水','木','金','土'],		
			dateFormat : 'yy-mm-dd',
			changeYear: true,
	});

	$('#countrySearch').search({
		type: 'category',
		source: categoryContentBasic
	});
});	
	
//在留期限 , 社員寮退去日,社員寮入居日 check
function stayDateDormCheck(obj){
	var str = obj.value;
	if(str!==""){
		if(!str.match(/^(20|21|22|23|24|25|26|27|28|29)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/)){
			alert("正しい日付を入力してください　例）YYYY-MM-DD");
			obj.value = "";
			return false;	
		}
	}
};

//確認メッセージ及び情報 submit
function empChange() {

	if (confirm("変更しますか？") == true) {
		document.getElementById("employees").action="EmployeesChangeEmp"
		document.getElementById('employees').submit();
	} else {
		return false;
	}
}
//社員名 check
function empNameCheck(obj){
	var str = obj.value;
	var id  = obj.id;
	var partnerCompFlg = $('#partnerCompFlg').val();
	//漢字 check
	if(str !== ""){
		if(id=="empName"){
			if(!str.match(/^[\u30a0-\u30ff\u3040-\u309f\u3005-\u3006\u30e0-\u9fcf　]+$/)){
				alert("正しい名前を入力してください");
				obj.value = "";
				return false;	
			}
		}else if(id=="empNameKana"){
			if(!isZenkakuKana(str)){
				alert("正しい名前（全角カナ）を入力してください");
				obj.value = "";
				return false;	
			}
		}else if(id=="empNameEng"){
			if(!isEnglish(str)){
				alert("正しい名前（英字）を入力してください");
				obj.value = "";
				return false;	
			}
		}		
	}
};
function isZenkakuKana(s) {
    return !!s.match(/^[ァ-ヶー　]*$/);
}
function isEnglish(s) {
    return !!s.match(/^[a-zA-Z ]*$/);
}
//電話番号check
function phoneNumberCheck(obj){
	var str = obj.value;
	if(str!==""){
		if(!str.match(/\d{2,3}[-](\d{3}|\d{4})[-](\d{4}$)/g)){
		    alert("正しい電話番号を入力してください 例）000-0000-0000");
		    obj.value = "";
			return false;
		}
	}
};

function backPage() {
	location.href='${pageContext.request.contextPath}/EmployeesListController.do?curPage=1';
}

//郵便番号check 
function empPostCheck(obj) {
	var str = obj.value;

	if (str !== "") {
		if (str.length !== 7) {
			alert("正しい郵便番号を入力してください 例）0000000");
			obj.value = "";
			obj.focus();
			return false;
		}else{
			inputEmpAddr();
			var disp = str.substring(0, 3) + "-" + str.substring(3, 8) 
			$('#empPostNo').val(disp);
		}
	}
}

//住所入力 
function inputEmpAddr(){

 $.ajax({
      url: "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + $('#empPostNo').val(),
      dataType: 'jsonp',
    }).done((data) => {
      if (data.results) { 
        getData(data.results[0]);
      } else {
        console.log('該当データが見つかりません');
        alert("該当データが見つかりません 例）0000000");
        $('#empPost').val("");
      }
    }).fail((data) => {
      console.log('通信に失敗しました');
      $('#empPost').val("");
    });

 function getData(data) {
	   var empAddr =  data.address1 + data.address2;
	   $('#empAddr').val(empAddr);
	  }
 }

function openUploadForm() {
$('#emp_upload_modal').modal('show');
}		 		

//이미지썸네일
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.readAsDataURL(input.files[0]);

		reader.onload = function(e) {

			var tempImage = new Image();
			tempImage.src = reader.result;
			tempImage.onload = function() {
				var canvas = document.createElement('canvas');
				var canvasContext = canvas.getContext("2d");

				var img = new Image();
				img.src = e.target.result;

				canvas.width = img.width * 0.5;
				canvas.height = img.height * 0.5;

				canvasContext.drawImage(this, 0, 0, canvas.width,
						canvas.height);

				var dataURI = canvas.toDataURL("image/png");

				document.querySelector("#thumbnail").src = dataURI;
			}
		};
	}
}

function selectFile() {
	document.getElementById("btn").click();
}


function changeDropdown(id, value) {
	$(id).val(value);
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

.table td, .table th{
	padding: 0.3rem;
	vertical-align: middle;
}
label{ 
	margin: 0px !important;
}
th{
	vertical-align:middle; text-align: right; background:#bee5eb;
}
.prompt{
	border-radius: .25rem !important;
	padding: .375rem .75rem !important;
	border: 1px solid #ced4da !important;
	color: #495057 !important;
}
</style>
</head>
<body>

<div class="content_margin" style="height:500px;">
	<div style="position: absolute; left: 50%; transform:translate(-50%); width: 800px;">
	<h1 ><i class="caret square left icon"></i>社員情報変更</h1>
	<form id="employees" name="employees" action="EmployeesChangeEmp" method="post" enctype="multipart/form-data">
	<table>
		<tr>
		<table class="table-bordered" style="border-bottom: none; border-left: none;">
			<tr>
				<th style="width: 140px;"><label for="empId">社員番号</label></th>
				<td colspan=2><input type="text" class="form-control" name="empId" id="empId" value="${empManage.empId}" readonly="readonly" /></td>
				<td rowspan="5" style="width: 140px; max-width: 140px; text-align: center;"><img id="thumbnail" src="images/${empManage.empFile}" alt="" style="width:140px; max-width: 140px; height: 180px; max-height: 180px;">
				<button type="button" id="photoBtn" class="mini ui blue button"  name="btnName" onclick="selectFile()" >写真登録</button></td>
			</tr>
			<tr>
				<th><label for="hiredDate">入社日</label></th>
				<td colspan="2"><input type="text" class="form-control" name="hiredDate"
					id="hiredDate" value="<fmt:formatDate value="${empManage.hiredDate}" pattern="yyyy-MM-dd"/>" disabled="disabled" /></td>
			</tr>
			<tr>
				<th rowspan="3" style="width: 132px; max-width: 132px;"><label for="empName">社員名</label></th>
				<td colspan="2"><input type="text" class="form-control" name="empName" id="empName"
					value="${empManage.empName}" onblur="empNameCheck(this)" maxlength="50" placeholder="漢字"  disabled="disabled"/></td>
			<tr>
				<td colspan="2"><input type="text" class="form-control" name="empNameKana" id="empNameKana"
					value="${empManage.empNameKana}" onblur="empNameCheck(this)" maxlength="50" placeholder="カナ"  disabled="disabled"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" class="form-control" name="empNameEng" id="empNameEng"
					value="${empManage.empNameEng}" onblur="empNameCheck(this)" maxlength="50" placeholder="英字"  disabled="disabled"/></td>
			</tr>
			<tr>
				<th><label for="empAge">生年月日</label></th>
				<td><input type="text" class="form-control" name="empAge"
					id="empAge" value="${empManage.empAge}"  disabled="disabled" /></td>
				<th><label for="empGender">性別</label></th>
				<td><input type="text" class="form-control"
					name="empGender" id="empGender" value="${displayGender}"
					maxlength="30" disabled /></td>
			</tr>
			<tr>
				<th><label for="empPost">郵便番号</label></th>
				<td colspan=3><input type="text" class="form-control" onblur="empPostCheck(this)" name="empPostNo" id="empPostNo" style="width: 200px" value="${empManage.empPostNo}" maxlength="7" readonly="readonly"/></td>
			</tr>
			<tr>
				<th><label for="empAddr">住所</label></th>
				<td colspan=3><input type="text" class="form-control" name="empAddr" id="empAddr" value="${empManage.empAddr}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th style="background-color:#afffb9;"><label for="phoneNumber">電話番号</label></th>
				<td><input type="text" class="form-control" name="phoneNumber" id="phoneNumber" value="${empManage.phoneNumber}" /></td>
				<th<c:if test='${empManage.partnerCompFlg eq false}'> style="background-color:#afffb9;"</c:if>><label for="emergencyPhoneNumber">緊急連絡先（母国）</label></th>
				<td><input type="text" class="form-control" name="emergencyPhoneNumber" id="emergencyPhoneNumber" value="${empManage.emergencyPhoneNumber}" onchange="phoneNumberCheck(this)" maxlength="15"/>
				<select name="empRelationDd" id="empRelationDd" onchange="changeDropdown('#empRelation', this.value)" class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;">
				                    	<option value="" <c:if test='${selectedEmpRelation eq ""}'>selected</c:if>>該当なし</option>
										<option value="G" <c:if test='${selectedEmpRelation eq "G"}'>selected</c:if>>祖父母</option>
										<option value="P" <c:if test='${selectedEmpRelation eq "P"}'>selected</c:if>>父母</option>
										<option value="S" <c:if test='${selectedEmpRelation eq "S"}'>selected</c:if>>兄弟</option>
										<option value="O" <c:if test='${selectedEmpRelation eq "O"}'>selected</c:if>>以外</option>
				</select></td>
			</tr>
			<tr>
				<th><label for="pst_select">職位</label></th>
				<td>
				<select name="pst_select" id="pst_select" onchange="changeDropdown('#positionCode', this.value)"  class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;color=#eee;" disabled="disabled">
					<c:forEach items="${pstDropBox}" var="list" varStatus="vs">
						<option value="${list.positionCode}"
						<c:if test="${selectedPositionCode eq list.positionCode}">selected</c:if>
						>${list.positionName}</option>
					</c:forEach>
				</select>
				<input type="hidden" id="positionCode" name="positionCode" value="${selectedPositionCode}" />	
				</td>
				<th><label for="pst_select">号俸</label></th>
				<td><input type="text" id="payLevel" name="payLevel" value="${empManage.empPayLevel}"   class="form-control" style="text-align: right !important; width: 72px; float: left;" readonly></td>
			</tr>
			<tr>
				<th><label for="departmentCode">部署</label></th>
				<td>
				<select name="departmentCodeDd" id="departmentCodeDd" onchange="changeDropdown('#departmentCode', this.value)" class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;" disabled="disabled">
					<c:forEach items="${dpDropBox}" var="list" varStatus="vs">
						<option value="${list.departmentCode}"
						<c:if test="${selectedDepartCode eq list.departmentCode}">selected</c:if>
						>${list.departmentName}</option>
					</c:forEach>
				</select>
				<input type="hidden" id="departmentCode" name="departmentCode" value="${selectedDepartCode}" />					
				</td>
				<th><label for="sectionName">課</label></th>
				<td><input type="text" class="form-control" name="empSectionName" id="empSectionName" value="${empManage.empSectionName}"  autocomplete="off"  disabled="disabled"/></td>
			</tr>
			<tr>
				<th style="background-color:#afffb9;"><label for="companyMail">会社メール</label></th>
				<td colspan=3><input type="text" class="form-control" name="companyMail" id="companyMail" value="${empManage.companyMail}" onchange=""  autocomplete="off" /></td>
			</tr>
			<tr>
				<th><label for="spouse">配偶者有無</label></th>
				<td>
					<div id="empSpouseFlgCkb" class="ui emp toggle checkbox">
	 					<input type="checkbox" name="empSpouseFlg" id="empSpouseFlg" value="${empManage.empSpouseFlg}" disabled="disabled" />
					</div>
				</td>
				<th><label for="dependentCount">扶養家族数</label></th>
				<td>
				<select name="dependentCountDd" id="dependentCountDd" onchange="changeDropdown('#dependentCount', this.value)" class="ui search selection dropdown" style="width: 70px; height: 40px !important; padding: 3px !important;"  disabled="disabled">
					<option value="0">無し</option>
					<c:forEach begin="1" end="9" step="1" varStatus="vs">
						<option value="${vs.count}"
						<c:if test="${selectedDependentCount eq vs.count}">selected</c:if>
						>${vs.count}名</option>
					</c:forEach>
				</select>
				<input type="hidden" id="dependentCount" name="dependentCount" value="${selectedDependentCount}" />	
				</td>
			<tr>
				<th><label for="bankBookNumber">口座情報</label></th>
				<td colspan=3>
						<table class="table-bordered" style="border-bottom:none; border-left:none;background-color:#eee;">
						<td>銀行<input type="text"  class="form-control"name="bankName" id="bankName" value="${empManage.bankName}" readonly /></td>
						<td>支店<input type="text" class="form-control"  name="bankBran" id="bankBran" value="${empManage.bankBran}" readonly style="width:120px"/></td>
						<td>区分<input type="text" class="form-control"  name="bankNumGubun" id="bankNumGubun"  value="${empManage.bankNumGubun}" readonly style="width:100px"/></td>
						<td>口座番号<input type="text" class="form-control"  name="bankBookNumber" id="bankBookNumber" value="${empManage.bankBookNumber}" readonly style="width:120px"/></td>
				      </table>
				</td>
			<tr>
				<th><label for="stayExpirationNumber">在留カード番号</label></th>
				<td><input type="text" class="form-control" name="stayExpirationNumber" id="stayExpirationNumber" value="${empManage.stayExpirationNumber}" onchange=""  autocomplete="off"  maxlength="12"  disabled="disabled"/></td>
				<th><label for="stayExpirationDate">在留期限</label></th>
				<td><input type="text" class="form-control" name="stayExpirationDate" id="stayExpirationDate" value="<fmt:formatDate value="${empManage.stayExpirationDate}" pattern="yyyy-MM-dd"/>"  onchange="stayDateDormCheck(this)"  disabled="disabled"/></td>
			</tr>
			<tr>
				<th><label for="myNumber">マイナンバー</label></th>
				<td><input type="text" class="form-control" name="myNumber" id="myNumber" value="${empManage.myNumber}" onchange=""  autocomplete="off" disabled="disabled"/></td>
				<th><label for="pensionNumber">年金番号</label></th>
				<td><input type="text" class="form-control" name="pensionNumber" id="pensionNumber" value="${empManage.pensionNumber}" onchange=""  autocomplete="off" disabled="disabled"/></td>
			</tr>
			<tr>
				<th><label for="nationality">国籍</label></th>
				<td>
					<div class="ui search Country icon field" id="countrySearch">
						<input type="text" class="prompt form-control" name="nationality" id="nationality" value="${empManage.nationality}" onchange=""  autocomplete="off"  disabled="disabled"/>
						<div class="results"></div>
					</div>
				</td>
				<th><label for="residentRegistrationNumber">住民登録番号</label></th>
				<td><input type="text" class="form-control" name="residentRegistrationNumber" id="residentRegistrationNumber" value="${empManage.residentRegistrationNumber}" onchange=""  autocomplete="off" disabled="disabled"/></td>
			</tr>
			<tr>
				<th><label for="dormInDate">社員寮入居日</label></th>
				<td><input type="text" class="form-control" name="dormInDate" id="dormInDate" value="<fmt:formatDate value="${empManage.dormInDate}" pattern="yyyy-MM-dd"/>" disabled="disabled" /></td>
				<th><label for="dormOutDate">社員寮退去日</label></th>
				<td><input type="text" class="form-control" name="dormOutDate" id="dormOutDate" value="<fmt:formatDate value="${empManage.dormOutDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
			</tr>
			<tr>
				<th><label for="healthInsuranceFlg">健康保険社納</label></th>
				<td>
					<div id="healthInsuranceFlgCkb" class="ui emp toggle checkbox">
	 					<input type="checkbox" id="healthInsuranceFlg" name="healthInsuranceFlg" value="${empManage.healthInsuranceFlg}" disabled="disabled"/>
					</div>
				</td>
				<th><label for="empAge">厚生年金社納</label></th>
				<td>
					<div id="pensionFlgCkb" class="ui emp toggle checkbox">
	 					<input type="checkbox" name="pensionFlg" id="pensionFlg" value="${empManage.pensionFlg}" disabled="disabled"/>
					</div>
				</td>
			</tr>
			<tr>
				<th><label for="departmentCode">雇用保険社納</label></th>
				<td colspan=3>
					<div id="empInsuranceFlgCkb" class="ui emp toggle checkbox">
	 					<input type="checkbox" name="empInsuranceFlg" id="empInsuranceFlg" value="${empManage.empInsuranceFlg}" disabled="disabled"/>
					</div>
				</td>
			</tr>
			<tr>
				<th><label>ファイル</label></th>
				<td colspan=3><button type="button" id="btnUp" class="tiny ui blue button"  name="btnName" onclick="openUploadForm()">ファイル</button></td>
				<div colspan=3></div>
			</tr>
		</table>
	</tr>

	<tr>
		<table>
			<input type="file" class="btn btn-primary" id="btn" name="btn" onchange="readURL(this)" hidden/>
			<input type="hidden" id="fileName" name="fileName"  value="${empManage.empFile}"/>
			<input type="hidden" id="historyNo" name="historyNo"　class="form-control" value="${empManage.historyNo}" />
     	 	<input type="hidden" id="fileUrl" name="fileUrl" value="${empManage.empUrl}"/> 
			<input type="hidden" id="empIdx" name="empIdx" value="${empManage.empIdx}" />	
		<!-- button box -->
		<div class="ui">
		<td style="width:640px">
			<div class="ui button left floated" onclick="backPage()"><i class="left chevron icon"></i>戻る</div>
		</td>	
		<td>
			<div class="ui blue button right floated" onClick="return empChange()">更新</div>
		</div>
		</td>
		</table>
		<div class="ui custom popup top left transition hidden" id="fileListArea"></div>
	</tr>
	</table>
	</form>	
	</div>
</div>

</body>
</html>

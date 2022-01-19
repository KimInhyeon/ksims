<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<title>受注情報登録</title>
<script>
	function obtainOrdeInsAndUp() {
		location.href = '${pageContext.request.contextPath}/obtainOrdeConfirm';

	}

	//dblClick 방지
	var doubleSubmitFlag = false;
	function doubleSubmitCheck() {
		if (doubleSubmitFlag) {
			return doubleSubmitFlag;
		} else {
			doubleSubmitFlag = true;
			return false;
		}
	}


$(document).on("click","button[name=addBtn1]",function(){
	alert("追加");
    var addStaffText =     '<tr name="trStaff">'+
        '    <td style="width: 150px"><input type="text"class="form-control hasDatepicker" name="pers_num" id="pers_num" autocomplete="off" value="50001"></td>'+

        '    <td style="width: 20%"><input type="text" class="form-control hasDatepicker" name="pers_name" id="pers_name" autocomplete="off" value="中村"></td>'+
        
        '    <td align="center" style="width: 90px"><select name="uprice_classify" id="uprice_classify" class="ui search selection dropdown" style="width: 70px; height: 30px; padding: 3px !important;">'+
        '    <option value="01" selected="">月額単価</option><option value="02">単価1</option> <option value="03">単価2</option> <option value="04">単価3</option> </select></td>'+

        '    <td style="width: 100px; text-align: right">'+
        '    <input type="text" class="form-control hasDatepicker" name="uprice_amount" id="uprice_amount" autocomplete="off" value="600,000">'+
        
        '    <td align="center"><div style="float: left;">'+
        '    <input type="text" class="form-control" size="3" name="start_standard_work_time" id="start_standard_work_time" maxlength="3" value="140" style="width: 80px;"></div>'+
        '    <div style="float: left;">&nbsp;~&nbsp;</div><div style="float: left;">'+
        '    <input type="text" class="form-control" size="3"name="end_standard_work_time" id="end_standard_work_time" maxlength="3" value="180" style="width: 80px;"></div></td>'+
        
        '        <td align="center"><div class="ui right labeled input">'+
        '        <input type="text" class="form-control hasDatepicker" name="excess_uprice" id="excess_uprice" autocomplete="off" value="3,450" style="width: 100px;"></div></td>'+
        
        '        <td align="center"><div class="ui input">'+
        '        <input type="text" class="form-control hasDatepicker" name="deduction_uprice" id="deduction_uprice" autocomplete="off" value="4,550" style="width: 100px;"></div></td></tr>';
        
    var trHtml = $( "tr[name=trStaff]:last" );
    
    trHtml.after(addStaffText);
    
    $(document).on("click","button[name=delStaff]",function(){
        alert("1q2w3e");
        var trHtml = $(this).parent().parent();
        
        trHtml.remove(); //tr 테그 삭제
        
    });
    
});


function deleteRow(rownum) {
	alert("削除");	 
	  const table = document.getElementById('addShow');

	  const newRow = table.deleteRow(rownum);
	}

</script>
<style>
h1 {
	font-family: "Arial", "メイリオ";
	position: relative;
	color: #6495ed;
	line-height: 1.4;
	-webkit-box-reflect: below -20px
		-webkit-linear-gradient(top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0) 10%,
		rgba(0, 0, 0, 0.6));
}

.table thead th {
	vertical-align: middle;
}

input {
	text-align: center;
}

.scrolltbody {
    display: block;     
    width: 1090px;  
/*     border-collapse: collapse;  */
/*     border: 2px solid #000; */
}


.scrolltbody tbody {
    display: block;  
    height: 480px; 
    overflow: auto;
}

</style>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" id="" name=""
			action="${pageContext.request.contextPath}/obtainOrdeConfirm"
			method="POST">
			<h1>
				<i class="caret square left icon"></i>受注情報登録
			</h1>
			<br />

			<div class="ui grid"">
				<div style="background-color: white;">
					<table class="table table-hover table-sm" style="border-top: none;">
						<tbody>
							<tr>
								<th class="table-info"><label for="field_name"><font
										color="red">＊</font>案件番号</label></th>
								<td><div style="float: left;">
										<select name="pject_num" id="pject_num"
											class="ui search selection dropdown"
											style="height: 40px !important; padding: 3px !important;">
											<option value="T21001" selected="">T21001</option>

											<option value="T21002">T21002</option>
											<option value="T21003">T21003</option>
											<option value="T21004">T21004</option>
											<option value="T21005">T21005</option>
											<option value="T21006">T21006</option>

										</select>
									</div></td>
							</tr>
							<tr>
								<th class="table-info"><font color="red">＊</font>見積番号</th>
								<td>
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											name="estimate_num" id="reg_date" maxlength="10"
											autocomplete="off" value="">
									</div>
								</td>
							</tr>
							<tr>
								<th class="table-info"><font color="red">＊</font>見積番号</th>
								<td>
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											name="registration_date" id="reg_date" maxlength="15"
											autocomplete="off" value="">
									</div>
								</td>
							<tr style="border: none;">
								<td colspan="4" style="border: none" align="right"><input
									type="button" class="btn btn-warning" value="検索" onclick="">&nbsp;&nbsp;</td>
							</tr>
						</tbody>
						<tr>
						
							<table class="table table-striped table-hover table-bordered table-sm scrolltbody">
 								<thead class="table-info" >
								
									<tr align="center" class="head">
										<th width="13%"><font color="red">＊</font>要員番号</th>
										<th width="18%">要員氏名</th>
										<th width="13%"><font color="red">＊</font>単価区分</th>
										<th width="18%"><font color="red">＊</font>単価金額</th>
										<th width="23%">基準作業時間(h)</th>
										<th width="18%">超過単価(円)</th>
										<th width="18%">控除単価(円)</th>
									</tr>
								</thead> 
								<tbody id="addShow" >

									<tr id="100022021090004">
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="pers_num"
											id="pers_num" autocomplete="off" value=""></td>

										<td style="width: 20%"><input type="text"
											class="form-control hasDatepicker" name="pers_name"
											id="pers_name" autocomplete="off" value=""></td>

										<td align="center" style="width: 90px"><select
											name="uprice_classify" id="uprice_classify"
											class="ui search selection dropdown"
											style="width: 70px; height: 30px; padding: 3px !important;">
												<option value="01" selected="">月額単価</option>
												<option value="02">単価1</option>
												<option value="03">単価2</option>
												<option value="04">単価3</option>
										</select></td>

										<td style="width: 100px; text-align: right"><input
											type="text" class="form-control hasDatepicker"
											name="uprice_amount" id="uprice_amount" autocomplete="off"
											value=""></td>

										<td align="center">
											<div style="float: left;">
												<input type="text" class="form-control" size="3"
													name="start_standard_work_time"
													id="start_standard_work_time" maxlength="3" value=""
													style="width: 80px;">
											</div>
											<div style="float: left;">&nbsp;~&nbsp;</div>
											<div style="float: left;">
												<input type="text" class="form-control" size="3"
													name="end_standard_work_time" id="end_standard_work_time"
													maxlength="3" value="" style="width: 80px;">
											</div>
										</td>

										<td align="center">
											<div class="ui right labeled input">
												<input type="text" class="form-control hasDatepicker"
													name="excess_uprice" id="excess_uprice" autocomplete="off"
													value="" style="width: 100px;">
											</div>
										</td>

										<td align="center">
											<div class="ui input">
												<input type="text" class="form-control hasDatepicker"
													name="deduction_uprice" id="deduction_uprice"
													autocomplete="off" value="" style="width: 100px;">
											</div>
										</td>
									</tr>

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="50003"></td>

										<td style="width: 20%"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="中村"></td>

										<td align="center" style="width: 90px"><select
											　name="field_difficulty" id="field_difficulty"
											class="ui search selection dropdown"
											style="width: 70px; height: 30px; padding: 3px !important;">
												<option value="-1">月額単価</option>
												<option value="上">単価1</option>
												<option value="中">単価2</option>
												<option value="下">単価3</option>
										</select></td>
										<td style="width: 100px; text-align: right"><input
											type="text" class="form-control hasDatepicker"
											name="reg_date" id="reg_date" autocomplete="off"
											value="600,000"></td>
										<td align="center">
											<div style="float: left;">
												<input type="text" class="form-control" size="3"
													name="field_code" id="field_code" maxlength="10"
													value="140" style="width: 80px;">
											</div>
											<div style="float: left;">&nbsp;~&nbsp;</div>
											<div style="float: left;">
												<input type="text" class="form-control" size="3"
													name="field_code" id="field_code" maxlength="110"
													value="180" style="width: 80px;">
											</div>
										</td>
										<td align="center">
											<div class="ui right labeled input">
												<input type="text" class="form-control hasDatepicker"
													name="reg_date" id="reg_date" autocomplete="off"
													value="3,450" style="width: 100px;">
											</div>
										</td>
										<td align="center">
											<div class="ui input">
												<input type="text" class="form-control hasDatepicker"
													name="reg_date" id="reg_date" autocomplete="off"
													value="4,550" style="width: 100px;">
											</div>
										</td>
									</tr>


									
									<tr name="trStaff"></tr>
								</tbody>
							</table>
							<div style="float: right;">
								<input type="submit" class="btn btn-warning" value="登録・変更"
									onclick="obtainOrdeInsAndUp()">&nbsp;&nbsp; <input
									type="button" class="btn btn-secondary" value="戻る " onclick="">
							</div>

						</tr>
						
					</table>
				</div>

			</div>
			<br />
		</form>
<!-- 		<input type="button" class="ui gray button"  id="addBtn1"name="addBtn1" value="+"> -->
		<button class="ui gray button" name="addBtn1">+1</button>
		<input type='button' class="ui gray button" value='-1' onclick='deleteRow(-1)' />
<!-- 		<button class="ui gray button" name="delStaff">-1</button> -->

	</div>
</body>
</html>
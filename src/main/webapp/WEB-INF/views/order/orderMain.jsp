<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<title>評価承認</title>
<script>
	function client_info() {
		location.href = '${pageContext.request.contextPath}/clientInfo';
		
	}

	function client_manager() {
		location.href = '${pageContext.request.contextPath}/clientManager';
		
	}
	function obtain_orde() {
		location.href = '${pageContext.request.contextPath}/obtainOrde';
		
	}
	
	
	function toggleChecker() {
		const chks = document.getElementsByName("confirmCheck");
		if (mst_chk.checked) {
			for (let i = 0; i < chks.length; i++) {
				chks[i].checked = true;
			}
		} else {
			for (let i = 0; i < chks.length; i++) {
				chks[i].checked = false;
			}
		}

	}

	function onConfirm() {
		if (confirm('評価を承認します。')) {
			const chks = document.getElementsByName("confirmCheck");
			var cnt = 0;
			for (let i = 0; i < chks.length; i++) {
				if (chks[i].checked) {
					$('.disis' + chks[i].value).removeAttr('disabled');
					cnt++;
				}
			}
			if (cnt == 0) {
				alert('承認したい社員を一人以上チェックしてください。');
				return;
			}
			if (doubleSubmitCheck()) {
				return;
			}
			appraisal.submit();
			$('#confirm_btn').css('pointer-events', 'none');
		}
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
</style>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" id="" name=""
			action="${pageContext.request.contextPath}/OrderInsert"
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
										color="red">＊</font>受発注区分</label></th>
								<td><div style="float: left;"><select name="addr_select" id="addr_select"
									onchange="SelectChange()" class="ui search selection dropdown"
									style="height: 40px !important; padding: 3px !important;">
										<option value="" selected="">受注</option>

										<option value="1">受注1</option>
										<option value="2">受注2</option>
										<option value="3">受注3</option>
										<option value="4">受注4</option>
										<option value="5">受注5</option>
										<option value="6">受注6</option>

								</select></div></td>
							</tr>
							<tr>
								<th class="table-info"><font color="red">＊</font>会社</th>
								<td>
									<div style="float: left;">
										<select name="addr_select" id="addr_select"
											onchange="SelectChange()"
											class="ui search selection dropdown"
											style="height: 40px !important; padding: 3px !important;">
											<option value="" selected="">00011</option>

											<option value="1">番号1</option>
											<option value="2">番号2</option>
											<option value="3">番号3</option>
											<option value="4">番号4</option>
											<option value="5">番号5</option>
											<option value="6">番号6</option>
											<option value="7">番号7</option>
											<option value="8">番号8</option>
											<option value="9">番号9</option>
											<option value="10">番号10</option>
											<option value="99">その他</option>

										</select>
									</div><div style="float: left;">&nbsp;&nbsp;</div>
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											style="width: 150px;" name="reg_date" id="reg_date"
											maxlength="10" autocomplete="off" value="IKI">
									</div>
								</td>
							</tr>
							<tr>
								<th class="table-info"><font color="red">＊</font>PJコード</th>
								<td><div style="float: left;">
										<select name="addr_select" id="addr_select"
											onchange="SelectChange()"
											class="ui search selection dropdown"
											style="height: 40px !important; padding: 3px !important;">
											<option value="" selected="">00151</option>

											<option value="1">番号1</option>
											<option value="2">番号2</option>
											<option value="3">番号3</option>
											<option value="4">番号4</option>
											<option value="5">番号5</option>
											<option value="6">番号6</option>
											<option value="7">番号7</option>
											<option value="8">番号8</option>
											<option value="9">番号9</option>
											<option value="10">番号10</option>
											<option value="99">その他</option>

										</select>
									</div><div style="float: left;">&nbsp;&nbsp;</div>
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											style="width: 150px;" name="reg_date" id="reg_date"
											maxlength="10" autocomplete="off" value="マルス1次開発">
									</div></td>
							</tr>
							<tr>
								<th class="table-info"><font color="red">＊</font>契約形態</th>
								<td><div style="float: left;"><select name="addr_select" id="addr_select"
									onchange="SelectChange()" class="ui search selection dropdown"
									style="height: 40px !important; padding: 3px !important;">
										<option value="" selected="">一括請負</option>

										<option value="1">test1</option>
										<option value="2">test2</option>
										<option value="3">test3</option>
										<option value="4">test4</option>
										<option value="5">test5</option>
										<option value="6">test6</option>
										<option value="7">test7</option>
										<option value="8">test8</option>
										<option value="9">test9</option>
										<option value="10">test10</option>
										<option value="99">その他</option>

								</select></div></td>

								<th class="table-info"><font color="red">＊</font>支払い条件</th>
								<td><div style="float: left;"><select name="field_difficulty" id="field_difficulty"
									class="ui search selection dropdown"
									style="width: 70px; height: 30px; padding: 3px !important;">
										<option value="-1">上記以外</option>
										<option value="上">上</option>
										<option value="中">中</option>
										<option value="下">下</option>
								</select></div><div style="float: left;">&nbsp;&nbsp;</div>
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											style="width: 150px;" name="reg_date" id="reg_date"
											maxlength="10" autocomplete="off" value="2021/8/31">
									</div></td>
							</tr>
							<tr>
								<th class="table-info"><label for="field_code"><font
										color="red">＊</font>作業期間</label></th>
								<td>
									<div style="float: left;">
										<input type="text" class="form-control" size="2"
											name="field_code" id="field_code" maxlength="10" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
									<div style="float: left;">&nbsp;~&nbsp;</div>
									<div style="float: left;">
										<input type="text" class="form-control" size="2"
											name="field_code" id="field_code" maxlength="110" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
								</td>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>登録年月</label></th>
								<td><input type="text" class="form-control hasDatepicker"
									name="reg_date" id="reg_date" maxlength="10" autocomplete="off"></td>
							</tr>
							<tr style="border: none;">
								<td colspan="4" style="border: none" align="right"><input
									type="button" class="btn btn-warning" value="検索" onclick="">&nbsp;&nbsp;</td>
							</tr>
						</tbody>
						<tr>










							<table class="table table-striped table-hover table-bordered table-sm">
								<thead class="table-info" align="center">
									<tr align="center">
										<th width="10%"><font color="red">＊</font>注文番号11</th>
										<th width="15%">要員氏名</th>
										<th width="10%"><font color="red">＊</font>単価区分</th>
										<th width="15%"><font color="red">＊</font>単価金額</th>
										<th width="20%">基準作業時間(h)</th>
										<th width="15%">超過単価(円)</th>
										<th width="15%">控除単価(円)</th>
									</tr>
								</thead>
								<tbody>
									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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

									<tr id="100022021090004">
										<!-- setting0-->
										<td style="width: 150px"><input type="text"
											class="form-control hasDatepicker" name="reg_date"
											id="reg_date" autocomplete="off" value="10044"></td>

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
								</tbody>
							</table>
							<div style="float: right;">
								<input type="button" class="btn btn-warning" value="登録・変更"
									onclick="locationURL()">&nbsp;&nbsp; <input
									type="button" class="btn btn-secondary" value="戻る " onclick="">
							</div>


						</tr>
					</table>
				</div>

			</div>
			<br />
			<!-- 			<div class="ui"> -->
			<!-- 				<div class="ui button left floated" onclick=""> -->
			<!-- 					<i class="left chevron icon"></i>戻る -->
			<!-- 				</div> -->
			<!-- 				<div class="ui blue button right floated" onClick="" -->
			<!-- 					id="confirm_btn"> -->
			<!-- 					承認<i class="right chevron icon"></i> -->
			<!-- 				</div> -->
			<!-- 			</div> -->
			<div class="ui blue button" onclick="client_info()" >取引先情報</div>
			<div class="ui blue button" onclick="client_manager()" >取引先担当者</div>
			<div class="ui blue button" onclick="" >プロジェクト情報</div>			
			<div class="ui blue button" onclick="obtain_orde()" >受注情報登録</div>
		</form>

	</div>
</body>
</html>
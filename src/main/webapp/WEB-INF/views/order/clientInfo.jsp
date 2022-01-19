<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<title>取引先情報</title>
<style>
.order_center {
	text-align: center;
	padding: 40px 20px;
}

.order_border {
	border: 1px solid gray;
}
</style>

<script>
	function clientInfoConfirm() {
		location.href = '${pageContext.request.contextPath}/clientInfoConfirm';
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

	function client_info_confirm() {

		alert("com_code --> " + document.getElementById("com_code").value);
		location.href = '${pageContext.request.contextPath}/clientInfoConfirm';

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

</style>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" id="" name=""
			action="${pageContext.request.contextPath}/clientInfoConfirm"
			method="POST">
			<h1>
				<i class="caret square left icon"></i>取引先情報
			</h1>
			<br />

			<div class="ui grid">
				<div style="background-color: white;">
					<table class="table table-hover table-sm" style="border-top: none;">
						<tbody>

							<tr>
								<th class="table-info"><font color="red">＊</font>会社コード</th>
								<td style="width: 350px;">
									<div style="float: left;">
										<input type="text" class="form-control hasDatepicker"
											style="width: 196px;" name="com_code" id="com_code"
											maxlength="3" autocomplete="off" value="32">
									</div>
								</td>

								<th class="table-info">業種</th>
								<td style="width: 350px;"><div style="float: left;">
										<select name="busi_type" id="busi_type"
											class="ui search selection dropdown"
											style="width: 70px; height: 30px; padding: 3px !important;">
											<option value="01">業種1</option>
											<option value="02">業種2</option>
											<option value="03">業種3</option>
											<option value="04">業種4</option>
										</select>
									</div>
									<div style="float: left;">&nbsp;&nbsp;</div></td>
							</tr>

							<tr>
								<th class="table-info">国籍</th>
								<td><div style="float: left;">
										<select name="com_nationality" id="com_nationality"
											class="ui search selection dropdown"
											style="width: 70px; height: 30px; padding: 3px !important;">
											<option value="001">日本</option>
											<option value="002">アメリカ</option>
											<option value="003">カンコク(韓国)</option>
											<option value="004">スウェーデン</option>
											<option value="005">中国</option>
											<option value="006">ハンガリー</option>
											<option value="007">マレーシア</option>
											<option value="008">ネパール</option>
										</select>
									</div></td>

								<th class="table-info">会社形態</th>
								<td><div style="float: left;">
										<select name="com_form" id="com_form"
											class="ui search selection dropdown"
											style="width: 70px; height: 30px; padding: 3px !important;">
											<option value="01">株式会社</option>
											<option value="02">派遣会社</option>
											<option value="03">契約会社</option>
											<option value="04">我社</option>
											<option value="05">弊社</option>
										</select>
									</div></td>

							</tr>

							<tr>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>名称（漢字）</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker"
									name="com_name" id="com_name" maxlength="30" autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>名称（カナ）</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker" 
									name="com_kana_name" id="com_kana_name" maxlength="30" autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date">名称（英字）</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker" 
									name="com_name_eng" id="com_name_eng" maxlength="30" autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="field_code"><font
										color="red">＊</font>郵便番号</label></th>
								<td colspan="4">
									<div style="float: left;">
										<input type="text" class="form-control" size="2"
											name="com_pos_code1" id="com_pos_code1" maxlength="3" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
									<div style="float: left;">&nbsp;―&nbsp;</div>
									<div style="float: left;">
										<input type="text" class="form-control" size="2"
											name="com_pos_code2" id="com_pos_code2" maxlength="4" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
								</td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>住所1</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker"
									name="com_address1" id="com_address1" maxlength="100"
									autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>住所2</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker"
									name="com_address2" id="com_address2" maxlength="100"
									autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date">URL</label></th>
								<td colspan="4"><input type="text" class="form-control hasDatepicker"
									name="com_URL" id="com_URL" maxlength="100"
									autocomplete="off"></td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date">基本契約情報</label></th>
								<td class="table-info"></td>
								<th class="table-info"></th>
								<td class="table-info"></td>
							</tr>

							<tr class="order_border">
								<td><div class="order_center">
										<label for="reg_date"><font color="red">＊</font>受注</label>
									</div></td>

								<td>
									<div>
										契約年月日 <input type="text" class="form-control hasDatepicker"
											name="obtorde_cont_date" id="obtorde_cont_date" maxlength="8"
											autocomplete="off"> 契約書番号 <input type="text"
											class="form-control hasDatepicker" name="obtorde_cont_num"
											id="obtorde_cont_num" maxlength="20" autocomplete="off">
									</div>
								</td>

								<td><div class="order_center">
										<label for="reg_date"><font color="red">＊</font>発注</label>
									</div></td>

								<td>
									<div>
										契約年月日 <input type="text" class="form-control hasDatepicker"
											name="order_cont_date" id="order_cont_date" maxlength="8"
											autocomplete="off"> 契約書番号 <input type="text"
											class="form-control hasDatepicker" name="order_cont_num"
											id="order_cont_num" maxlength="20" autocomplete="off">
									</div>
								</td>
							</tr>

							<tr>
								<th class="table-info"><label for="reg_date"><font
										color="red">＊</font>振込先</label></th>
								<td class="table-info"></td>
								<th class="table-info"></th>
								<td class="table-info"></td>
							</tr>

							<tr>
								<td><div class="order_center">
										<label for="reg_date"><font color="red">＊</font>口座</label>
									</div></td>

								<td>
									<div style="float: left;">
										銀行123 <input type="text" class="form-control" size="2"
											name="acc_info_bank" id="acc_info_bank" maxlength="3" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
									<div style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
									<div>
										支店 <input type="text" class="form-control" size="2"
											name="acc_info_branch" id="acc_info_branch" maxlength="5" value=""
											onkeyup="moveFocus(this)" style="width: 110px;">
									</div>
									<div style="float:">
										区分
										<div>
											<select name="acc_info_classify" id="acc_info_classify"
												class="ui search selection dropdown"
												style="width: 70px; height: 30px; padding: 3px !important;">
												<option value="1">当座</option>
												<option value="2">普通1</option>
												<option value="3">普通2</option>
												<option value="4">普通3</option>
												<option value="5">普通4</option>
											</select>
										</div>
										<div style="float:;">
											口座名義 <input type="text" class="form-control" size="2"
												name="acc_name" id="acc_name" maxlength="30" value=""
												onkeyup="moveFocus(this)" style="width: 110px;">
										</div>

									</div>
								</td>

							</tr>


							<tr style="border: none;">
								<td colspan="4" style="border: none" align="right">
									<div style="float: right;">
										<input type="submit" class="btn btn-warning" value="登録・変更"
											onclick="clientInfoConfirm()">&nbsp;&nbsp; <input
											type="button" class="btn btn-secondary" value="戻る "
											onclick="history.back()">
									</div>
							</tr>
						</tbody>
						<tr>

						</tr>

					</table>

				</div>

			</div>
			<br />
		</form>

	</div>
</body>
</html>
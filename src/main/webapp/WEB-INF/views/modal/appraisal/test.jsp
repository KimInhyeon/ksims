<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
		<div class="ui form">

			<!-- 新規フォルダー作成　モーダル -->
			<div class="modal fade" id="test" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">フォルダー名変更</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div id="msgArea" align="left">${exceptionModal.message}</div>
							<br />
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button class="btn btn-outline-secondary" type="button" id="button-addon1">フォルダー名</button>
								</div>
								<input type="hidden" id="org_number" name="org_number" value="${org_number}" />
								<input type="hidden" id="current_level" name="current_level" value="${curLevel}" />
								<input type="hidden" id="current_url" name="current_url" value="${txt_folderPath}" />
								<input type="text" class="form-control" id="new_item_name" name="new_item_name" value="${new_item_name}" aria-describedby="button-addon1" maxlength="100" onkeydown="renameWithEnter();"/>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="renameFolderBtn" class="btn btn-primary mr-auto"
								onclick="doubleSubmitCheckFolderRename()">確認</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">キャンセル</button>
						</div>
					</div>
				</div>
			</div>
		</div>

</body>
</html>
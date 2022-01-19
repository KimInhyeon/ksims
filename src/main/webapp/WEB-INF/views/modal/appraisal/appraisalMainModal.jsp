<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
	.modal-body .table tr td{height:40px; padding:0; vertical-align: middle;}
	.modal-body .table tr td .btn{padding:1px 0; margin:0; font-size: 15px; width:80%; margin:0 5%;}
	.modal-body .table tr td .badge{line-height: 1.5;}
</style>
<script>
function fieldCheck(field_code,field_name){
		var obj = new Object();
		obj.field_code = field_code;
		obj.field_name = field_name;
		document.getElementById('field_code').value=field_code;
		
		$.ajax({
			url: "AppraisalModalController",
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(obj),
			success: function(data){ 
				var auth='${sessionScope.adminFlg}';
				var m_cont='';
				
				if(auth=='01'){
					m_cont+='<table class="table">';
					for(var n=0;n<data.employeeCnt;n++){
						m_cont+='<tr id="tr_'+n+'" class="border-bottom">';
						m_cont+='<td style="width:30%; padding:0; vertical-align:middle;"><h3 style="margin:auto;">'+data.employee_List[n].emp_name+'&nbsp;&#40;'+data.employee_List[n].emp_id+'&#41;</h3></td>';
						m_cont+='<td>'
								+'<button type="button"';
						if(data.employee_List[n].self_app_flg){
							if(data.employee_List[n].accept_flg){
								m_cont+=' class="btn btn-success" '
							}else {
								m_cont+=' class="btn btn-primary" ';
								}
							}
						else{
							m_cont+=' class="btn btn-warning" ';
							}
						m_cont+='onclick="admin_self_appraisal('+data.employee_List[n].emp_id+')">自己評価</button>'
								+'</td>';
								
						m_cont+='<td>'
								+'<button type="button"';
						if(data.employee_List[n].leader_app_flg){
							if(data.employee_List[n].accept_flg){
								m_cont+=' class="btn btn-success" '
							}else {
								m_cont+=' class="btn btn-primary" ';
								}
							}
						else{
							m_cont+=' class="btn btn-warning" ';
							}
						m_cont+='onclick="leader_appraisal('+data.employee_List[n].emp_id+')">リーダー評価</button>'
								+'</td>';
								
						m_cont+='<td>'
								+'<button type="button"';
						if(data.employee_List[n].customer_app_flg){
							if(data.employee_List[n].accept_flg){
								m_cont+=' class="btn btn-success" '
							}else {
								m_cont+=' class="btn btn-primary" ';
								}
							}
						else{
							m_cont+=' class="btn btn-warning" ';
							}
						m_cont+='onclick="customer_appraisal('+data.employee_List[n].emp_id+')">顧客評価</button>'
								+'</td>';
								
						m_cont+='<td>'
								+'<button type="button"';
						if(data.employee_List[n].perfomance_app_flg){
							if(data.employee_List[n].accept_flg){
								m_cont+=' class="btn btn-success" '
							}else {
								m_cont+=' class="btn btn-primary" ';
								}
							}
						else{
							m_cont+=' class="btn btn-warning" ';
							}
						m_cont+='onclick="perfomance_appraisal('+data.employee_List[n].emp_id+')">実績評価</button>'
								+'</td>';
						m_cont+='</tr>';
						
					}
					m_cont+='</table>';
					$('.modal-body').html(m_cont);

					
				}else {
					m_cont+='<table class="table">';
						m_cont+='<tr class="border-bottom">';
						m_cont+='<td style="padding:0; vertical-align:middle;"><h3>'
								+data.employee_List[0].emp_name+'&nbsp;&#40;'+data.employee_List[0].emp_id+'&#41'
								+'</h3></td>';
						m_cont+='<td>';
						m_cont+='<button type="button"';
						if(data.employee_List[0].self_app_flg){
							if(data.employee_List[0].accept_flg){
								m_cont+=' class="btn btn-success" '
							}else {
								m_cont+=' class="btn btn-primary" ';
								}
							}
						else{
							m_cont+=' class="btn btn-warning" ';
							}
								m_cont+='onclick="self_appraisal()">自己評価</button>' //自己評価ページ
								+'</td>';
						m_cont+='</tr>';
					if(data.leader_code=='02'){
						for(var n=1;n<data.employeeCnt;n++){
							m_cont+='<tr class="border-bottom">';
							m_cont+='<td style="padding:0; vertical-align:middle;"><h3>'
									+data.employee_List[n].emp_name+'&nbsp;&#40;'+data.employee_List[n].emp_id+'&#41'
									+'</h3></td>';
							m_cont+='<td>';	
							if(data.employee_List[n].leader_code=='03'){
								m_cont+='<button type="button" id="leader_leader_btn"';
								if(data.employee_List[n].leader_app_flg){
									if(data.employee_List[n].accept_flg){
										m_cont+=' class="btn btn-success" '
									}else {
										m_cont+=' class="btn btn-primary" ';
										}
									}
								else{
									m_cont+=' class="btn btn-warning" ';
									}
								m_cont+='onclick="leader_appraisal('+data.employee_List[n].emp_id+')"'
										+'">リーダー評価</button>';
							}else{
								m_cont+='<span class="badge bg-light" style="font-size: 14px; font-weight: normal;">評価対象外</span>';
							}
							m_cont+='</td>';
							m_cont+='</tr>';
						}
					}	
					
					m_cont+='</table>';
					$('.modal-body').html(m_cont);	
					$('.modal-content').css('width','70%');
					$('.modal-content').css('margin-left','10%');
					$('.modal-content').css('margin-bottom','5%');
				}
				
				$('#exampleModalLongTitle').html(data.field_name+' 評価対象リスト');
				$('#admin_appraisal_modal').modal('show');
				$('#admin_appraisal_modal').modal({backdrop: 'static'});
			},
		});
		
	}
	
    function closeModal() {
	    $('#admin_appraisal_modal').modal('toggle');
    }
	
	
	//遷移
	function self_appraisal(){
		modal_form.action='${pageContext.request.contextPath}/AppraisalSelfController';
		modal_form.submit();
	}
	function admin_self_appraisal(emp_id){
		document.getElementById('emp_id').value=emp_id;
		modal_form.action='${pageContext.request.contextPath}/AppraisalSelfController';
		modal_form.submit();
	}
	function leader_appraisal(emp_id){
		document.getElementById('emp_id').value=emp_id;
		modal_form.action='${pageContext.request.contextPath}/AppraisalLeaderController';
		modal_form.submit();
	}
	function customer_appraisal(emp_id){
		document.getElementById('emp_id').value=emp_id;
		modal_form.action='${pageContext.request.contextPath}/AppraisalPerformanceController';
		modal_form.submit();
	}
	function perfomance_appraisal(emp_id){
		document.getElementById('emp_id').value=emp_id;
		modal_form.action='${pageContext.request.contextPath}/AppraisalPerformanceController';
		modal_form.submit();
	}
	
</script>
<meta charset="UTF-8">
<title>KS情報システム</title>

</head>
<body>
			<div class="modal" id="admin_appraisal_modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered modal-lg">
					<div class="modal-content">
						<div class="modal-header" style="vertical-align: middle;">
							<h5 class="modal-title" id="exampleModalLongTitle" style="font-size: :14px;">評価対象リスト</h5>
							<h3 style="margin:0;padding:0; float:left; width:50%;">
								<span class="badge bg-warning" style="font-size:14px; font-weight: normal; padding:3px;">未作成</span>&nbsp;▶&nbsp;
								<span class="badge bg-primary" style="font-size:14px; color:#fff; font-weight: normal;">作成完了</span>&nbsp;▶&nbsp;
								<span class="badge bg-success" style="font-size:14px; color:#fff; font-weight: normal;">承認完了</span>
							</h3>
							<form name="modal_form" method="post">
							<input type="hidden" id="field_code" name="field_code">
							<input type="hidden" id="emp_id" name="emp_id">
							</form>
							<button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onclick="closeModal()">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						</div>
						<div class="modal-footer">
							
						</div>
					</div>
				</div>
			</div>
</body>
</html>
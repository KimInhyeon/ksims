<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<script type="text/javascript">
		function formBack(){ //뒤로가기
		    history.back();
	}
	function beforesubmit(){
		
		
			 if(document.getElementById('cust1').value=='0'||document.getElementById('cust3').value=='0'){
				 if(!confirm('顧客評価に0点があります。　続きますか？')){
					 return;
				 }
			 }
			 if(document.getElementById('cust2').value==''||document.getElementById('cust4').value==''){
				 if(!confirm('顧客評価が未完成です。　続きますか？')){
					 return;
				 }
			 }
		 
		
			 if(document.getElementById('perform1').value=='0'||document.getElementById('perform3').value=='0'){
				 if(!confirm('実績評価に0点があります。　続きますか？')){
					 return;
				 }
			 }
			 if(document.getElementById('perform2').value==''||document.getElementById('perform4').value==''){
				 if(!confirm('実績評価が未完成です。　続きますか？')){
					 return;
				 }
			 }
		$('#sb_btn').css('pointer-events','none');
		$('.disis').removeAttr('disabled');
		cusomer_perfomance_appr.action='${pageContext.request.contextPath}/Appraisal_performance';
		cusomer_perfomance_appr.submit();
	}
	function score_check(idname){
		var activeID=document.activeElement.id;
    	var activeVal=document.getElementById(activeID).value;
    	if(!/^[0-9]{1,3}$/.test(activeVal)){
    		document.getElementById(activeID).value=0;
    	}else if(Number(activeVal)>10){
    		document.getElementById(activeID).value=10;
    	}
		
		var sum=Number(document.getElementById(idname+'1').value)*6+Number(document.getElementById(idname+'3').value)*4;
		document.getElementById(idname+'5').value=sum;
	}
</script>
<style>
	.table tr td{padding:1px auto; text-align:center; vertical-align: middle;}
	.table-secondary{background:#e9ecef;}
	.profiletable th {width:20%; padding:1px auto;}
	.profiletable tr td{vertical-align: middle; padding:1px 0px 1px 10px;}
	.table input{padding:0 3px; margin:0;}
	.table tr td textarea{padding:4px 7px;}
	.content{text-align: center; border:0;}
	.canWrite{background:#afffb9; border:3px solid #afffb9;}
</style> 
</head>
<body>
<form name="cusomer_perfomance_appr"  method="post">
	<table class="table table-striped table-bordered profiletable" style="width:70%; margin:3px auto;">
            <tr>
            	<th class="table-info">
            		プロジェクト名
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name = "field_name" value="${dto.field_name}" disabled size="40"/>
            		<input type = "hidden"  name = "field_code" value="${dto.field_code}"/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">評価期間</th>
         		 <td>
         		   <input type = "text" class="form-control-plaintext disis" name = "conduct_start_month" value="${dto.conduct_start_month}" style="width:90px; float:left;" disabled/>
         		   <span style="width:5%; line-height: 1.5; float:left; text-align:center; vertical-align: middle;">~&nbsp;</span>
         		   <input type = "text" class="form-control-plaintext disis" name = "conduct_end_month" value="${dto.conduct_end_month}" style="width:20%;float:left;" disabled/>
				</td>
			</tr>        
             <tr>
            	<th class="table-info" style="border-bottom:none;">
            		作業場所
            	</th>
           	<td>
           		 <input type = "text" class="form-control-plaintext disis" name = "field_addr" value="${dto.field_addr}" disabled/>
           	</td>
      		</tr>
            <tr>
	            <th class="table-info" style="background:#afffb9; border:3px solid #afffb9;">
	            	作業役割
	            </th>
            	<td style="padding:0 7px; border:3px solid #afffb9;">
            		<input type = "text" class="form-control-plaintext disis" name = "field_role" value="${dto.field_role}" autofocus="autofocus"/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発環境
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name = "field_env" value="${dto.field_env}" disabled size="40" height="20px"/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発ツール
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name ="field_tool" value="${dto.field_tool}" disabled size="40" height="20px"/>
            	</td>
            </tr>
              </table>
             <table class="table table-bordered profiletable" style="width:70%; height:10%; margin:3px auto;">
           		 <tr>
             	   <th class="table-info" style="width:10%; padding:10px;">
             	   		氏名/ 社番
             	   	</th>
                	<td>
                  		<input type = "text" class="form-control-plaintext disis" name = "emp_name" value="${dto.emp_name} / ${dto.emp_id}" disabled>
                  		<input type = "hidden" name = "emp_id" value="${dto.emp_id}">
                	</td>
                	<th class="table-info" style="width:10%;">
                		職位
                	</th>
                	<td style="width:15%;">
                		<input type = "text" class="form-control-plaintext disis"  class="form-control" name = "field_position_name" value="${dto.field_position_name}" disabled/>
                		<input type = "hidden" name = "field_position_code" value="${dto.field_position_code}"/>
                	</td>
                	<th class="table-info" style="width:10%; padding:10px;">
                		評価者
                	</th>
                	<td>
                		<input type = "text" class="form-control-plaintext disis" id="appriaiser_name" name = "appriaiser_name" value="${sessionScope.sname}" disabled>
                		<input type="hidden" name="appraisal_idx" value="${dto.appraisal_idx}">
                	</td>
         	   </tr>
         	 </table> 
				<div style="margin-left:15%; padding-top:1%;">
					<h2>
						顧客評価
					</h2>
				</div>
					<table class="table table-bordered" style="width:70%; margin:3px auto;">
							<tr style="background:#e9ecef;">
								<th style="width:20%;"></th>
								<th style="width:12%;">評価</th>
								<th style="width:68%;">コメント</th>
							</tr>
							<tr>
								<th class="canWrite"> 業務能力<br>(0~10)</th>
								<td style="border:3px solid #afffb9;"><input id="cust1" type="number" class="form-control content" name="cust_ability" value="${dto.cust_ability}" onchange="score_check('cust')" onkeyup="score_check('cust')"></td>
								<td style="border:3px solid #afffb9;">
									<textarea id="cust2" class = "form-control-plaintext" name="cust_ability_cmt" style="resize: none; width: 100%; height: 100%;"  maxlength="600" rows="2" placeholder="600桁以内">${dto.cust_ability_cmt}</textarea>
								</td>
							</tr>
							<tr>
								<th class="canWrite">次プロジェクト継続有無<br>(0~10) </th>
								<td style="border:3px solid #afffb9;"><input id="cust3" type="number" class="form-control content" name="cust_keepwork" value="${dto.cust_keepwork}" onchange="score_check('cust')" onkeyup="score_check('cust')"></td>
								<td style="border:3px solid #afffb9;">
									<textarea id="cust4" class = "form-control-plaintext" name="cust_keepwork_cmt" style="resize: none; width: 100%; height: 100%;"  maxlength="600" rows="2" placeholder="600桁以内">${dto.cust_keepwork_cmt}</textarea>
								</td>
							</tr>
							<tr>
								<th class="table-secondary">合計</th>							
								<td style="border-right: none; padding-right:2%;"><input id="cust5" type="text" class="form-control-plaintext content" name="cust_sum" value="${dto.cust_sum}" readonly></td>
								<td style="border: none;"></td>
							</tr>
					</table>
				<div style="margin-left:15%; padding-top:1%;">
					<h2>
						実績評価
					</h2>
				</div>
					<table class="table table-bordered" style="width:70%; margin:3px auto;">
							<tr style="background:#e9ecef;">
								<th style="width:20%;"></th>
								<th style="width:12%;">評価</th>
								<th style="width:68%;">コメント</th>
							</tr>
							<tr>
								<th class="canWrite">売上及び利益貢献度<br>(0~10)</th>
								<td style="border:3px solid #afffb9;"><input id="perform1" type="number" class="form-control content" name="ld_sales" value="${dto.ld_sales}" onchange="score_check('perform')" onkeyup="score_check('perform')"></td>
								<td style="border:3px solid #afffb9; ">
									<textarea id="perform2" class = "form-control-plaintext" name="ld_sales_cmt" style="resize: none; width: 100%; height: 100%;"  maxlength="600" rows="2" placeholder="600桁以内">${dto.ld_sales_cmt}</textarea>
								</td>
							</tr>
							<tr>
								<th class="canWrite">社員育成貢献度 <br>(0~10)</th>
								<td style="border:3px solid #afffb9;"><input id="perform3" type="number" class="form-control content" name="ld_promote" value="${dto.ld_promote}" onchange="score_check('perform')" onkeyup="score_check('perform')"></td>
								<td style="border:3px solid #afffb9;">
									<textarea id="perform4" class = "form-control-plaintext" name="ld_promote_cmt" style="resize: none; width: 100%; height: 100%;"  maxlength="600" rows="2" placeholder="600桁以内">${dto.ld_promote_cmt}</textarea>
								</td>
							</tr>
							<tr>
								<th class="table-secondary">合計</th>							
								<td style="border-right: none; padding-right:2%;"><input id="perform5" type="text" class="form-control-plaintext content" name="sales_sum" value="${dto.sales_sum}" readonly></td>
								<td style="border: none;"></td>
							</tr>
					</table>
				
				<div align="center" style="margin:7px auto;">
					 <input id="sb_btn" class="ui primary button" type="button" value="登録" onclick="beforesubmit()"/>
					<input class="ui button" type="button" value="戻る" onclick="formBack()"/>
				</div>
</form>
</body>
</html>
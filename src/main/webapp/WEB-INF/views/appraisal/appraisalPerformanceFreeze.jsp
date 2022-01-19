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
</script>
<style>
	.table tr td{padding:1px; text-align:center; vertical-align: middle;}
	.table-secondary{background:#e9ecef;}
	.profiletable th {width:20%; padding:1px auto;}
	.profiletable tr td{ text-align:justify; padding:1px 0px 1px 10px;}
	._cmt{white-space: pre-line; word-break: break-all; padding: 1px; text-align: left; vertical-align: text-top; overflow-y:scroll; height:70px; padding-left:10px;}
</style> 
</head>
<body>
	<table class="table table-bordered profiletable" style="width:70%; margin: auto;  ">
            <tr>
            	<th class="table-info" style="width:20%;">
            		プロジェクト名
            	</th>
            	<td>
            		${dto.field_name}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		評価期間
            	</th>
         		 <td>
         		   ${dto.conduct_start_month}
         		   ~
         		   ${dto.conduct_end_month}
				</td>
			</tr>        
            <tr>
            	<th class="table-info">
            		作業場所
            	</th>
           	<td>
           		${dto.field_addr}
           	</td>
      		</tr>
            <tr>
	            <th class="table-info">
	            	作業役割
	            </th>
            	<td>
            		${dto.field_role}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発環境
            	</th>
            	<td>
            		${dto.field_env}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発ツール
            	</th>
            	<td>
            		${dto.field_tool}
            	</td>
            </tr>
              </table>
             <table class="table table-bordered profiletable" style="width:70%; height:10%; margin:3px auto;">
           		 <tr>
             	   <th class="table-info" style="width:10%;">
             	   		氏名/ 社番
             	   	</th>
                	<td>
                  		${dto.emp_name} / ${dto.emp_id}
                	</td>
                	<th class="table-info" style="width:10%;">
                		職位
                	</th>
                	<td style="width:15%;">
                		${dto.field_position_name}
                	</td>
                	<th class="table-info" style="width:10%; padding:10px;">
                		評価者
                	</th>
                	<td>
                		${dto.appriaiser_name}
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
								<th class="table-info" style="width:20%; "></th>
								<th class="table-info" style="width:12%;">評価</th>
								<th class="table-info" style="width:68%;">コメント</th>
							</tr>
							<tr>
								<th class="table-info"> 業務能力<br>(0~10)</th>
								<td style="text-align: right; padding-right: 10px;">${dto.cust_ability}</td>
								<td style="width:300px;">
									<p class="_cmt">
									${dto.cust_ability_cmt}
									</p>
								</td>
							</tr>
							<tr>
								<th class="table-info">次プロジェクト継続有無<br>(0~10) </th>
								<td style="text-align: right; padding-right: 10px;">${dto.cust_keepwork}</td>
								<td style="width:60%;">
									<p class = "_cmt">
										${dto.cust_keepwork_cmt}
									</p>
								</td>
							</tr>
							<tr>
								<th class="table-info">合計</th>							
								<td style="border-right: none; text-align: end; padding-right: 10px;">
									${dto.cust_sum}</td>
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
								<th class="table-info" style="width:20%;">実績評価 </th>
								<th class="table-info" style="width:12%;">評価</th>
								<th class="table-info" style="width:68%;">コメント</th>
							</tr>
							<tr>
								<th class="table-info">売上及び利益貢献度<br>(0~10)</th>
								<td style="text-align: right; padding-right: 10px;">${dto.ld_sales}</td>
								<td style="width:60%;">
									<p class = "_cmt">
										${dto.ld_sales_cmt}
									</p>
								</td>
							</tr>
							<tr>
								<th class="table-info">社員育成貢献度 <br>(0~10)</th>
								<td style="text-align: right; padding-right: 10px;">${dto.ld_promote}</td>
								<td>
									<p  class = "_cmt">
										${dto.ld_promote_cmt}
									</p>		
								</td>
							</tr>
							<tr>
								<th class="table-info">合計</th>							
								<td style="border-right: none; text-align: end; padding-right: 10px;"><label>${dto.sales_sum}</label></td>
								<td  style="border: none;"></td>
							</tr>
					</table>
				<div align="center" style="margin:7px auto;">
					<button type="button" class="ui button"  onclick="formBack()">戻る</button>
				</div>
</body>
</html>